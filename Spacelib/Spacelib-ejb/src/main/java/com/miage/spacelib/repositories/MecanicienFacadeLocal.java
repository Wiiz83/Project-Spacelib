/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Mecanicien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface MecanicienFacadeLocal {

    void create(Mecanicien mecanicien);

    void edit(Mecanicien mecanicien);

    void remove(Mecanicien mecanicien);

    Mecanicien find(Object id);

    List<Mecanicien> findAll();

    List<Mecanicien> findRange(int[] range);

    int count();
    
    Mecanicien findByPrenomAndNom(String prenom, String nom);
    
    Mecanicien findByLoginAndPassword(String login, String motdepasse);
    
    Mecanicien findByPrenomAndNomAndLogin(String prenom, String nom, String login);
    
    Mecanicien creerMecanicienSiInexistant(String prenom, String nom, String login, String motdepasse);
    
}
