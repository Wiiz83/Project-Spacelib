/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author uzanl
 */
@Stateless
public class RevisionFacade extends AbstractFacade<Revision> implements RevisionFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RevisionFacade() {
        super(Revision.class);
    }

    @Override
    public Revision nouveauDebutRevision(Mecanicien mecanicien, Navette navette, Quai quai) {
        String statut = Revision.statutDebutRevision;
        Revision r = new Revision(navette, statut, mecanicien, quai);
        this.create(r);
        return r;
    }

    @Override
    public Revision nouveauFinRevision(Mecanicien mecanicien, Navette navette, Quai quai) {
        String statut = Revision.statutFinRevision;
        Revision r = new Revision(navette, statut, mecanicien, quai);
        this.create(r);
        return r;
    }

    @Override
    public Revision nouveauRevisionNecessaire(Navette navette, Quai quai) {
        String statut = Revision.statutRevisionNecessaire;
        Revision r = new Revision(navette, statut, quai);
        this.create(r);
        return r;
    }
    
}
