package com.miage.spacelib.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Revision extends Operation {

    public static final String statutRevisionNecessaire = "révision nécessaire";
    public static final String statutDebutRevision = "début de révision";
    public static final String statutFinRevision = "fin de révision";
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_QUAI", nullable = false)
    private Quai quaiNavette;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MECANICIEN", nullable = true)
    private Mecanicien mecanicien;
    
    public Revision(){
        
    }
    
    public Revision(Navette n, String s, Quai q){
        super(n, s);
        this.quaiNavette = q;
    }
    
    public Revision(Navette n, String s, Mecanicien m, Quai q){
        this(n, s, q);
        this.mecanicien = m;
    }  

    public Quai getQuai() {
        return quaiNavette;
    }

    public void setQuai(Quai quaiNavette) {
        this.quaiNavette = quaiNavette;
    }
    
    
}
