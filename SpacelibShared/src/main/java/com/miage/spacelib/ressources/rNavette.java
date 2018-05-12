package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;

public class rNavette implements Serializable {
    
    private Long id;
    
    private enum NavetteStatut {Disponible,AReviser,EnRevision,EnVol}
    private NavetteStatut statut;
    
    private rQuai quai;
    
    // 2, 5, 10 ou 15 places
    private int nbPlaces;
    
    private int nbVoyages;
   
    private List<rVoyage> voyages;
 
    private List<rTransfert> transferts;
    
    private List<rRevision> revisions;
    
    public rNavette(){
        
    }
    
    public rNavette(int nb, String s){
        this.nbPlaces = nb;
        this.nbVoyages = 0;
        this.statut = NavetteStatut.Disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NavetteStatut getStatut() {
        return statut;
    }

    public void setStatut(NavetteStatut statut) {
        this.statut = statut;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNbVoyages() {
        return nbVoyages;
    }

    public void setNbVoyages(int nbVoyages) {
        this.nbVoyages = nbVoyages;
    }

    public rQuai getQuai() {
        return quai;
    }

    public void setQuai(rQuai quai) {
        this.quai = quai;
    }   
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof rNavette)) {
            return false;
        }
        rNavette other = (rNavette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Navette[ id=" + id + " ]";
    }
    
}
