package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.ClientInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.Remote;


@Remote
public interface ServicesUsagerRemote {
    
    public boolean login (String nom, String pass);    
    
    public void creeCompte(String nom, String prenom, String login, String motdepasse);
    
    public long reserver(int nbPassagers, long idQuaiDepart, long idQuaiArrivee, Calendar dateDepart);
    
    public long voyageEnCours(long idUsager);
    
    public void finaliserVoyage(long idVoyage);

    public ArrayList obtenirStations();   

    public void reserverVoyage(Long idClient, Long idStationDepart, Long idStationArrivee, int NbPassagers) throws ClientInconnuException, StationInconnuException;

    public void annulerVoyage(Long idClient, Long idReservation) throws ClientInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException;

}
