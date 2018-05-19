package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class RTransfert implements Serializable {

    private Long conducteur;

    private int nbPassagers;

    private Long quaiDepart;

    private Long quaiArrivee;

    private Calendar dateDepart;

    private Calendar dateArrivee;

    private Long id;

    private Long navette;

    private Calendar dateCreation;

    private String statut;

    public RTransfert() {

    }

    public RTransfert(Long id, int nbPassagers, Long navette, String statut, Long conducteur, Calendar dateCreation, Calendar dateDepart, Calendar dateArrivee, Long quaiDepart, Long quaiArrivee) {
        this.conducteur = conducteur;
        this.dateArrivee = dateArrivee;
        this.dateCreation = dateCreation;
        this.dateDepart = dateDepart;
        this.id = id;
        this.navette = navette;
        this.nbPassagers = nbPassagers;
        this.quaiArrivee = quaiArrivee;
        this.quaiDepart = quaiDepart;
        this.statut = statut;
    }

    public Long getConducteur() {
        return conducteur;
    }

    public void setConducteur(Long conducteur) {
        this.conducteur = conducteur;
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
