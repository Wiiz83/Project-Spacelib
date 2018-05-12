package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class rTransfert extends rReservation implements Serializable  {
    
    public static final String statutDebutVoyage = "voyage initié";
    public static final String statutFinVoyage = "voyage achevé";
    
    private rConducteur conducteur;
    
    public rTransfert(){
        
    }
    
    public rTransfert(int nb, rNavette n, String s, rConducteur c, Calendar dd, Calendar da, rQuai qd, rQuai qa){
        super(nb, n, s, dd, da, qd, qa);
        this.conducteur = c;
    }

    public rConducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(rConducteur conducteur) {
        this.conducteur = conducteur;
    }
}
