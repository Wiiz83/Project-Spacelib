/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.business.GestionVoyageLocal;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
    
    // RETOURNE LE LOGIN DE L'USAGER POUR LE RECUPERER DANS LE CLIENT LOURD ET WEB COMME VARIABLE DE SESSION
    @Override
    public Long login(String nom, String pass) throws UsagerInconnuException {
        return null; 
    }

    @Override
    public void creerCompte(String nom, String prenom, String login, String motdepasse) {

    }

    /*
    
    // NOTE PAR LUCAS POUR MAHDI : Utilise plutôt la méthode reserverVoyage(), 
    // l'utilisateur choisi des stations de départ et d'arrivée
    // il ne choisi pas les quais : c'est fait par le système automatiquement ça
    @Override
    public Voyage reserver(int nbPassagers, long idQuaiDepart, long idQuaiArrivee, Calendar dateDepart) {
        return null;
    }

    @Override
    public Voyage voyageEnCours(Long idUsager) {
        return null;
    }
    
     */

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException{
    } 

    /**
     * Ce service retourne la liste des stations existantes (incluant leur localisation)
     * CF 3.1
     */
    //@Override
    public ArrayList<Station> obtenirStations() {
        //return this.gestionStation.obtenir();
        return null;
    }

    
    /**
     * Ce service permet à un client de réserver un voyage
     * CF 3.2
     */
    //@Override
    public Voyage reserverVoyage(Long idClient, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws UsagerInconnuException, StationInconnuException {
       //return this.gestionVoyage.reserver();
       return null;
    }
    
    //@Override
    public ArrayList<Voyage> obtenirVoyagesUsager(Long idUsager) throws UsagerInconnuException {
        return null;
    }

    
    /**
     * Ce service permet à un client d'annuler un voyage
     * CF 3.3
     */
    @Override
    public void annulerVoyage(Long idClient, Long idUsager) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        this.gestionVoyage.annuler(idClient, idUsager);
    }

    
}
