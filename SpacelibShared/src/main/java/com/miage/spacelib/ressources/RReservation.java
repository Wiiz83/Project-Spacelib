package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public abstract class RReservation extends ROperation implements Serializable {

    private int nbPassagers;
    
    private RQuai quaiDepart;
    
    private RQuai quaiArrivee;
    
    private Calendar dateDepart;
    
    private Calendar dateArrivee;

    public RReservation(){
        
    }
    
    public RReservation(int nb, RNavette n, String s, Calendar dd, Calendar da, RQuai qd, RQuai qa){
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

    public RQuai getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(RQuai quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public RQuai getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(RQuai quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
    }
    
    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }
    
}
