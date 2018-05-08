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
import com.miage.spacelib.exceptions.StationInconnuException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static javax.swing.UIManager.get;

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

    @Override
    public Revision recupererDerniereRevisionQuai(Quai quai) {
        try{
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Revision> cq = cb.createQuery(Revision.class);
            Root<Revision> root = cq.from(Revision.class);
            cq.where(
                    cb.and(
                            cb.equal(root.get("quaiNavette"), quai)
                    )
            );
            cq.orderBy(cb.desc(root.get("dateCreation")));

            return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();  
        } catch(NoResultException e) {
            return null;
        }
    }
    
}
