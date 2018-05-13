package com.miage.spacelib.entities;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Reservation extends Operation {
    
    

    @Column(name="NOMBRE_PASSAGERS", nullable = false)
    private int nbPassagers;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_QUAI_DEPART", nullable = false)
    private Quai quaiDepart;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_QUAI_ARRIVE", nullable = false)
    private Quai quaiArrivee;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_DEPART", nullable = false)
    private Calendar dateDepart;
    
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_ARRIVEE", nullable = false)
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
