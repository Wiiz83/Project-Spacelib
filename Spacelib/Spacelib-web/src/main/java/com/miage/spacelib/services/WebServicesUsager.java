package com.miage.spacelib.services;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.ressources.rStation;
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
    public ArrayList<rStation> obtenirStations() throws IllegalAccessException, InvocationTargetException {
        ArrayList<rStation> stations = ejbRef.obtenirStations();
        return stations;
    }
    
    @WebMethod(operationName = "reserverVoyage")
    public Voyage reserverVoyage(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee, @WebParam(name = "NbPassagers") int NbPassagers, @WebParam(name = "dateDepart") Calendar dateDepart) throws UsagerInconnuException, StationInconnuException {
       //return this.ejbRef.reserverVoyage(idUsager, idStationDepart, idStationArrivee, NbPassagers, dateDepart);
       return null;
    }
    

    @WebMethod(operationName = "obtenirVoyagesUsager")
    public ArrayList<Voyage> obtenirVoyagesUsager(@WebParam(name = "idUsager") Long idUsager) throws UsagerInconnuException {
       //return this.ejbRef.obtenirVoyagesUsager(idUsager);
       return null;
    }
    
    @WebMethod(operationName = "annulerVoyage")
    public void annulerVoyage(@WebParam(name = "idUsager") Long idUsager, @WebParam(name = "idVoyage") Long idVoyage) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        this.ejbRef.annulerVoyage(idUsager, idVoyage);
    }
    
    
}
