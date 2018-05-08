/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface QuaiFacadeLocal {

    void create(Quai quai);

    void edit(Quai quai);

    void remove(Quai quai);

    Quai find(Object id);

    List<Quai> findAll();

    List<Quai> findRange(int[] range);

    int count();
    
    Quai findByNavette(Navette navette);
    
    List<Quai> recupererListeQuaisParStation(Station station);
    
}
