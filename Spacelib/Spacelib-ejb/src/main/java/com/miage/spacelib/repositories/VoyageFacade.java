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
    public List<Voyage> findVoyagesArriveeADateEtQuai(Calendar dateDepart, Quai q) {
        try {
            java.util.Date utilDate = dateDepart.getTime();

            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);

            final ParameterExpression<Quai> quaiParameter = cb.parameter(Quai.class);
            final ParameterExpression<Date> dateDepartParameter = cb.parameter(Date.class);
            final ParameterExpression<Date> dateArriveParameter = cb.parameter(Date.class);

            final Predicate quaiPredicate = cb.equal(root.get("quaiDepart"), quaiParameter);
            
            final Path<Date> checkDateDepartPath = root.<Date>get("dateDepart");
            final Path<Date> checkDateArrivePath = root.<Date>get("dateArrivee");

            final Predicate dateDepartPredicate = cb.lessThan(checkDateDepartPath, dateDepartParameter);
            final Predicate dateArrivePredicate = cb.lessThan(checkDateArrivePath, dateArriveParameter);
            
            cq.where(cb.and(quaiPredicate, dateDepartPredicate, dateArrivePredicate));

            //cq.orderBy(Arrays.asList(cb.asc(checkDatePath)));

            return getEntityManager().createQuery(cq)
                    .setParameter(quaiParameter, q)
                    .setParameter(dateDepartParameter, utilDate, TemporalType.DATE)
                    .setParameter(dateArriveParameter, utilDate, TemporalType.DATE)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }

        /*
        try{
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Voyage> cq = cb.createQuery(Voyage.class);
            Root<Voyage> root = cq.from(Voyage.class);
            final ParameterExpression<Date> endDateParameter = cb.parameter(Date.class);
            Predicate startPredicate = cb.greaterThanOrEqualTo(root.<Date>get("inservicedate"), endDateParameter);
            cq.where(
                    cb.and(
                            cb.equal(root.get("quaiArrivee"), q),
                            cb.equal(root.get("dateArrivee"), dateDepart),
                            cb.lessThanOrEqualTo(root.get("dateArrivee"), dateDepart)
                    )
            );

            return getEntityManager().createQuery(cq).getResultList();
        } catch(NoResultException e) {
            return null;
        }
         */
    }

}
