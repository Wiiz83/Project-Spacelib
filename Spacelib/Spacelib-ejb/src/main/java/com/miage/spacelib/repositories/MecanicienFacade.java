/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Mecanicien;
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
public class MecanicienFacade extends AbstractFacade<Mecanicien> implements MecanicienFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MecanicienFacade() {
        super(Mecanicien.class);
    }

    @Override
    public Mecanicien findByPrenomAndNom(String prenom, String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mecanicien> cq = cb.createQuery(Mecanicien.class);
        Root<Mecanicien> root = cq.from(Mecanicien.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult(); 
    }

    @Override
    public Mecanicien findByLoginAndPassword(String login, String motdepasse) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mecanicien> cq = cb.createQuery(Mecanicien.class);
        Root<Mecanicien> root = cq.from(Mecanicien.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase()),
                        cb.equal(cb.upper(root.get("motdepasse").as(String.class)), motdepasse.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult(); 
    }

    @Override
    public Mecanicien creerMecanicienSiInexistant(String prenom, String nom, String login, String motdepasse) {
        try{
            return this.findByPrenomAndNomAndLogin(prenom, nom, login);
        }catch(NoResultException noRes){
            Mecanicien nouveauAdmin = new Mecanicien(nom, prenom, login, motdepasse);
            this.create(nouveauAdmin);
            return nouveauAdmin;
        }
    }

    @Override
    public Mecanicien findByPrenomAndNomAndLogin(String prenom, String nom, String login) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mecanicien> cq = cb.createQuery(Mecanicien.class);
        Root<Mecanicien> root = cq.from(Mecanicien.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase()),
                        cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult(); 
    }
    
}
