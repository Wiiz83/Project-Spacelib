package com.miage.spacelib.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CONDUCTEUR")
public class Conducteur extends Utilisateur {

    private static final long serialVersionUID = 1L;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conducteur")
    private List<Transfert> transferts;

    public Conducteur(){
        
    }
    
    public Conducteur(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<Transfert> getTransferts() {
        return transferts;
    }

    public void setTransferts(List<Transfert> transferts) {
        this.transferts = transferts;
    }

}
