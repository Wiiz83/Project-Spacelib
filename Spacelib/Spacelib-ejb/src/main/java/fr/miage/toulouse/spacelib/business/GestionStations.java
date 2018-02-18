/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.spacelib.business;

import fr.miage.toulouse.spacelib.repositories.StationFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mahdi
 */
@Stateless
public class GestionStations implements GestionStationsLocal {

    @EJB(beanName = "StationFacadeLocal") 
    private StationFacadeLocal stationFacade;
    
    public void creerStation (Long id) {
        this.stationFacade.creerStation(id);
    }
}
