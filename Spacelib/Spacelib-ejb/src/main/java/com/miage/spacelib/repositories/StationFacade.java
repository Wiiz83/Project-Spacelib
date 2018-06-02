/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Voyage;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author uzanl
 */
@Stateless
public class StationFacade extends AbstractFacade<Station> implements StationFacadeLocal {

    @PersistenceContext(unitName = "SpacelibPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StationFacade() {
        super(Station.class);
    }

    @Override
    public int nbNavetteSortantes(Long idStation, Calendar date_sup) {
        Query query = getEntityManager().createNativeQuery(
                "SELECT COUNT(*) "
                + "FROM TRANSFERT, VOYAGE, QUAI "
                + "WHERE QUAI.ID_STATION=?1  AND ("
                + "(TRANSFERT.ID_QUAI_DEPART=QUAI.ID AND TRANSFERT.DATE_DEPART <= ?2 AND TRANSFERT.DATE_DEPART > CURRENT_DATE) "
                + "OR  (VOYAGE.ID_QUAI_DEPART=QUAI.ID AND VOYAGE.DATE_DEPART <= ?2 AND VOYAGE.DATE_DEPART > CURRENT_DATE ) )"
        ).setParameter(1, idStation)
                .setParameter(2, date_sup, TemporalType.DATE);
        return (int) (query.getSingleResult());
    }

    @Override
    public int nbNavetteEntrantes(Long idStation, Calendar date_sup) {
        Query query = getEntityManager().createNativeQuery(
                "SELECT COUNT(*) "
                + "FROM TRANSFERT, VOYAGE, QUAI "
                + "WHERE QUAI.ID_STATION=?1  AND ("
                + "(TRANSFERT.ID_QUAI_ARRIVE=QUAI.ID AND TRANSFERT.DATE_ARRIVEE  <= ?2 AND TRANSFERT.DATE_ARRIVEE  > CURRENT_DATE) "
                + "OR  (VOYAGE.ID_QUAI_ARRIVE=QUAI.ID AND VOYAGE.DATE_ARRIVEE  <= ?2 AND VOYAGE.DATE_ARRIVEE  > CURRENT_DATE ) )"
        ).setParameter(1, idStation)
                .setParameter(2, date_sup, TemporalType.DATE);
        return (int) (query.getSingleResult());
    }

    @Override
    public int nbNavettes(Long idStation) {
        Query query = getEntityManager().createNativeQuery(
                "SELECT COUNT(*) "
                + "FROM QUAI "
                + "WHERE QUAI.ID_STATION=?1 "
                + "AND ID_NAVETTE IS NOT NULL "
        );
        query.setParameter(1, idStation);
        return (int) (query.getSingleResult());
    }

    @Override
    public int nbQuais(Long idStation) {
        Query query = getEntityManager().createNativeQuery(
                "SELECT COUNT(*) "
                + "FROM QUAI "
                + "WHERE QUAI.ID_STATION=?1"
        );
        query.setParameter(1, idStation);
        return (int) (query.getSingleResult());
    }
}
