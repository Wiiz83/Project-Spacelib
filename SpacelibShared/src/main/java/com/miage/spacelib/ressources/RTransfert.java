package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class RTransfert extends RReservation implements Serializable  {
    
    public static final String statutDebutVoyage = "voyage initié";
    public static final String statutFinVoyage = "voyage achevé";
    
    private RConducteur conducteur;
    
    public RTransfert(){
        
    }
    
    public RTransfert(int nb, RNavette n, String s, RConducteur c, Calendar dd, Calendar da, RQuai qd, RQuai qa){
        super(nb, n, s, dd, da, qd, qa);
        this.conducteur = c;
    }

    public RConducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(RConducteur conducteur) {
        this.conducteur = conducteur;
    }
}
