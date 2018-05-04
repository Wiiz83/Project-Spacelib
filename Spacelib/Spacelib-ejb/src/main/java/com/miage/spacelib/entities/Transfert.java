package com.miage.spacelib.entities;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Transfert")
@Table(name="TRANSFERT")
public class Transfert extends Reservation {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "conducteurId")
    private Conducteur conducteur;
    
    public Transfert(){
        
    }
    
    public Transfert(int nb, Navette n, String s, Conducteur c, Calendar dd, Calendar da, Quai qd, Quai qa){
        super(nb, n, s, dd, da, qd, qa);
        this.conducteur = c;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        this.conducteur = conducteur;
    }

    
    
    
    
}
