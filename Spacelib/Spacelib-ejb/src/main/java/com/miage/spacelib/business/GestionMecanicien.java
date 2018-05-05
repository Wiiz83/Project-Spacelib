/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.repositories.MecanicienFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionMecanicien implements GestionMecanicienLocal {

    @EJB
    private MecanicienFacadeLocal mecanicienFacade;
    
    @Override
    public List<Mecanicien> findAll() {
        return this.mecanicienFacade.findAll();
    }
}
