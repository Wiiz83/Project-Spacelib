package com.miage.spacelib.services;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.NavetteInconnuException;
import com.miage.spacelib.exceptions.NavettePourQuaiInexistantException;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.RevisionInconnuException;
import com.miage.spacelib.exceptions.RevisionInexistanteException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
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
    
    
    @WebMethod(operationName = "creerCompte")
    public void creerCompte(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) throws UtilisateurExistantException {
        ejbRef.creerCompte(nom, prenom, login, motdepasse);
    }
   
    @WebMethod(operationName = "authentifier")
    public void authentifier(@WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) throws MecanicienInconnuException {
        ejbRef.authentifier(login, motdepasse);
    }
    
    @WebMethod(operationName = "authentifierAvecStationRattachement")
    public long authentifierAvecStationRattachement(@WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse, @WebParam(name = "idStation") long idStation) throws MecanicienInconnuException, StationInconnuException {
        return ejbRef.authentifierAvecStationRattachement(login, motdepasse, idStation);
    }
 
    @WebMethod(operationName = "recupererListeStations")
    public List<Station> recupererListeStations() {
        return ejbRef.recupererListeStations();
    }      
    
    @WebMethod(operationName = "recupererListeNavettesAReviser")
    public List<Revision> recupererListeNavettesAReviser(@WebParam(name = "idStation") long idStation) throws StationInconnuException, QuaiInexistantException, NavettePourQuaiInexistantException, RevisionInexistanteException {
        return ejbRef.recupererListeNavettesAReviser(idStation);
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
    public Quai choisirNavetteDebutRevision(@WebParam(name = "idNavette") long idNavette, @WebParam(name = "idStation") long idStation, @WebParam(name = "idMecanicien") long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException {
        return ejbRef.choisirNavetteDebutRevision(idNavette, idStation, idMecanicien);
    }
    
    /*
    REVISION DE LA NAVETTE TERMINEE
    */
    
    @WebMethod(operationName = "consulterRevisionEnCours")
    public Revision consulterRevisionEnCours(@WebParam(name = "idMecanicien") long idMecanicien, @WebParam(name = "idStation") long idStation) throws NavetteInconnuException, QuaiInexistantException, RevisionInexistanteException {
        return ejbRef.consulterRevisionEnCours(idMecanicien,idStation);
    }
    
    @WebMethod(operationName = "finirRevisionEnCours")
    public void finirRevisionEnCours(@WebParam(name = "idNavette") long idNavette, @WebParam(name = "idStation") long idStation, @WebParam(name = "idMecanicien") long idMecanicien) throws QuaiInconnuException, NavetteInconnuException, MecanicienInconnuException{
        ejbRef.finirRevisionEnCours(idNavette, idStation, idMecanicien);
    }
    
}
