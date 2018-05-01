package com.miage.spacelib.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CONDUCTEUR")
public class Conducteur extends Utilisateur {

    private static final long serialVersionUID = 1L;

    public Conducteur(){
        
    }
    
    public Conducteur(String n, String p, String l, String m){
        super(n, p, l, m);
    }

}
