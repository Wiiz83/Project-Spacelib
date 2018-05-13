/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.TempsTrajet;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author uzanl
 */
@Stateless
public class TempsTrajetFacade extends AbstractFacade<TempsTrajet> implements TempsTrajetFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TempsTrajetFacade() {
        super(TempsTrajet.class);
    }

    @Override
    public TempsTrajet findByStations(Station sd, Station sa) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TempsTrajet> cq = cb.createQuery(TempsTrajet.class);
        Root<TempsTrajet> root = cq.from(TempsTrajet.class);
        
        cq.where(
                cb.and(
                        cb.equal(root.get("stationDepart"), sd),
                        cb.equal(root.get("stationArrivee"), sa)
                )
        );
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
}
