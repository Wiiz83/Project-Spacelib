/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.NombreNavettesInvalideException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface GestionStationLocal {
    
    public List<Station> recupererListeStations();
 
    public Long creerStation (String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes, Map<Long,Integer> tempsTrajets ) throws NombreNavettesInvalideException;   
    
//    public List<Station> stationsAEquilibrer(Long idStationDepart) throws StationInconnuException ;
    public int variationNbNavettesDans10Jours (Long idStation) ;
}
