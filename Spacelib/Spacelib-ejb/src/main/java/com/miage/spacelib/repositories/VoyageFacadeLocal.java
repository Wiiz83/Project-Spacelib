/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.repositories;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uzanl
 */
@Local
public interface VoyageFacadeLocal {

    void create(Voyage voyage);

    void edit(Voyage voyage);

    void remove(Voyage voyage);

    Voyage find(Object id);

    List<Voyage> findAll();

    List<Voyage> findRange(int[] range);

    int count();
    
    public Voyage creerVoyage(Navette navette, Usager usager, Quai quaiDepart, Quai quaiArrive, int NbPassagers, Calendar dateDepart, Calendar dateArrivee);
   
    public Voyage findPlusProcheVoyageArriveADateEtQuai(Calendar dateDepart,Quai q);
    
    public Voyage findPlusProcheVoyageDepartADateEtQuai(Calendar dateDepart, Quai q);
    
    public Voyage findVoyageDepartDeNavetteEntreDatesAQuai(Navette n, Calendar dArrivee, Quai q);
}
