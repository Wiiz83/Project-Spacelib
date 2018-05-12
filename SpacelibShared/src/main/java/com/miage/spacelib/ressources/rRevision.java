package com.miage.spacelib.ressources;

import java.io.Serializable;

public class rRevision extends rOperation implements Serializable {

    public static final String statutRevisionNecessaire = "révision nécessaire";
    public static final String statutDebutRevision = "début de révision";
    public static final String statutFinRevision = "fin de révision";
    
    private rQuai quaiNavette;
    
    private rMecanicien mecanicien;
    
    public rRevision(){
        
    }
    
    public rRevision(rNavette n, String s, rQuai q){
        super(n, s);
        this.quaiNavette = q;
    }
    
    public rRevision(rNavette n, String s, rMecanicien m, rQuai q){
        this(n, s, q);
        this.mecanicien = m;
    }  

    public rQuai getQuai() {
        return quaiNavette;
    }

    public void setQuai(rQuai quaiNavette) {
        this.quaiNavette = quaiNavette;
    }
    
    
}
