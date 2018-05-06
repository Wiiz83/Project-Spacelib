package com.miage.spacelib.entities;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Transfert")
@Table(name="TRANSFERT")
public class Transfert extends Reservation {
    
    public static final String statutDebutVoyage = "voyage initié";
    public static final String statutFinVoyage = "voyage achevé";
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CONDUCTEUR", nullable = false)
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
