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
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.NavetteInconnuException;
import com.miage.spacelib.exceptions.NavettePourQuaiInexistantException;
import com.miage.spacelib.exceptions.QuaiInconnuException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.RevisionInconnuException;
import com.miage.spacelib.exceptions.RevisionInexistanteException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.repositories.MecanicienFacadeLocal;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import java.util.ArrayList;
import java.util.List;
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

    @EJB
    private StationFacadeLocal stationFacade;

    @Override
    public Quai choisirNavetteDebutRevision(long idNavette, long idStation, long idMecanicien) throws NavetteInconnuException, MecanicienInconnuException, QuaiInconnuException {

        final Mecanicien mecanicien = this.mecanicienFacade.find(idMecanicien);
        if (mecanicien == null) {
            throw new MecanicienInconnuException();
        }

        final Navette navette = this.navetteFacade.find(idNavette);
        if (navette == null) {
            throw new NavetteInconnuException();
        }

        final Quai quai = this.quaiFacade.findByNavette(navette);
        if (quai == null) {
            throw new QuaiInconnuException();
        }

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

    @Override
    public List<Revision> recupererListeNavettesAReviser(long idStation) throws StationInconnuException, QuaiInexistantException, NavettePourQuaiInexistantException, RevisionInexistanteException {

        List<Quai> quais = new ArrayList<Quai>();
        List<Revision> revisions = new ArrayList<Revision>();

        Station station = this.stationFacade.find(idStation);
        
        // on récupère les quai de la station 
        quais = this.quaiFacade.recupererListeQuaisParStation(station);

        if (quais.size() > 0) {
            for (Quai q : quais) {
                // on vérifie que le quai possède une navette en révision 
                if (q.getNavette() != null) {
                    // on récupère toutes les révisions avec le statut 'révision nécessaires' du quai
                    Revision derniereRevisionDuQuai = this.revisionFacade.recupererDerniereRevisionQuai(q);
                    System.out.println(derniereRevisionDuQuai.getId());
                    
                    
                    System.out.println(derniereRevisionDuQuai.getStatut()+" et " + Revision.statutRevisionNecessaire);
                    
                    
                    // si la dernière opération de révision est une révision nécessaire : 
                    if(new String(derniereRevisionDuQuai.getStatut()).equals(Revision.statutRevisionNecessaire)){
                        
                        System.out.println("passe1");
                        // et si la navette possède bien 0 voyage restant
                        if (derniereRevisionDuQuai.getNavette().getNbVoyages() == 0) {
                            System.out.println("passe2");
                            // alors c'est bien une révision nécessaire !
                            revisions.add(derniereRevisionDuQuai);
                        }
                    }
                }
            }
        } else {
            // Pas de quais dans cette station !
            throw new QuaiInexistantException("Il n'y a pas de quais dans cette station.");
        }
        if(revisions.size() > 0){
            return revisions;
        } else {
            throw new RevisionInexistanteException("Il n'y a pas de révisions nécessaires pour cette station.");
        }
    }

}
