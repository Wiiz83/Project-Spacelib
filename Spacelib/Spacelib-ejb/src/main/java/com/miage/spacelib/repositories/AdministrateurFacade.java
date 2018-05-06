package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Administrateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class AdministrateurFacade extends AbstractFacade<Administrateur> implements AdministrateurFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrateurFacade() {
        super(Administrateur.class);
    }

    @Override
    public Administrateur findByPrenomAndNom(String prenom, String nom) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Administrateur> cq = cb.createQuery(Administrateur.class);
        Root<Administrateur> root = cq.from(Administrateur.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("prenom").as(String.class)), prenom.toUpperCase()),
                        cb.equal(cb.upper(root.get("nom").as(String.class)), nom.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult(); 
    }

    @Override
    public Administrateur findByLoginAndPassword(String login, String motdepasse) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Administrateur> cq = cb.createQuery(Administrateur.class);
        Root<Administrateur> root = cq.from(Administrateur.class);
        cq.where(
                cb.and(
                        cb.equal(cb.upper(root.get("login").as(String.class)), login.toUpperCase()),
                        cb.equal(cb.upper(root.get("motdepasse").as(String.class)), motdepasse.toUpperCase())
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult(); 
    }

    @Override
    public Administrateur creerAdministrateurSiInexistant(String prenom, String nom, String login, String motdepasse) {
        try{
            return this.findByPrenomAndNomAndLogin(prenom, nom, login);
        }catch(NoResultException noRes){
            Administrateur nouveauAdmin = new Administrateur(nom, prenom, login, motdepasse);
            this.create(nouveauAdmin);
            return nouveauAdmin;
        }
    }

    @Override
    public Administrateur findByPrenomAndNomAndLogin(String prenom, String nom, String login) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Administrateur> cq = cb.createQuery(Administrateur.class);
        Root<Administrateur> root = cq.from(Administrateur.class);
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
