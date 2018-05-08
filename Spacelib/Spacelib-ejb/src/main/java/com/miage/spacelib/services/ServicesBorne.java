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
public class ServicesBorne implements ServicesUsagerRemote {

    @Override
    public boolean login(String nom, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
