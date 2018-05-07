/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.repositories.MecanicienFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GestionMecanicien implements GestionMecanicienLocal {

    @EJB
    private MecanicienFacadeLocal mecanicienFacade;
    
    @EJB
    private StationFacadeLocal stationFacade;

    @Override
    public void authentifier(String login, String motdepasse) throws MecanicienInconnuException {
        final Mecanicien mecanicien = this.mecanicienFacade.findByLoginAndPassword(login, motdepasse);
        if(mecanicien == null) throw new MecanicienInconnuException("Ce compte de mécanicien n'existe pas.");
    }

    
    @Override
    public long renseignerStationRattachement(String nom) throws StationInconnuException {
        return 0;
    }
    
    @Override
    public long authentifierAvecStationRattachement(String login, String motdepasse, long idStation) throws MecanicienInconnuException, StationInconnuException {
        final Mecanicien mecanicien = this.mecanicienFacade.findByLoginAndPassword(login, motdepasse);
        if(mecanicien == null) throw new MecanicienInconnuException("Ce compte de mécanicien n'existe pas.");
        
        final Station station = this.stationFacade.find(idStation);
        if(station == null) throw new StationInconnuException("Cette station n'existe pas.");
        
        return mecanicien.getId();
    }
}
