/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class ServicesMecanicien implements ServicesMecanicienLocal {

    /**
     *
     * @return
     */
    @Override
    public String toto() {
        return "ça marche";
    }

    
    
    
}
