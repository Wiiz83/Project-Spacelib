package com.miage.spacelib.entities;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Voyage extends Reservation {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conducteur conducteur;
    
    public Voyage(){
        
    }
    
    public Voyage(int nb, Navette n, Conducteur c, Calendar dd, Calendar da, Quai qd, Quai qa){
        super(nb, n, dd, da, qd, qa);
        this.conducteur = c;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        this.conducteur = conducteur;
    }
    
    
    
}
