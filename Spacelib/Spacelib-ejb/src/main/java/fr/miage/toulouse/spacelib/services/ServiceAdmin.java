/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.spacelib.services;
import fr.miage.toulouse.spacelib.entities.Station;
import fr.miage.toulouse.spacelib.repositories.StationFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mahdi
 */
@Stateless
public class ServiceAdmin implements ServiceAdminRemote {
@EJB
private StationFacade stationFacade ; 
    @Override
    public void creerStation(Long id) {
         stationFacade.create(new Station ());
     }
}
