/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.repositories.StationFacadeLocal;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ArrayList obtenir() {
        return null;
    }

    @Override
    public List<Station> recupererListeStations() {
        System.out.println("com.miage.spacelib.business.GestionStation.recupererListeStations()");
        return this.stationFacade.findAll();
    }
}
