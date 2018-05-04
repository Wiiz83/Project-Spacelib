package com.miage.spacelib.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("REVISION")
@Table(name="REVISION")
public class Revision extends Operation {
    
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quai quai;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Mecanicien mecanicien;
    
    public Revision(){
        
    }
    
    public Revision(Navette n, String s, Mecanicien m, Quai q){
        super(n, s);
        this.mecanicien = m;
        this.quai = q;
    }

    public Mecanicien getMecanicien() {
        return mecanicien;
    }

    public void setMecanicien(Mecanicien mecanicien) {
        this.mecanicien = mecanicien;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setQuai(Quai quai) {
        this.quai = quai;
    }
    
    
}
