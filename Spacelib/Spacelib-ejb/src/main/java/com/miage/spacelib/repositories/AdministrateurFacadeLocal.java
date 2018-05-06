/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Administrateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface AdministrateurFacadeLocal {

    void create(Administrateur administrateur);

    void edit(Administrateur administrateur);

    void remove(Administrateur administrateur);

    Administrateur find(Object id);

    List<Administrateur> findAll();

    List<Administrateur> findRange(int[] range);

    int count();
    
    Administrateur findByPrenomAndNom(String prenom, String nom);
    
    Administrateur findByLoginAndPassword(String login, String motdepasse);
    
    Administrateur findByPrenomAndNomAndLogin(String prenom, String nom, String login);
    
    Administrateur creerAdministrateurSiInexistant(String prenom, String nom, String login, String motdepasse);
    
}
