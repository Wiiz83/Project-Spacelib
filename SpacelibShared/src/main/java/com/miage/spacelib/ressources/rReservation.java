package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public abstract class rReservation extends rOperation implements Serializable {

    private int nbPassagers;
    
    private rQuai quaiDepart;
    
    private rQuai quaiArrivee;
    
    private Calendar dateDepart;
    
    private Calendar dateArrivee;

    public rReservation(){
        
    }
    
    public rReservation(int nb, rNavette n, String s, Calendar dd, Calendar da, rQuai qd, rQuai qa){
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

    public rQuai getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(rQuai quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public rQuai getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(rQuai quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
    }
    
    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }
    
}
