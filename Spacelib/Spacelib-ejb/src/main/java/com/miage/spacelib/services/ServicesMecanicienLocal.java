package com.miage.spacelib.services;

import com.miage.spacelib.entities.Mecanicien;
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
import javax.ejb.Local;

@Local
public interface ServicesMecanicienLocal {
    
    public void authentifier(String login, String motdepasse) throws MecanicienInconnuException;
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse) throws UtilisateurExistantException;
    
    public long renseignerStationRattachement(String nom) throws StationInconnuException;
    
    public List<Navette> consulterListeNavettes(long idStation) throws StationInconnuException;

    public Quai choisirNavetteDebutRevision(long idNavette, long idStation, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException;
    
    public Revision consulterRevisionEnCours(long idMecanicien, long idStation) throws NavetteInconnuException, QuaiInexistantException, RevisionInexistanteException;
    
    public void finirRevisionEnCours(long idNavette, long idStation, long idMecanicien) throws QuaiInconnuException, NavetteInconnuException, MecanicienInconnuException;

    public List<Station> recupererListeStations();
    
    public List<Revision> recupererListeNavettesAReviser(long idStation) throws StationInconnuException, QuaiInexistantException, NavettePourQuaiInexistantException, RevisionInexistanteException;
    
    public long authentifierAvecStationRattachement(String login, String motdepasse, long idStation) throws MecanicienInconnuException, StationInconnuException;
 
    
}
