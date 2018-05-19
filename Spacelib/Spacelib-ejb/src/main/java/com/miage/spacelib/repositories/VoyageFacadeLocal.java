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
import java.util.ArrayList;
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

    public Voyage findPlusProcheVoyageArriveADateEtQuai(Calendar dateDepart, Quai q);

    public Voyage findPlusProcheVoyageDepartDeNavetteADateEtQuai(Calendar dateDepart, Quai q, Navette n);

    public List<Voyage> findAllVoyagesPrevusByUsager(Usager usager);

    public Voyage findVoyageEnCoursUsager(Long idUsager);

    public boolean verifierSiAutresVoyagesPrevusSurNavetteADate(Calendar dateDepart, Navette n);

    public boolean verifierSiNavettePossedeDepartVoyageAvantDate(Calendar Cdate, Navette n);

    public Voyage findVoyageArriveeJourDateEtQuai(Calendar dateDepart, Quai q);

    public Voyage findVoyageDepartJourDateEtQuai(Calendar dateDepart, Quai q);
    
    public boolean verifierSiVoyagePasse(Long IdVoyage);
    
    
}
