/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Conducteur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface ConducteurFacadeLocal {

    void create(Conducteur conducteur);

    void edit(Conducteur conducteur);

    void remove(Conducteur conducteur);

    Conducteur find(Object id);

    List<Conducteur> findAll();

    List<Conducteur> findRange(int[] range);

    int count();
    
}
