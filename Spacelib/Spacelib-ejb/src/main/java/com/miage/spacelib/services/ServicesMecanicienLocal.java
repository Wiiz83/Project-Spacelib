package com.miage.spacelib.services;

import com.miage.spacelib.entities.Mecanicien;
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
import javax.ejb.Local;

@Local
public interface ServicesMecanicienLocal {
    
    public void authentifier(String login, String motdepasse) throws MecanicienInconnuException;
    
    public long renseignerStationRattachement(String nom) throws StationInconnuException;
    
    public List<Navette> consulterListeNavettes(long idStation) throws StationInconnuException;

    public Quai choisirNavetteDebutRevision(long idNavette, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException;
    
    public Revision consulterRevisionEnCours(long idMecanicien) throws NavetteInconnuException;
    
    public void finirRevisionEnCours(long idRevision) throws RevisionInconnuException;
    
    public List<Station> recupererListeStations();
    
    public void authentifierAvecStationRattachement(String login, String motdepasse, long idStation) throws MecanicienInconnuException, StationInconnuException;
 
    
}
