package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
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

    public Long login(String nom, String pass) throws UsagerInconnuException;

    public Long creerCompte(String nom, String prenom, String login, String motdepasse);

    public RVoyage voyageEnCours(Long idUsager) throws UsagerInconnuException, VoyageInconnuException;

    public void finaliserVoyage(Long idVoyage)
            throws VoyageInconnuException;

    public ArrayList<RStation> obtenirStations()
            throws IllegalAccessException, InvocationTargetException;

    public ArrayList<RVoyage> obtenirVoyagesPrevusUsager(Long idUsager)
            throws UsagerInconnuException;

    public RVoyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart)
            throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException;

    public void annulerVoyage(Long idUsager, Long idVoyage)
            throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException;

    public RStation obtenirStationParIdQuai(Long idQuai) throws StationInconnuException, QuaiInconnuException;
}
