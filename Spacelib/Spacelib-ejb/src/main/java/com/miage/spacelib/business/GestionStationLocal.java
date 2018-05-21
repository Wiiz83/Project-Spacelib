/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.NombreNavettesInvalideException;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface GestionStationLocal {
    
    public List<Station> recupererListeStations();
 
    public Long creerStation (String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes ) throws NombreNavettesInvalideException;    
}
