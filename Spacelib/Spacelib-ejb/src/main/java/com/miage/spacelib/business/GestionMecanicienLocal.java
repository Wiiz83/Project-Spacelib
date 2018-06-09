/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.exceptions.MecanicienInconnuException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface GestionMecanicienLocal {
    
    
    public void authentifier(String login, String motdepasse) throws MecanicienInconnuException;
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse) throws UtilisateurExistantException ;    
    
    public long renseignerStationRattachement(String nom) throws StationInconnuException;
    
    public long authentifierAvecStationRattachement(String login, String motdepasse, long idStation) throws MecanicienInconnuException, StationInconnuException;
 
    
}
