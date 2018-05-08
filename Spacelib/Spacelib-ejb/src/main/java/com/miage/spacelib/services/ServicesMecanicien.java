package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionMecanicienLocal;
import com.miage.spacelib.business.GestionNavetteLocal;
import com.miage.spacelib.business.GestionRevisionLocal;
import com.miage.spacelib.business.GestionStationLocal;
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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ServicesMecanicien implements ServicesMecanicienLocal {

    @EJB
    private GestionMecanicienLocal gestionMecanicien;
    
    @EJB
    private GestionNavetteLocal gestionNavette;
    
    @EJB
    private GestionRevisionLocal gestionRevision;
    
    @EJB
    private GestionStationLocal gestionStation;

    @Override
    public void authentifier(String login, String motdepasse) throws MecanicienInconnuException {
        this.gestionMecanicien.authentifier(login, motdepasse);
    }

    @Override
    public long renseignerStationRattachement(String nom) throws StationInconnuException {
        return this.gestionMecanicien.renseignerStationRattachement(nom);
    }

    @Override
    public List<Navette> consulterListeNavettes(long idStation) throws StationInconnuException {
        return this.gestionNavette.consulterListeNavettes(idStation);
    }

    @Override
    public Quai choisirNavetteDebutRevision(long idNavette, long idStation, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException {
        return this.gestionRevision.choisirNavetteDebutRevision(idNavette, idStation, idMecanicien);
    }

    @Override
    public Revision consulterRevisionEnCours(long idMecanicien, long idStation) throws NavetteInconnuException, QuaiInexistantException, RevisionInexistanteException {
        return this.gestionRevision.consulterRevisionEnCours(idMecanicien, idStation);
    }

    @Override
    public void finirRevisionEnCours(long idNavette, long idStation, long idMecanicien) throws QuaiInconnuException, NavetteInconnuException, MecanicienInconnuException {
        this.gestionRevision.finirRevisionEnCours(idNavette, idStation, idMecanicien);
    }

    @Override
    public List<Station> recupererListeStations() {
        return this.gestionStation.recupererListeStations();
    }

    @Override
    public long authentifierAvecStationRattachement(String login, String motdepasse, long idStation) throws MecanicienInconnuException, StationInconnuException {
        return this.gestionMecanicien.authentifierAvecStationRattachement(login, motdepasse, idStation);
    }

    @Override
    public List<Revision> recupererListeNavettesAReviser(long idStation) throws StationInconnuException, QuaiInexistantException, NavettePourQuaiInexistantException, RevisionInexistanteException {
        return this.gestionRevision.recupererListeNavettesAReviser(idStation);
    }
    

    
    
}
