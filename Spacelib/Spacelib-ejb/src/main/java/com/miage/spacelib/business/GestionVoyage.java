/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.exceptions.ClientInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {

    
    
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
    public void annuler(Long idClient, Long idReservation) throws ClientInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        
        
    }

}
