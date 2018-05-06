package com.miage.spacelib.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Affectation extends Revision {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mecanicienId")
    private Mecanicien mecanicien;
    
    public Affectation(){
        
    }
    
    public Affectation(Navette n, String s, Mecanicien m, Quai q){
        super(n, s, q);
        this.mecanicien = m;
    }  
}
