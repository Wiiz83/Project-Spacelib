/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Usager;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface UsagerFacadeLocal {

    void create(Usager usager);

    void edit(Usager usager);

    void remove(Usager usager);

    Usager find(Object id);

    List<Usager> findAll();

    List<Usager> findRange(int[] range);

    int count();
    
    Usager findByPrenomAndNom(String prenom, String nom);
    
    Usager findByLoginAndPassword(String login, String motdepasse);
    
    Usager findByPrenomAndNomAndLogin(String prenom, String nom, String login);
    
    Usager creerUsagerSiInexistant(String prenom, String nom, String login, String motdepasse);
    
}
