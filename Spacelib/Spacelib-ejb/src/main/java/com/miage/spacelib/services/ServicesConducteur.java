package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionConducteurLocal;
import com.miage.spacelib.business.GestionTransfertLocal;
import com.miage.spacelib.business.equilibrage.Equilibrage;
import com.miage.spacelib.business.equilibrage.EquilibrageResultat;
import com.miage.spacelib.business.equilibrage.InfoStation;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import javax.ejb.Stateless;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RStatsStation;
import com.miage.spacelib.ressources.RTransfert;
import com.miage.spacelib.ressources.RTransfertNecessaire;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.ejb.EJB;

@Stateless
public class ServicesConducteur implements ServicesConducteurLocal, ServicesConducteurRemote {

    @EJB
    private GestionConducteurLocal gestionConducteur;

    @EJB
    private GestionTransfertLocal gestionTransfert;
    
    @EJB
    private StationFacadeLocal stationFacade;

    @Override
    public Long login(String login, String motdepasse) throws UsagerInconnuException {
        return this.gestionConducteur.login(login, motdepasse);
    }

    @Override
    public void creerCompte(String nom, String prenom, String login, String motdepasse) throws UtilisateurExistantException {
        this.gestionConducteur.creerCompte(nom, prenom, login, motdepasse);
    }
    
    @Override
    public List<RTransfertNecessaire> obtenirTransfertsNecessaires() {
        List<Station> stations = stationFacade.findAll();
        Map<Station, Integer> variations = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        stations.forEach((s) -> {
            variations.put(s,
                    this.stationFacade.nbNavetteEntrantes(s.getId(), cal)
                    - this.stationFacade.nbNavetteSortantes(s.getId(), cal)
            );
        });
        Map<Station, InfoStation> infos = new HashMap<>();
        stations.forEach((s) -> {
            infos.put(s,
                    new InfoStation(this.stationFacade.nbNavettes(s.getId()),
                             this.stationFacade.nbQuais(s.getId())
                    )
            );
        });
        Equilibrage eq = new Equilibrage(variations, infos);
        EquilibrageResultat eqr = eq.obtenirResultats();
        List<RTransfertNecessaire> dto = new ArrayList<>();
        List<Map.Entry<Station, Station>> res = eqr.listeTransferts();
        for (Map.Entry<Station, Station> entry : res) {
            dto.add (new RTransfertNecessaire (
                    entry.getKey().getId(),
                    entry.getValue().getId(),
                    entry.getKey().getNom(),
                    entry.getValue().getNom()
            ));
        }
        return dto;
    }
    
    
    @Override
    public List<RStatsStation> stats() {
        List<Station> stations = stationFacade.findAll();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        List<RStatsStation> res = new ArrayList<>();
        for (Station station : stations) {
            RStatsStation stat = new RStatsStation();
            stat.nbNavettesArrimees = filtrer(station.getQuais(), q -> q.getNavette() != null).size();
            stat.nbNavettesEntrantes10jours = this.stationFacade.nbNavetteEntrantes(station.getId(), cal);
            stat.nbNavettesSortantes10jours = this.stationFacade.nbNavetteSortantes(station.getId(), cal);
            stat.station = rs(station);
            res.add(stat);
        }
        return res;
    }

    private RStation rs(Station station) {
        RStation rstation = new RStation();
        rstation.setId(station.getId());
        rstation.setLocalisation(station.getLocalisation());
        rstation.setNbQuais(station.getNbQuais());
        rstation.setNom(station.getNom());
        return rstation;
    }

    private <E> ArrayList<E> filtrer(List<E> all, Predicate<E> filter) {
        return filtrer(all, filter, null);
    }

    private <E> ArrayList<E> filtrer(List<E> all, Predicate<E> filter, Comparator<E> c) {
        ArrayList<E> filtered = all.stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
        if (c == null) {
            return filtered;
        } else {
            filtered.sort(c);
        }
        return filtered;
    }

    @Override
     public RTransfert reserverTransfert(Long idConducteur, Long idStationDepart, Long idStationArrivee) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        Transfert transfert = this.gestionTransfert.reserverTransfert(idConducteur, idStationDepart, idStationArrivee);
        RTransfert rtransfert = new RTransfert(transfert.getId(), transfert.getNbPassagers(), transfert.getNavette().getId(), transfert.getStatut(), transfert.getConducteur().getId(), transfert.getDateCreation(), transfert.getDateDepart(), transfert.getDateArrivee(), transfert.getQuaiDepart().getId(), transfert.getQuaiArrivee().getId());
        return rtransfert; 
    }

    @Override
    public ArrayList<RTransfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException {
        ArrayList<Transfert> transferts = this.gestionTransfert.obtenirTransfertsConducteur(idConducteur);
        ArrayList<RTransfert> rtransferts = new ArrayList<>();
        for (Transfert transfert : transferts) {
            RTransfert rtransfert = new RTransfert(transfert.getId(), transfert.getNbPassagers(), transfert.getNavette().getId(), transfert.getStatut(), transfert.getConducteur().getId(), transfert.getDateCreation(), transfert.getDateDepart(), transfert.getDateArrivee(), transfert.getQuaiDepart().getId(), transfert.getQuaiArrivee().getId());
            rtransferts.add(rtransfert);
        }
        return rtransferts;
    }
    
    
    
    @Override
    public RTransfert transfertEnCours(Long idConducteur) throws  UsagerInconnuException, VoyageInconnuException {
        Transfert transfert = this.gestionTransfert.transfertEnCours(idConducteur);
        RTransfert rtransfert = new RTransfert();        
        rtransfert.setDateArrivee(transfert.getDateArrivee());
        rtransfert.setDateCreation(transfert.getDateCreation());
        rtransfert.setDateDepart(transfert.getDateDepart());
        rtransfert.setId(transfert.getId());
        rtransfert.setNavette(transfert.getNavette().getId());
        rtransfert.setNbPassagers(transfert.getNbPassagers());
        rtransfert.setQuaiArrivee(transfert.getQuaiArrivee().getId());
        rtransfert.setQuaiDepart(transfert.getQuaiDepart().getId());
        rtransfert.setStatut(transfert.getStatut());
        rtransfert.setConducteur(transfert.getConducteur().getId());
        return rtransfert;
    }

    @Override
    public void finaliserVoyage(Long idTransfert) throws VoyageInconnuException {
        this.gestionTransfert.finaliserTransfert(idTransfert);
    }

}
