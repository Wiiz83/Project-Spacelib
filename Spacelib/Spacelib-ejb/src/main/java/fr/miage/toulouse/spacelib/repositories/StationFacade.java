/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.spacelib.repositories;

import fr.miage.toulouse.spacelib.entities.Station;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author mahdi
 */
public class StationFacade  extends AbstractFacade<Station> implements StationFacadeLocal {
    
    @PersistenceContext(unitName = "Spacelib-PU")
    private EntityManager em;

    public StationFacade(Class<Station> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void creerStation(Long id) {
        Station  station = new Station(); station.setId(id);
        this.create(station); 
    }
    
}
