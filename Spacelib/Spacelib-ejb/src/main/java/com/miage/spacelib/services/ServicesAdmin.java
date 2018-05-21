/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.business.GestionStationLocal;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.ressources.RNavette;
import com.miage.spacelib.ressources.RQuai;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mahdi
 */
@Stateless
public class ServicesAdmin implements ServicesAdminRemote {

    @EJB
    GestionStationLocal gestionStation;

    @Override
    public Long creerStation(String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes) throws NombreNavettesInvalideException {
        return this.gestionStation.creerStation(localisation, nom, nbquais, nbPlacesNavettes);
    }

}
