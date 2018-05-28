/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.TempsTrajet;
import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.repositories.TempsTrajetFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;
    
    @EJB
    private TempsTrajetFacadeLocal tempsTrajetFacade;

    @Override
    public List<Station> recupererListeStations() {
        return this.stationFacade.findAll();
    }

    @Override
    public Long creerStation(String localisation, String nom, Long nb_quais, ArrayList<Integer> nbPlacesNavettes, Map<Long, Integer> tempsTrajets) throws NombreNavettesInvalideException {
        int nb_navettes = nbPlacesNavettes.size();
        if (nb_quais < nb_navettes || nb_navettes * 2 + 1 < nb_quais) {
            throw new NombreNavettesInvalideException("Nombre de quais / navettes invalide.");
        }
        Station station = new Station(localisation, (int) (long) nb_quais, nom);
        

        List<Navette> navettes = new ArrayList<>();
        for (Integer places : nbPlacesNavettes) {
            Navette navette = new Navette(places);
            this.navetteFacade.create(navette);
            navettes.add(navette);
        }
        int index_navette = 0;
        List<Quai> quais = new ArrayList<>();
        for (int i = 0; i < nb_quais; i++) {
            Quai quai = new Quai(station);
            if (index_navette < navettes.size()) {
                quai.setNavette(navettes.get(index_navette));
                index_navette++;
            }
            this.quaiFacade.create(quai);
            quais.add(quai);
        }
        station.setQuais(quais);
        this.stationFacade.create(station);
        
        for (Map.Entry<Long, Integer> entry : tempsTrajets.entrySet()) {
            Station station_arrivee = this.stationFacade.find(entry.getKey());
            TempsTrajet temps = new TempsTrajet (station,station_arrivee,entry.getValue());
            tempsTrajetFacade.create(temps);
            TempsTrajet temps_sym = new TempsTrajet (station_arrivee,station,entry.getValue());
            tempsTrajetFacade.create(temps_sym);
        }        
        return station.getId();
    }
}
