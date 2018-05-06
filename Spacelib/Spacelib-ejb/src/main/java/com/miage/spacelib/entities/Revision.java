package com.miage.spacelib.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Revision extends Operation {

    public static final String statutRevisionNecessaire = "révision nécessaire";
    public static final String statutDebutRevision = "début de révision";
    public static final String statutFinRevision = "fin de révision";
    
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quai quaiNavette;
    
    public Revision(){
        
    }
    
    public Revision(Navette n, String s, Quai q){
        super(n, s);
        this.quaiNavette = q;
    }

    public Quai getQuai() {
        return quaiNavette;
    }

    public void setQuai(Quai quaiNavette) {
        this.quaiNavette = quaiNavette;
    }
    
    
}
