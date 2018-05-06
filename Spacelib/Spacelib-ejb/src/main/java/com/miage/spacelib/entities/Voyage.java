package com.miage.spacelib.entities;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Voyage")
@Table(name="VOYAGE")
public class Voyage extends Reservation {
    
    public static final String statutDebutTransfert = "transfert initié";
    public static final String statutFinTransfert = "transfert achevé";
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USAGER", nullable = false)
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
