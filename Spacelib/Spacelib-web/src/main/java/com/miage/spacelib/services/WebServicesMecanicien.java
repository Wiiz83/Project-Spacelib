package com.miage.spacelib.services;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.NavetteInconnuException;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.RevisionInconnuException;
import com.miage.spacelib.exceptions.StationInconnuException;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "WebServicesMecanicien")
@Stateless()
public class WebServicesMecanicien {

    @EJB
    private ServicesMecanicienLocal ejbRef;
 
    
    
    @WebMethod(operationName = "authentifier")
    public void authentifier(@WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) throws MecanicienInconnuException {
        ejbRef.authentifier(login, motdepasse);
    }
    
    @WebMethod(operationName = "authentifierAvecStationRattachement")
    public void authentifierAvecStationRattachement(@WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse, @WebParam(name = "idStation") long idStation) throws MecanicienInconnuException, StationInconnuException {
        ejbRef.authentifierAvecStationRattachement(login, motdepasse, idStation);
    }
 
    @WebMethod(operationName = "recupererListeStations")
    public List<Station> recupererListeStations() {
        return ejbRef.recupererListeStations();
    }        
    
    @WebMethod(operationName = "renseignerStationRattachement")
    public long renseignerStationRattachement(@WebParam(name = "nomStation") String nom) throws StationInconnuException {
        return ejbRef.renseignerStationRattachement(nom);
    }
    
    /*
    CHOIX D'UNE NAVETTE A REVISER
    */
    
    @WebMethod(operationName = "consulterListeNavettes")
    public List<Navette> consulterListeNavettes(@WebParam(name = "idStation") long idStation) throws StationInconnuException {
        return ejbRef.consulterListeNavettes(idStation);
    }

    @WebMethod(operationName = "choisirNavetteDebutRevision")
    public Quai choisirNavetteDebutRevision(@WebParam(name = "idNavette") long idNavette, @WebParam(name = "idMecanicien") long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException {
        return ejbRef.choisirNavetteDebutRevision(idNavette, idMecanicien);
    }
    
    /*
    REVISION DE LA NAVETTE TERMINEE
    */
    
    @WebMethod(operationName = "consulterRevisionEnCours")
    public Revision consulterRevisionEnCours(@WebParam(name = "idMecanicien") long idMecanicien) throws NavetteInconnuException {
        return ejbRef.consulterRevisionEnCours(idMecanicien);
    }
    
    @WebMethod(operationName = "finirRevisionEnCours")
    public void finirRevisionEnCours(@WebParam(name = "idRevision") long idRevision) throws RevisionInconnuException {
        ejbRef.finirRevisionEnCours(idRevision);
    }
    
    
}
