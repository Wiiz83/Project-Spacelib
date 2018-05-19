package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionConducteurLocal;
import com.miage.spacelib.business.GestionTransfertLocal;
import com.miage.spacelib.entities.Transfert;
import javax.ejb.Stateless;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.ressources.RTransfert;
import java.util.ArrayList;
import javax.ejb.EJB;

@Stateless
public class ServicesConducteur implements ServicesConducteurLocal {

    @EJB
    private GestionConducteurLocal gestionConducteur;

    @EJB
    private GestionTransfertLocal gestionTransfert;

    @Override
    public Long login(String login, String motdepasse) throws UsagerInconnuException {
        return this.gestionConducteur.login(login, motdepasse);
    }

    @Override
    public void creerCompte(String nom, String prenom, String login, String motdepasse) {
        this.gestionConducteur.creerCompte(nom, prenom, login, motdepasse);
    }

    @Override
    public ArrayList<RTransfert> obtenirTransfertsNecessaires(Long idStationDepart, Long idStationArrivee) throws StationInconnuException {
        ArrayList<Transfert> transferts = this.gestionTransfert.obtenirTransfertsNecessaires(idStationDepart, idStationArrivee);
        ArrayList<RTransfert> rtransferts = new ArrayList<>();
        for (Transfert transfert : transferts) {
            RTransfert rtransfert = new RTransfert(transfert.getId(), transfert.getNbPassagers(), transfert.getNavette().getId(), transfert.getStatut(), transfert.getConducteur().getId(), transfert.getDateCreation(), transfert.getDateDepart(), transfert.getDateArrivee(), transfert.getQuaiDepart().getId(), transfert.getQuaiArrivee().getId());
            rtransferts.add(rtransfert);
        }
        return rtransferts;
    }

    @Override
    public RTransfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException {
        Transfert transfert = this.gestionTransfert.reserverTransfert(idConducteur, idTransfert);
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

}
