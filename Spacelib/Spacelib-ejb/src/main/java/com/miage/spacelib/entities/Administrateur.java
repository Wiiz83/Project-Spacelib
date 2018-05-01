package com.miage.spacelib.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ADMINISTRATEUR")
public class Administrateur extends Utilisateur {

    private static final long serialVersionUID = 1L;

    public Administrateur(){
        
    }
    
    public Administrateur(String n, String p, String l, String m){
        super(n, p, l, m);
    }
    
}
