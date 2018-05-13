/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.TempsTrajet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface TempsTrajetFacadeLocal {

    void create(TempsTrajet tempsTrajet);

    void edit(TempsTrajet tempsTrajet);

    void remove(TempsTrajet tempsTrajet);

    TempsTrajet find(Object id);

    List<TempsTrajet> findAll();

    List<TempsTrajet> findRange(int[] range);

    int count();
    
    TempsTrajet findByStations(Station sd, Station sa);
    
}
