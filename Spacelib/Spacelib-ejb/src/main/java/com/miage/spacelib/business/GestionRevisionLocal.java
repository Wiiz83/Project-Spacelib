package com.miage.spacelib.business;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.NavetteInconnuException;
import com.miage.spacelib.exceptions.NavettePourQuaiInexistantException;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.RevisionInconnuException;
import com.miage.spacelib.exceptions.RevisionInexistanteException;
import com.miage.spacelib.exceptions.StationInconnuException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GestionRevisionLocal {
    
    public Quai choisirNavetteDebutRevision(long idNavette, long idStation, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException;
    
    public Revision consulterRevisionEnCours(long idMecanicien, long idStation) throws NavetteInconnuException, QuaiInexistantException, RevisionInexistanteException;
    
    public void finirRevisionEnCours(long idNavette, long idStation, long idMecanicien) throws QuaiInconnuException, NavetteInconnuException, MecanicienInconnuException;
    
    public List<Revision> recupererListeNavettesAReviser(long idStation) throws StationInconnuException, QuaiInexistantException, NavettePourQuaiInexistantException, RevisionInexistanteException;
    
}
