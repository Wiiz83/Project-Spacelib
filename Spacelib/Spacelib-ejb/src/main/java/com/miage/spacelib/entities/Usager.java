package com.miage.spacelib.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USAGER")
public class Usager extends Utilisateur {

    private static final long serialVersionUID = 1L;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usager")
    private List<Voyage> voyages;

    public Usager(){
        
    }
    
    public Usager(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    
    
}
