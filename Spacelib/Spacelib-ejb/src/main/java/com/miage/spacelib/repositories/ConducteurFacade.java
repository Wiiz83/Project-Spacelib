/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Conducteur;
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
public class ConducteurFacade extends AbstractFacade<Conducteur> implements ConducteurFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConducteurFacade() {
        super(Conducteur.class);
    }

    @Override
    public Conducteur findByPrenomAndNom(String prenom, String nom) {
        try {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Conducteur> cq = cb.createQuery(Conducteur.class);
        Root<Conducteur> root = cq.from(Conducteur.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult(); 
        }catch(NoResultException noRes){
            return null;
        }
    }

    @Override
    public Conducteur findByLoginAndPassword(String login, String motdepasse) {
        try{
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Conducteur> cq = cb.createQuery(Conducteur.class);
            Root<Conducteur> root = cq.from(Conducteur.class);
            cq.where(
                    cb.and(
                            cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase()),
                            cb.equal(cb.upper(root.get("motdepasse").as(String.class)), motdepasse.toUpperCase())
                    )
            );
            return getEntityManager().createQuery(cq).getSingleResult(); 
        }catch(NoResultException noRes){
            return null;
        }
    }

    @Override
    public Conducteur creerConducteurSiInexistant(String prenom, String nom, String login, String motdepasse) {
        try{
            return this.findByPrenomAndNomAndLogin(prenom, nom, login);
        }catch(NoResultException noRes){
            Conducteur nouveauAdmin = new Conducteur(nom, prenom, login, motdepasse);
            this.create(nouveauAdmin);
            return nouveauAdmin;
        }
    }

    @Override
    public Conducteur findByPrenomAndNomAndLogin(String prenom, String nom, String login) {
        try{
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Conducteur> cq = cb.createQuery(Conducteur.class);
            Root<Conducteur> root = cq.from(Conducteur.class);
            cq.where(
                    cb.and(
                            cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                            cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase()),
                            cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase())
                    )
            );
            return getEntityManager().createQuery(cq).getSingleResult(); 
        }catch(NoResultException noRes){
            return null;
        }
    }
    
}
