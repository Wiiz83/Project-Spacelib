package com.miage.spacelib.entities;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Voyage extends Reservation {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usager usager;
    
    public Voyage(){
        
    }
    
    public Voyage(int nb, Navette n, String s, Usager u, Calendar dd, Calendar da, Quai qd, Quai qa){
        super(nb, n, s, dd, da, qd, qa);
        this.usager = u;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    
    
    
}
