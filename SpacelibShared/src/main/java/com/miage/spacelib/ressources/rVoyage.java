package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class rVoyage extends rReservation implements Serializable {
    
    public static final String statutDebutTransfert = "transfert initié";
    public static final String statutFinTransfert = "transfert achevé";
    
    private rUsager usager;
    
    public rVoyage(){
        
    }
    
    public rVoyage(int nb, rNavette n, String s, rUsager u, Calendar dd, Calendar da, rQuai qd, rQuai qa){
        super(nb, n, s, dd, da, qd, qa);
        this.usager = u;
    }

    public rUsager getUsager() {
        return usager;
    }

    public void setUsager(rUsager usager) {
        this.usager = usager;
    }

    
    
    
}
