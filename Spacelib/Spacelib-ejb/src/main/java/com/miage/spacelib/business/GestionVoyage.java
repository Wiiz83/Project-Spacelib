/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {

    //@Override
    public void annuler(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        
        
    }

    @Override
    public void reserver() {
    
    
    }

    @Override
    public void annulerVoyage(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
    }

    @Override
    public Voyage voyageEnCours(Long idUsager) throws UsagerInconnuException {
        return null;
    }

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException {
    }

}
