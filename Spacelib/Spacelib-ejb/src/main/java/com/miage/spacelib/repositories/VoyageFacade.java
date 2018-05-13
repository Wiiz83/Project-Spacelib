/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author uzanl
 */
@Stateless
public class VoyageFacade extends AbstractFacade<Voyage> implements VoyageFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoyageFacade() {
        super(Voyage.class);
    }

    @Override
    public Voyage creerVoyage(Navette navette, Usager usager, Quai quaiDepart, Quai quaiArrive, int NbPassagers, Calendar dateDepart, Calendar dateArrivee) {
        Voyage nouveauVoyage = new Voyage(NbPassagers, navette, Voyage.statutDebutVoyage, usager, dateDepart, dateArrivee, quaiDepart, quaiArrive);
        this.create(nouveauVoyage);
        return nouveauVoyage;
    }

    @Override
    public Voyage findPlusProcheVoyageArriveADateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date utilDate = dateDepart.getTime();
            
            System.out.println("Date o " + dateDepart);
            System.out.println("Date a " + utilDate);
            System.out.println("Quai " + q);

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiArrivee"), q);
            final Predicate dateArrivePredicate = cb.lessThanOrEqualTo(root.<Date>get("dateArrivee"), utilDate);
            
            cq.where(cb.and(quaiPredicate, dateArrivePredicate));
            cq.orderBy(cb.asc(root.get("dateArrivee")));

            return getEntityManager().createQuery(cq)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("NOP");
            return null;
        }
    }
    
    @Override
    public Voyage findPlusProcheVoyageDepartADateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date utilDate = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final ParameterExpression<Quai> quaiParameter = cb.parameter(Quai.class);
            final ParameterExpression<Date> dateArriveParameter = cb.parameter(Date.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiDepart"), quaiParameter);
            final Path<Date> checkDateArrivePath = root.<Date>get("dateDepart");

            final Predicate dateArrivePredicate = cb.greaterThanOrEqualTo(checkDateArrivePath, dateArriveParameter);

            cq.where(cb.and(quaiPredicate, dateArrivePredicate));
            cq.orderBy(cb.desc(root.get("dateDepart")));
            
            return getEntityManager().createQuery(cq)
                    .setParameter(quaiParameter, q)
                    .setParameter(dateArriveParameter, utilDate, TemporalType.DATE)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    @Override
    public Voyage findVoyageDepartDeNavetteEntreDatesAQuai(Navette n, Calendar dArrivee, Quai q) {
        try {
            java.util.Date datePrecedenteArrivee = dArrivee.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final ParameterExpression<Quai> quaiParameter = cb.parameter(Quai.class);
            final ParameterExpression<Date> dateArriveParameter = cb.parameter(Date.class);
            
            final Predicate quaiPredicate = cb.equal(root.get("quaiDepart"), quaiParameter);
            final Path<Date> checkDateDepartPath = root.<Date>get("dateDepart");

            final Predicate dateArrivePredicate = cb.greaterThanOrEqualTo(checkDateDepartPath, dateArriveParameter);

            cq.where(cb.and(quaiPredicate, dateArrivePredicate));
            cq.orderBy(cb.desc(root.get("dateDepart")));
            
            return getEntityManager().createQuery(cq)
                    .setParameter(quaiParameter, q)
                    .setParameter(dateArriveParameter, datePrecedenteArrivee, TemporalType.DATE)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
