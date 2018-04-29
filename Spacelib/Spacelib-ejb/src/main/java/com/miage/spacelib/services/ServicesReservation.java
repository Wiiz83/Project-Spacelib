package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.business.GestionVoyageLocal;
import com.miage.spacelib.exceptions.*;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ServicesReservation implements ServicesReservationLocal {

    @EJB
    private GestionStationLocal gestionStation;
    
    @EJB
    private GestionVoyageLocal gestionVoyage;
    
    
    /**
     * Ce service retourne la liste des stations existantes (incluant leur localisation)
     * CF 3.1
     * 
     * @return la liste des stations existantes
     */
    @Override
    public ArrayList obtenirStations() {
        return this.gestionStation.obtenir();
    }

    
    /**
     * Ce service permet à un client de réserver un voyage
     * CF 3.2
     * 
     * @param idClient
     * @param idStationDepart
     * @param idStationArrivee
     * @param NbPassagers
     * @throws com.miage.spacelib.exceptions.ClientInconnuException
     * @throws com.miage.spacelib.exceptions.StationInconnuException
     */
    @Override
    public void reserverVoyage(Long idClient, Long idStationDepart, Long idStationArrivee, int NbPassagers) throws ClientInconnuException, StationInconnuException {
       this.gestionVoyage.reserver();
    }

    
    /**
     * Ce service permet à un client d'annuler un voyage
     * CF 3.3
     * 
     * @param idClient l'identifiant du client 
     * @param idReservation l'identifiant de la réservation à annuler
     * 
     * @throws com.miage.spacelib.exceptions.ClientInconnuException si l'id du client est inconnu
     * @throws com.miage.spacelib.exceptions.ReservationInconnuException si l'id de la réservation est inconnu
     * @throws com.miage.spacelib.exceptions.ReservationPasseeException si la date de départ est passé 
     * @throws com.miage.spacelib.exceptions.ReservationClotureeException si la réservation est clôturée
     */
    @Override
    public void annulerVoyage(Long idClient, Long idReservation) throws ClientInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        this.gestionVoyage.annuler(idClient, idReservation);
    }
}
