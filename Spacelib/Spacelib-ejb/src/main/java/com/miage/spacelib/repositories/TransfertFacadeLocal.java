/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.entities.Voyage;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface TransfertFacadeLocal {

    void create(Transfert transfert);

    void edit(Transfert transfert);

    void remove(Transfert transfert);

    Transfert find(Object id);

    List<Transfert> findAll();

    List<Transfert> findRange(int[] range);

    int count();
    
    public Transfert findPlusProcheTransfertArriveADateEtQuai(Calendar dateDepart,Quai q);
    
    public Transfert findPlusProcheTransfertDepartDeNavetteADateEtQuai(Calendar dateDepart, Quai q, Navette n);
    
    public boolean verifierSiAutresTransfertsPrevusSurNavetteADate(Calendar dateDepart, Navette n);
    
    public boolean verifierSiNavettePossedeDepartTransfertAvantDate(Calendar Cdate, Navette n);
    
    public Transfert findTransfertArriveeJourDateEtQuai(Calendar dateDepart, Quai q);
    
    public Transfert findTransfertDepartJourDateEtQuai(Calendar dateDepart, Quai q);
    
}
