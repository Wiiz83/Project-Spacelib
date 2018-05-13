package com.miage.spacelib.ressources;

import java.io.Serializable;

public class RRevision extends ROperation implements Serializable {

    public static final String statutRevisionNecessaire = "révision nécessaire";
    public static final String statutDebutRevision = "début de révision";
    public static final String statutFinRevision = "fin de révision";
    
    private RQuai quaiNavette;
    
    private RMecanicien mecanicien;
    
    public RRevision(){
        
    }
    
    public RRevision(RNavette n, String s, RQuai q){
        super(n, s);
        this.quaiNavette = q;
    }
    
    public RRevision(RNavette n, String s, RMecanicien m, RQuai q){
        this(n, s, q);
        this.mecanicien = m;
    }  

    public RQuai getQuai() {
        return quaiNavette;
    }

    public void setQuai(RQuai quaiNavette) {
        this.quaiNavette = quaiNavette;
    }
    
    
}
