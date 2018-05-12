/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.business.GestionUsagerLocal;
import com.miage.spacelib.business.GestionVoyageLocal;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.ressources.rStation;
import com.miage.spacelib.ressources.rVoyage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author uzanl
 */
@Stateless
public class ServicesUsager implements ServicesUsagerRemote {
    
    @EJB
    private GestionStationLocal gestionStation;
    
    @EJB
    private GestionVoyageLocal gestionVoyage;
    
    @EJB
    private GestionUsagerLocal gestionUsager;
    
    
    // RETOURNE LE LOGIN DE L'USAGER POUR LE RECUPERER DANS LE CLIENT LOURD ET WEB COMME VARIABLE DE SESSION
    @Override
    public Long login(String login, String motdepasse) throws UsagerInconnuException {
        return this.gestionUsager.authentifier(login, motdepasse);
    }

    @Override
    public void creerCompte(String nom, String prenom, String login, String motdepasse) {

    }

    @Override
    public rVoyage voyageEnCours(Long idUsager) {
        rVoyage rvoy = new rVoyage();
        return null;
    }
    

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException{
    } 

    /**
     * Ce service retourne la liste des stations existantes (incluant leur localisation)
     * CF 3.1
     */
    @Override
    public ArrayList<rStation> obtenirStations() throws IllegalAccessException, InvocationTargetException{
        List<Station> stations = this.gestionStation.recupererListeStations();
        ArrayList<rStation> resultList = new ArrayList<>();
        for(Station station : stations){
            rStation rstation = new rStation();
            BeanUtils.copyProperties(rstation, station);
            resultList.add(rstation);
        }
        return resultList;
    }

    
    /**
     * Ce service permet à un client de réserver un voyage
     * CF 3.2
     */
    @Override
    public rVoyage reserverVoyage(Long idClient, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws UsagerInconnuException, StationInconnuException {
       //return this.gestionVoyage.reserver();
       return null;
    }
    
    @Override
    public ArrayList<rVoyage> obtenirVoyagesUsager(Long idUsager) throws UsagerInconnuException {
        return null;
    }

    
    /**
     * Ce service permet à un client d'annuler un voyage
     * CF 3.3
     */
    @Override
    public void annulerVoyage(Long idClient, Long idUsager) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        this.gestionVoyage.annulerVoyage(idClient, idUsager);
    }

    
}
