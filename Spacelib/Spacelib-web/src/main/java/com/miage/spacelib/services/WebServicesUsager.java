package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RVoyage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;


@WebService(serviceName = "WebServicesUsager")
@Stateless()
public class WebServicesUsager {

    @EJB
    private ServicesUsagerRemote ejbRef;
    
    
    @WebMethod(operationName = "login")
    public Long login(@WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) throws UsagerInconnuException {
        return ejbRef.login(login, motdepasse);
    }
    
    @WebMethod(operationName = "creerCompte")
    public void creerCompte(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) {
        ejbRef.creerCompte(nom, prenom, login, motdepasse);
    }
    
    @WebMethod(operationName = "obtenirStations")
    public ArrayList<RStation> obtenirStations() throws IllegalAccessException, InvocationTargetException {
        ArrayList<RStation> stations = ejbRef.obtenirStations();
        return stations;
    }
    
    @WebMethod(operationName = "reserverVoyage")
    public RVoyage reserverVoyage(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "NbPassagers") int NbPassagers, @WebParam(name = "dateDepart") Calendar dateDepart) throws QuaiInexistantException, QuaiIndisponibleException, InvocationTargetException, IllegalAccessException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
       return this.ejbRef.reserverVoyage(idUsager, idStationDepart, idStationArrivee, NbPassagers, dateDepart);
    }

    @WebMethod(operationName = "obtenirVoyagesUsager")
    public ArrayList<RVoyage> obtenirVoyagesUsager(@WebParam(name = "idUsager") Long idUsager) throws UsagerInconnuException {
       return this.ejbRef.obtenirVoyagesUsager(idUsager);
    }
    
    @WebMethod(operationName = "annulerVoyage")
    public void annulerVoyage(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idVoyage") Long idVoyage) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        this.ejbRef.annulerVoyage(idUsager, idVoyage);
    }
    
    
}
