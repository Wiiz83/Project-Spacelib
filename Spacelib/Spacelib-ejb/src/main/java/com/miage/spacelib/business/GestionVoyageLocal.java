package com.miage.spacelib.business;

import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.Local;


@Local
public interface GestionVoyageLocal {

    public Voyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException;

    public void annulerVoyage(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException;

    public Voyage voyageEnCours(Long idUsager) throws UsagerInconnuException, VoyageInconnuException;
    
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException;
    
    public ArrayList<Voyage> obtenirVoyagesPrevusUsager(Long idUsager) throws UsagerInconnuException;
}
