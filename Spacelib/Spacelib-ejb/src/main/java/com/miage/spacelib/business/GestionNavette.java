/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionNavette implements GestionNavetteLocal {
    
    @EJB
    private NavetteFacadeLocal navetteFacade;

    @Override
    public List<Navette> consulterListeNavettes(long idStation) throws StationInconnuException {
        return null;
    }

}
