/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface RevisionFacadeLocal {

    void create(Revision revision);

    void edit(Revision revision);

    void remove(Revision revision);

    Revision find(Object id);

    List<Revision> findAll();

    List<Revision> findRange(int[] range);

    int count();
    
    Revision nouveauDebutRevision(Mecanicien mecanicien, Navette navette, Quai quai);
    
    Revision nouveauFinRevision(Mecanicien mecanicien, Navette navette, Quai quai);
    
    Revision nouveauRevisionNecessaire(Navette navette, Quai quai);
    
}
