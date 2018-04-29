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
    
    @Override
    public ArrayList obtenirStations() {
        //return this.gestionStation.obtenir();
    }

    @Override
    public void reserverVoyage() {
       this.gestionVoyage.reserver();
    }

    
    /**
     * Ce service permet à un client d'annuler un voyage
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
