package com.miage.spacelib.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MECANICIEN")
public class Mecanicien extends Utilisateur {

    private static final long serialVersionUID = 1L;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mecanicien")
    private List<Affectation> affectations;

    public Mecanicien(){
        
    }
    
    public Mecanicien(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<Affectation> getAffectations() {
        return affectations;
    }

    public void setAffectations(List<Affectation> affectations) {
        this.affectations = affectations;
    }
}
