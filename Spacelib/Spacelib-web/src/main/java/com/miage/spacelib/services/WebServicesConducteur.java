package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RTransfert;
import com.miage.spacelib.ressources.RTransfertNecessaire;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "WebServicesConducteur")
@Stateless()
public class WebServicesConducteur {

    @EJB
    private ServicesConducteurLocal ejbRef;
    
    
    @WebMethod(operationName = "login")
    public Long login(@WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) throws UsagerInconnuException {
        return ejbRef.login(login, motdepasse);
    }
    
    @WebMethod(operationName = "creerCompte")
    public void creerCompte(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom, @WebParam(name = "login") String login, @WebParam(name = "motdepasse") String motdepasse) throws UtilisateurExistantException {
        ejbRef.creerCompte(nom, prenom, login, motdepasse);
    }
    
    @WebMethod(operationName = "obtenirTransfertsNecessaires")
    public List<RTransfertNecessaire> obtenirTransfertsNecessaires() {
        return this.ejbRef.obtenirTransfertsNecessaires();
    }
    
    @WebMethod(operationName = "reserverTransfert")
    public RTransfert reserverTransfert(@WebParam(name = "idConducteur") Long idConducteur, @WebParam(name = "idStationDepart") Long idStationDepart, @WebParam(name = "idStationArrivee") Long idStationArrivee) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        System.out.println("PASSE");
        return this.ejbRef.reserverTransfert(idConducteur, idStationDepart, idStationArrivee);
    }

    @WebMethod(operationName = "obtenirTransfertsConducteur")
    public ArrayList<RTransfert> obtenirTransfertsConducteur(@WebParam(name = "idConducteur") Long idConducteur) throws UsagerInconnuException {
       return this.ejbRef.obtenirTransfertsConducteur(idConducteur);
    }
    
    
    
    
}
