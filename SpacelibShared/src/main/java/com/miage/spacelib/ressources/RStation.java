package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;

public class RStation implements Serializable {
   
    private Long id;
    
   // private List<RQuai> quais;

    private String localisation;
    
    private int nbQuais;

    private String nom;
    
    private String statut;
    
    public RStation(){
        
    }
    
    public RStation(String l, int nb, String n){
        this.localisation = l;
        this.nbQuais = nb;
        this.statut = "Disponible";
        this.nom = n;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getNbQuais() {
        return nbQuais;
    }

    public void setNbQuais(int nbQuais) {
        this.nbQuais = nbQuais;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
/*
    public List<RQuai> getQuais() {
        return quais;
    }

    public void setQuais(List<RQuai> quais) {
        this.quais = quais;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RStation)) {
            return false;
        }
        RStation other = (RStation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Station[ id=" + id + " ]";
    }
    
}
