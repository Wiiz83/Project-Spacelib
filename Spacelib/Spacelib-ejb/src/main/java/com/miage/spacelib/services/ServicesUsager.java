/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.business.GestionUsagerLocal;
import com.miage.spacelib.business.GestionVoyageLocal;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RVoyage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.beanutils.BeanUtils;

@Stateless
public class ServicesUsager implements ServicesUsagerRemote {

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionVoyageLocal gestionVoyage;

    @EJB
    private GestionUsagerLocal gestionUsager;

    @EJB
    private StationFacadeLocal stationFacade;
    
    @EJB
    private QuaiFacadeLocal quaiFacade;
    
    

    // RETOURNE LE LOGIN DE L'USAGER POUR LE RECUPERER DANS LE CLIENT LOURD ET WEB COMME VARIABLE DE SESSION
    @Override
    public Long login(String login, String motdepasse) throws UsagerInconnuException {
        return this.gestionUsager.authentifier(login, motdepasse);
    }

    @Override
    public Long creerCompte(String nom, String prenom, String login, String motdepasse) {
        return this.gestionUsager.creerCompte(nom, prenom, login, motdepasse);
    }

    @Override
    public RVoyage voyageEnCours(Long idUsager) throws  UsagerInconnuException, VoyageInconnuException {
        Voyage voyage = this.gestionVoyage.voyageEnCours(idUsager);
        RVoyage rvoyage = new RVoyage();        
        rvoyage.setDateArrivee(voyage.getDateArrivee());
        rvoyage.setDateCreation(voyage.getDateCreation());
        rvoyage.setDateDepart(voyage.getDateDepart());
        rvoyage.setId(voyage.getId());
        rvoyage.setNavette(voyage.getNavette().getId());
        rvoyage.setNbPassagers(voyage.getNbPassagers());
        rvoyage.setQuaiArrivee(voyage.getQuaiArrivee().getId());
        rvoyage.setQuaiDepart(voyage.getQuaiDepart().getId());
        rvoyage.setStatut(voyage.getStatut());
        rvoyage.setUsager(voyage.getUsager().getId());
        return rvoyage;
    }

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException {
        this.gestionVoyage.finaliserVoyage(idVoyage);
    }

    /**
     * Ce service retourne la liste des stations existantes (incluant leur
     * localisation) CF 3.1
     */
    @Override
    public ArrayList<RStation> obtenirStations() throws IllegalAccessException, InvocationTargetException {
        List<Station> stations = this.gestionStation.recupererListeStations();
        ArrayList<RStation> resultList = new ArrayList<>();
        for (Station station : stations) {
            RStation rstation = new RStation();
            BeanUtils.copyProperties(rstation, station);
            resultList.add(rstation);
        }
        return resultList;
    }

    /**
     * Ce service permet à un client de réserver un voyage CF 3.2
     */
    @Override
    public RVoyage reserverVoyage(Long idClient, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        Voyage voyage = this.gestionVoyage.reserverVoyage(idClient, idStationDepart, idStationArrivee, NbPassagers, dateDepart);
        System.out.println("Voyage = " + voyage);
        RVoyage rvoyage = new RVoyage();
        System.out.println("rvoyage" + voyage.getStatut());
        System.out.println("rvoyage" + voyage.getDateArrivee());
        System.out.println("rvoyage" + voyage.getNavette());

        rvoyage.setDateArrivee(voyage.getDateArrivee());
        rvoyage.setDateCreation(voyage.getDateCreation());
        rvoyage.setDateDepart(voyage.getDateDepart());
        rvoyage.setId(voyage.getId());
        rvoyage.setNavette(voyage.getNavette().getId());
        rvoyage.setNbPassagers(voyage.getNbPassagers());
        rvoyage.setQuaiArrivee(voyage.getQuaiArrivee().getId());
        rvoyage.setQuaiDepart(voyage.getQuaiDepart().getId());
        rvoyage.setStatut(voyage.getStatut());
        rvoyage.setUsager(voyage.getUsager().getId());

        System.out.println("rvoyage" + rvoyage.getStatut());
        System.out.println("rvoyage" + rvoyage.getDateArrivee());
        System.out.println("rvoyage" + rvoyage.getNavette());

        //BeanUtils.copyProperties(rvoyage, voyage);
        System.out.println("rvoyage2" + rvoyage);
        return rvoyage;
    }

    @Override
    public ArrayList<RVoyage> obtenirVoyagesPrevusUsager(Long idUsager) throws UsagerInconnuException {
        List<Voyage> voyages = this.gestionVoyage.obtenirVoyagesPrevusUsager(idUsager);
        
        ArrayList<RVoyage> rvoyages = new ArrayList<>();
        
        for (Voyage voyage : voyages) {
            RVoyage rvoyage = new RVoyage();
            
            rvoyage.setDateArrivee(voyage.getDateArrivee());
            rvoyage.setDateCreation(voyage.getDateCreation());
            rvoyage.setDateDepart(voyage.getDateDepart());
            rvoyage.setId(voyage.getId());
            rvoyage.setNavette(voyage.getNavette().getId());
            rvoyage.setNbPassagers(voyage.getNbPassagers());
            rvoyage.setQuaiArrivee(voyage.getQuaiArrivee().getId());
            rvoyage.setQuaiDepart(voyage.getQuaiDepart().getId());
            rvoyage.setStatut(voyage.getStatut());
            rvoyage.setUsager(voyage.getUsager().getId());
            
            rvoyages.add(rvoyage);
        }
        return rvoyages;
    }

    /**
     * Ce service permet à un client d'annuler un voyage CF 3.3
     */
    @Override
    public void annulerVoyage(Long idClient, Long idUsager) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
        this.gestionVoyage.annulerVoyage(idClient, idUsager);
    }

    @Override
    public RStation obtenirStationParIdQuai(Long idQuai) throws StationInconnuException, QuaiInconnuException {
        Quai q = this.quaiFacade.find(idQuai);
        if(q == null){
            throw new QuaiInconnuException("Quai inconnue");
        }

        Station s = this.stationFacade.find(q.getStation().getId());
        if(s == null){
            throw new StationInconnuException("Station inconnue");
        }
        
        RStation rstation = new RStation();

        rstation.setId(s.getId());
        rstation.setLocalisation(s.getLocalisation());
        rstation.setNbQuais(s.getNbQuais());
        rstation.setNom(s.getNom());
        rstation.setStatut(s.getStatut());

        return rstation;
    }

    @Override
    public RVoyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
