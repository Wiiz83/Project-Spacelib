package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.StationsIdentiquesException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RVoyage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.Remote;


@Remote
public interface ServicesUsagerRemote {
    
    public Long login (String nom, String pass) throws UsagerInconnuException;    
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse);
    
    public RVoyage voyageEnCours(Long idUsager);
    
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException;

    public ArrayList<RStation> obtenirStations() throws IllegalAccessException, InvocationTargetException;   
    
    public ArrayList<RVoyage> obtenirVoyagesUsager(Long idUsager) throws UsagerInconnuException;  

    public RVoyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws InvocationTargetException, IllegalAccessException, StationsIdentiquesException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException;

    public void annulerVoyage(Long idUsager, Long idVoyage) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException;

}
