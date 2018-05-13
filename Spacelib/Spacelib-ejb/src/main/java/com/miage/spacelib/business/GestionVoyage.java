/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.TempsTrajet;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.StationsIdentiquesException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.repositories.TempsTrajetFacadeLocal;
import com.miage.spacelib.repositories.TransfertFacadeLocal;
import com.miage.spacelib.repositories.UsagerFacadeLocal;
import com.miage.spacelib.repositories.VoyageFacadeLocal;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionVoyage implements GestionVoyageLocal {
    
    @EJB
    private UsagerFacadeLocal usagerFacade;
    
    @EJB
    private VoyageFacadeLocal voyageFacade;
    
    @EJB
    private TransfertFacadeLocal transfertFacade;
    
    @EJB
    private RevisionFacadeLocal revisionFacade;
    
    @EJB
    private QuaiFacadeLocal quaiFacade;
    
    @EJB
    private StationFacadeLocal stationFacade;
    
    @EJB
    private TempsTrajetFacadeLocal tpstrajetFacade;
    
    @EJB
    private NavetteFacadeLocal navetteFacade;

    @Override
    public void annulerVoyage(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
    }

    @Override
    public Voyage voyageEnCours(Long idUsager) throws UsagerInconnuException {
        return null;
    }

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException {
    }

    @Override
    public Voyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws InvocationTargetException, IllegalAccessException, StationsIdentiquesException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        
        final Usager usager = this.usagerFacade.find(idUsager);
        if(usager == null){
            throw new UsagerInconnuException("Ce compte de mécanicien n'existe pas.");
        }

        if(idStationDepart == idStationArrivee){
            System.out.println("BUG");
            throw new StationsIdentiquesException("Les stations de départ et d'arrivée ne peuvent pas être identiques.");
        } 
        
        final Station stationDepart = this.stationFacade.find(idStationDepart);
        if(stationDepart == null){
            throw new StationInconnuException("La station de départ n'existe pas.");
        }
        
        final Station stationArrive = this.stationFacade.find(idStationArrivee);
        if(stationArrive == null){
            throw new StationInconnuException("La station d'arrivée n'existe pas.");
        }
        
        final TempsTrajet tpsTrajet = this.tpstrajetFacade.findByStations(stationDepart, stationArrive);
        if(tpsTrajet == null){
            throw new TempsTrajetInconnuException("Le temps de trajet n'existe pas entre ces deux stations.");
        }
        
        Calendar dateArrivee = dateDepart;
        dateArrivee.add(Calendar.DATE, tpsTrajet.getTemps());
          
        List<Quai> quaisStationDepart = this.quaiFacade.recupererListeQuaisParStation(stationDepart);
        for(Quai q : quaisStationDepart){
            List<Voyage> voyages = this.voyageFacade.findVoyagesArriveeADateEtQuai(dateDepart, q);
            //List<Transfert> transferts = this.transfertFacade.verifierSiInexistantEntreDatesSurQuai(dateDepart, dateArrivee, q);
            System.out.println("Quai "+ q.getId());
            for(Voyage v : voyages){
                System.out.println("Voyage : " +v.getId());
                System.out.println("Depart : " +v.getDateDepart());
                System.out.println("Arrivée : " +v.getDateArrivee());
                System.out.println("Quai Voyage : " +v.getQuaiDepart());
            }
        }
        
        
        
        
        /*
        
        List<Navette> navettes = this.navetteFacade.findAll();
        for(Navette n : navettes){
            
            // d'abord on regarde si elle est arrimée à un des quais de la stations de départ et si elle le restera jusqu'au départ de l'usager
     
            
            
        }
        
        
        
        
        
                // Trouvez la navette disponible au départ 
            // Vérifier combien il y a d'autres voyages prévus avant la date de départ
            // Voir si le nombre de voyage avant révision > 0
        
            verifierSiTransfertPrevuDate();
            verifierSiVoyagePrevuDate();
            
        
        // Trouvez les quais de départ et d'arrivée DISPONIBLES
        final Quai quaiDepart = stationDepart.findQuaiDisponible(dateDepart);
        final Quai quaiDepart = stationArrive.findQuaiDisponible(dateArrivee);
        
        
        
        
        
        Voyage voyage = this.voyageFacade.creerVoyage(navette, usager, quaiDepart, quaiArrive, NbPassagers, dateDepart, dateArrivee);
       
        
        
//creerVoyage(Navette navette, Usager usager, Quai quaiDepart, Quai quaiArrive, int NbPassagers, Calendar dateDepart, Calendar dateArrivee);
    */
            
            return  null;
        
    }
    
    

}
