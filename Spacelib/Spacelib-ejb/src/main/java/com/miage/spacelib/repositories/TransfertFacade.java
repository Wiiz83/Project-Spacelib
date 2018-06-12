/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Conducteur;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.entities.Voyage;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.naming.NamingException;
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
public class TransfertFacade extends AbstractFacade<Transfert> implements TransfertFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransfertFacade() {
        super(Transfert.class);
    }

    @Override
    public Transfert findPlusProcheTransfertArriveADateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date utilDate = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
            Root<Transfert> root = cq.from(Transfert.class);

            final ParameterExpression<Quai> quaiParameter = cb.parameter(Quai.class);
            final ParameterExpression<Date> dateArriveParameter = cb.parameter(Date.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiArrivee"), quaiParameter);
            final Path<Date> checkDateArrivePath = root.<Date>get("dateArrivee");

            final Predicate dateArrivePredicate = cb.lessThanOrEqualTo(checkDateArrivePath, dateArriveParameter);

            cq.where(cb.and(quaiPredicate, dateArrivePredicate));

            cq.orderBy(cb.asc(root.get("dateArrivee")));

            return getEntityManager().createQuery(cq)
                    .setParameter(quaiParameter, q)
                    .setParameter(dateArriveParameter, utilDate, TemporalType.DATE)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Transfert findPlusProcheTransfertDepartDeNavetteADateEtQuai(Calendar dateDepart, Quai q, Navette n) {
        try {
            java.util.Date utilDate = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
            Root<Transfert> root = cq.from(Transfert.class);

            final ParameterExpression<Quai> quaiParameter = cb.parameter(Quai.class);
            final ParameterExpression<Date> dateArriveParameter = cb.parameter(Date.class);
            final ParameterExpression<Navette> navetteParameter = cb.parameter(Navette.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiDepart"), quaiParameter);
            final Predicate navettePredicate = cb.equal(root.get("navette"), navetteParameter);
            final Predicate dateArrivePredicate = cb.greaterThanOrEqualTo(root.<Date>get("dateDepart"), dateArriveParameter);

            cq.where(cb.and(quaiPredicate, dateArrivePredicate, navettePredicate));
            cq.orderBy(cb.desc(root.get("dateDepart")));
            return getEntityManager().createQuery(cq)
                    .setParameter(quaiParameter, q)
                    .setParameter(navetteParameter, n)
                    .setParameter(dateArriveParameter, utilDate, TemporalType.DATE)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean verifierSiAutresTransfertsPrevusSurNavette(Calendar Cdate, Navette n) {
        try {
            boolean autresVoyages = false;
            java.util.Date date = Cdate.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
            Root<Transfert> root = cq.from(Transfert.class);

            final Predicate navettePredicate = cb.equal(root.get("navette"), n);
            final Predicate dateDebutPredicate = cb.greaterThanOrEqualTo(root.<Date>get("dateDepart"), date); // premier plus grand ou égal au second

            cq.where(cb.and(navettePredicate, dateDebutPredicate));

            if (getEntityManager().createQuery(cq).getResultList().size() > 0) {
                autresVoyages = true;
            }

            return autresVoyages;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Transfert findTransfertDepartJourDateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date date = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
            Root<Transfert> root = cq.from(Transfert.class);

            final Predicate quaiPredicate = cb.equal(root.get("quai"), q);
            final Predicate dateDebutPredicate = cb.equal(root.<Date>get("dateDepart"), date);

            cq.where(cb.and(quaiPredicate, dateDebutPredicate));

            return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Transfert findTransfertArriveeJourDateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date date = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
            Root<Transfert> root = cq.from(Transfert.class);

            final Predicate quaiPredicate = cb.equal(root.get("quai"), q);
            final Predicate dateDebutPredicate = cb.equal(root.<Date>get("dateArrivee"), date);

            cq.where(cb.and(quaiPredicate, dateDebutPredicate));

            return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean verifierSiNavettePossedeDepartTransfertAvantDate(Calendar Cdate, Navette n) {
        boolean autresVoyages = false;
        java.util.Date date = Cdate.getTime();

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
        Root<Transfert> root = cq.from(Transfert.class);

        final Predicate navettePredicate = cb.equal(root.get("navette"), n);
        final Predicate dateDebutPredicate = cb.lessThan(root.<Date>get("dateDepart"), date);

        cq.where(cb.and(navettePredicate, dateDebutPredicate));

        if (getEntityManager().createQuery(cq).getResultList().size() > 0) {
            autresVoyages = true;
        }

        return autresVoyages;
    }

    @Override
    public List<Transfert> findAllTransfertsPrevusByConducteur(Conducteur conducteur) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
        Root<Transfert> root = cq.from(Transfert.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("conducteur"), conducteur),
                        cb.equal(root.get("statut"), Transfert.statutDebutTransfert)
                )
        );
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    
    @Override
    public Transfert findTransfertEnCoursConducteur(Conducteur conducteur) {
        Date currDate = new Date();
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Transfert> cq = cb.createQuery(Transfert.class);
        Root<Transfert> root = cq.from(Transfert.class);
        cq.where(
                cb.and(
                        cb.equal(root.get("conducteur"), conducteur),
                        cb.equal(root.get("statut"), Transfert.statutDebutTransfert),
                        cb.equal(root.<Date>get("dateArrivee"), currDate)
                )
        );
        cq.orderBy(cb.asc(root.get("dateDepart")));
        Transfert res;
        try {
            res = getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            res = null;
        }
        return res;
    }

}
