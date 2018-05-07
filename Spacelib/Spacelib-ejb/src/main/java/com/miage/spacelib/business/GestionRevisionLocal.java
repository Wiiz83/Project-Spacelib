package com.miage.spacelib.business;

import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.NavetteInconnuException;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.RevisionInconnuException;
import javax.ejb.Local;

@Local
public interface GestionRevisionLocal {
    
    public Quai choisirNavetteDebutRevision(long idNavette, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException;
    
    public Revision consulterRevisionEnCours(long idMecanicien) throws NavetteInconnuException;
    
    public void finirRevisionEnCours(long idRevision) throws RevisionInconnuException;
    
}
