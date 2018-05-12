package com.miage.spacelib.business;

import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import javax.ejb.Local;


@Local
public interface GestionVoyageLocal {

    public void reserver();
   
    public void annulerVoyage(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException;

    public Voyage voyageEnCours(Long idUsager) throws UsagerInconnuException;
    
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException;
}
