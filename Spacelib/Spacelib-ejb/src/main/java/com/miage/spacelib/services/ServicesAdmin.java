/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.business.equilibrage.Equilibrage;
import com.miage.spacelib.business.equilibrage.EquilibrageResultat;
import com.miage.spacelib.business.equilibrage.InfoStation;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RStatsStation;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mahdi
 */
@Stateless
public class ServicesAdmin implements ServicesAdminRemote {

    @EJB
    GestionStationLocal gestionStation;
    @EJB
    private StationFacadeLocal stationFacade;

    @Override
    public Long creerStation(String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes, Map<Long, Integer> tempsTrajets) throws NombreNavettesInvalideException {
        return this.gestionStation.creerStation(localisation, nom, nbquais, nbPlacesNavettes, tempsTrajets);
    }

    @Override
    public ArrayList<RStation> obtenirStations() {
        List<Station> stations = this.gestionStation.recupererListeStations();
        ArrayList<RStation> resultList = new ArrayList<>();
        for (Station station : stations) {
            resultList.add(rs(station));
        }
        return resultList;
    }

    @Override
    public List<Map.Entry<RStation, RStation>> transfertsEquilibrage() {
        List<Station> stations = stationFacade.findAll();
        Map<Station, Integer> variations = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        stations.forEach((s) -> {
            variations.put(s,
                    this.stationFacade.nbNavetteEntrantes(s.getId(), cal)
                    - this.stationFacade.nbNavetteSortantes(s.getId(), cal)
            );
        });
        Map<Station, InfoStation> infos = new HashMap<>();
        stations.forEach((s) -> {
            infos.put(s,
                    new InfoStation(this.stationFacade.nbNavettes(s.getId()),
                             this.stationFacade.nbQuais(s.getId())
                    )
            );
        });
        Equilibrage eq = new Equilibrage(variations, infos);
        EquilibrageResultat eqr = eq.obtenirResultats();
        List<Map.Entry<RStation, RStation>> dto = new ArrayList<>();
        List<Map.Entry<Station, Station>> res = eqr.listeTransferts();
        for (Map.Entry<Station, Station> entry : res) {
            dto.add(new AbstractMap.SimpleEntry<>(rs(entry.getKey()), rs(entry.getValue())));
        }
        return dto;
    }

    @Override
    public List<RStatsStation> stats() {
        List<Station> stations = stationFacade.findAll();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        List<RStatsStation> res = new ArrayList<>();
        for (Station station : stations) {
            RStatsStation stat = new RStatsStation();
            stat.nbNavettesArrimees = filtrer(station.getQuais(), q -> q.getNavette() != null).size();
            stat.nbNavettesEntrantes10jours = this.stationFacade.nbNavetteEntrantes(station.getId(), cal);
            stat.nbNavettesSortantes10jours = this.stationFacade.nbNavetteSortantes(station.getId(), cal);
            stat.station = rs(station);
            res.add(stat);
        }
        return res;
    }

    private RStation rs(Station station) {
        RStation rstation = new RStation();
        rstation.setId(station.getId());
        rstation.setLocalisation(station.getLocalisation());
        rstation.setNbQuais(station.getNbQuais());
        rstation.setNom(station.getNom());
        return rstation;
    }

    private <E> ArrayList<E> filtrer(List<E> all, Predicate<E> filter) {
        return filtrer(all, filter, null);
    }

    private <E> ArrayList<E> filtrer(List<E> all, Predicate<E> filter, Comparator<E> c) {
        ArrayList<E> filtered = all.stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
        if (c == null) {
            return filtered;
        } else {
            filtered.sort(c);
        }
        return filtered;
    }
}
