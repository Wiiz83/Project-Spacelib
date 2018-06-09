/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Usager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author uzanl
 */
@Stateless
public class UsagerFacade extends AbstractFacade<Usager> implements UsagerFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsagerFacade() {
        super(Usager.class);
    }

    @Override
    public Usager findByPrenomAndNom(String prenom, String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usager> cq = cb.createQuery(Usager.class);
        Root<Usager> root = cq.from(Usager.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }

    @Override
    public Usager findByLoginAndPassword(String login, String motdepasse) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Usager> cq = cb.createQuery(Usager.class);
            Root<Usager> root = cq.from(Usager.class);
            cq.where(
                    cb.and(
                            cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase()),
                            cb.equal(cb.upper(root.get("motdepasse").as(String.class)), motdepasse.toUpperCase())
                    )
            );
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Usager creerUsagerSiInexistant(String prenom, String nom, String login, String motdepasse) {
        try {
            return this.findByPrenomAndNomAndLogin(prenom, nom, login);
        } catch (NoResultException noRes) {
            Usager nouveauUsager = new Usager(nom, prenom, login, motdepasse);
            this.create(nouveauUsager);
            return nouveauUsager;
        }
    }

    @Override
    public Usager findByPrenomAndNomAndLogin(String prenom, String nom, String login) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Usager> cq = cb.createQuery(Usager.class);
            Root<Usager> root = cq.from(Usager.class);
            cq.where(
                    cb.and(
                            cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                            cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase()),
                            cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase())
                    )
            );
            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException noRes) {
            return null;
        }
    }

}
