package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("RESERVATION")
@Table(name="RESERVATION")
public class Reservation extends Operation {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    private int nbPassagers;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Quai quaiDepart;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Quai quaiArrivee;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDepart;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateArrivee;

    public Reservation(){
        
    }
    
    public Reservation(int nb, Navette n, String s, Calendar dd, Calendar da, Quai qd, Quai qa){
        super(n, s);
        this.nbPassagers = nb;
        this.dateDepart = dd;
        this.dateArrivee = da;
        this.quaiArrivee = qa;
        this.quaiDepart = qd;
    }

    public Calendar getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Calendar dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Calendar getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Calendar dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Quai getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(Quai quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public Quai getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(Quai quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
    }
    
    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }
    
}
