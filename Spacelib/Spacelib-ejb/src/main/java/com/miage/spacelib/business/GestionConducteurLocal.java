/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface GestionConducteurLocal {
    
    public Long login(String login, String motdepasse) throws UsagerInconnuException;
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse) throws UtilisateurExistantException ;
    
}
