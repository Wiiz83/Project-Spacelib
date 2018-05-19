/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface GestionUsagerLocal {
    
    public Long authentifier(String login, String motdepasse) throws UsagerInconnuException;
    
    public Long creerCompte(String nom, String prenom, String login, String motdepasse) ;    
    
}