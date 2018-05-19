package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class RVoyage implements Serializable {
    
    public static final String statutDebutVoyage = "voyage initié";
    public static final String statutFinVoyage = "voyage achevé";
    
    private Long usager;
    
    private int nbPassagers;
    
    private Long quaiDepart;
    
    private Long quaiArrivee;
    
    private Calendar dateDepart;
    
    private Calendar dateArrivee;
    
    private Long id;
    
    private Long navette;
    
    private Calendar dateCreation;

    private String statut;
    
    public RVoyage(){
        
    }
    /*
    public RVoyage(int nb, Long n, String s, Long u, Calendar dd, Calendar da, Long qd, Long qa){
        super(nb, n, s, dd, da, qd, qa);
        this.usager = u;
    }
*/

    public Long getUsager() {
        return usager;
    }

    public void setUsager(Long usager) {
        this.usager = usager;
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public Long getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(Long quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public Long getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(Long quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNavette() {
        return navette;
    }

    public void setNavette(Long navette) {
        this.navette = navette;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    
    
    
}
