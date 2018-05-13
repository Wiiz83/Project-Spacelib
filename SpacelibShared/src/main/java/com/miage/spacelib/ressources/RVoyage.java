package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class RVoyage extends RReservation implements Serializable {
    
    public static final String statutDebutTransfert = "transfert initié";
    public static final String statutFinTransfert = "transfert achevé";
    
    private RUsager usager;
    
    public RVoyage(){
        
    }
    
    public RVoyage(int nb, RNavette n, String s, RUsager u, Calendar dd, Calendar da, RQuai qd, RQuai qa){
        super(nb, n, s, dd, da, qd, qa);
        this.usager = u;
    }

    public RUsager getUsager() {
        return usager;
    }

    public void setUsager(RUsager usager) {
        this.usager = usager;
    }

    
    
    
}
