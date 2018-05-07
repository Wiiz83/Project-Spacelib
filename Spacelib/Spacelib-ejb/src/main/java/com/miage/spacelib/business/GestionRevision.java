/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.NavetteInconnuException;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.RevisionInconnuException;
import com.miage.spacelib.repositories.MecanicienFacadeLocal;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionRevision implements GestionRevisionLocal {
    
    @EJB
    private RevisionFacadeLocal revisionFacade;
    
    @EJB
    private NavetteFacadeLocal navetteFacade;
    
    @EJB
    private MecanicienFacadeLocal mecanicienFacade;
    
    @EJB
    private QuaiFacadeLocal quaiFacade;

    @Override
    public Quai choisirNavetteDebutRevision(long idNavette, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException {
        
        final Mecanicien mecanicien = this.mecanicienFacade.find(idMecanicien);
        if(mecanicien == null) throw new MecanicienInconnuException();
        
        final Navette navette = this.navetteFacade.find(idNavette);
        if(navette == null) throw new NavetteInconnuException();
        
        final Quai quai = this.quaiFacade.findByNavette(navette);
        if(quai == null) throw new QuaiInconnuException();
        
        this.revisionFacade.nouveauDebutRevision(mecanicien, navette, quai);
        
        return quai;
    }

    @Override
    public Revision consulterRevisionEnCours(long idMecanicien) throws NavetteInconnuException {
        return null;
    }

    @Override
    public void finirRevisionEnCours(long idRevision) throws RevisionInconnuException {

    }

    
}
