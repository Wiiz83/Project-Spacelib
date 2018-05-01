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
    private List<Reservation> reservations;

    public Usager(){
        
    }
    
    public Usager(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
}
