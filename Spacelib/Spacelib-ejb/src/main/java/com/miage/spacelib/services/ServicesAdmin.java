/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.ressources.RStation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Override
    public Long creerStation(String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes,Map<Long,Integer> tempsTrajets) throws NombreNavettesInvalideException {
        return this.gestionStation.creerStation(localisation, nom, nbquais, nbPlacesNavettes, tempsTrajets);
    }

 
    @Override
    public ArrayList<RStation> obtenirStations() {
        List<Station> stations = this.gestionStation.recupererListeStations();
        ArrayList<RStation> resultList = new ArrayList<>();
        for (Station station : stations) {
            RStation rstation = new RStation();
            rstation.setId(station.getId());
            rstation.setLocalisation(station.getLocalisation());
            rstation.setNbQuais(station.getNbQuais());
            rstation.setNom(station.getNom());
            rstation.setStatut(station.getStatut());
            resultList.add(rstation);
        }
        return resultList;
    }
}
