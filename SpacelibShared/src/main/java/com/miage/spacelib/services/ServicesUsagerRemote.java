package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.ressources.rStation;
import com.miage.spacelib.ressources.rVoyage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.Remote;


@Remote
public interface ServicesUsagerRemote {
    
    public Long login (String nom, String pass) throws UsagerInconnuException;    
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse);
    
    public rVoyage voyageEnCours(Long idUsager);
    
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException;

    public ArrayList<rStation> obtenirStations() throws IllegalAccessException, InvocationTargetException;   
    
    public ArrayList<rVoyage> obtenirVoyagesUsager(Long idUsager) throws UsagerInconnuException;  

    public rVoyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws UsagerInconnuException, StationInconnuException;

    public void annulerVoyage(Long idUsager, Long idVoyage) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException;

}
