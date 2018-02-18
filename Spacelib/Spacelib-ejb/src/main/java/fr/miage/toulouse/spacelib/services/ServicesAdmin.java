/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.spacelib.services;

import fr.miage.toulouse.spacelib.business.GestionStationsLocal;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author mahdi
 */

@Stateless
public class ServicesAdmin implements ServicesAdminRemote {

    @EJB(beanName = "GestionStationsLocal") 
    private GestionStationsLocal gestionStation;

    public void creerStation(Long id) {
        this.gestionStation.creerStation(id);
    }
}
