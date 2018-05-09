/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import java.util.Calendar;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class ServicesUsager implements ServicesUsagerLocal {

    @Override
    public boolean login(String nom, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void creeCompte(String nom, String prenom, String login, String motdepasse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voyage reserver(int nbPassagers, Quai quaiDepart, Quai quaiArrivee, Calendar dateDepart) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Voyage voyageEnCours(Usager usager) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finaliserVoyage(Voyage voyage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
