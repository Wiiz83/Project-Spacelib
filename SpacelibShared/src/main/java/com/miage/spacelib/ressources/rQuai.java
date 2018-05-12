package com.miage.spacelib.ressources;

import java.io.Serializable;

public class rQuai implements Serializable {

    private Long id;
    private rStation station;
    private rNavette navette;
    private enum QuaiStatut {Disponible,Occupe}
    private QuaiStatut statut;
    
    rQuai(rStation station){
        this.station = station;
        this.statut = QuaiStatut.Disponible;
    }
/*
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuaiStatut getStatut() {
        return statut;
    }

    public void setStatut(QuaiStatut statut) {
        this.statut = statut;
    }
    
    public rStation getStation() {
        return station;
    }

    public void setStation(rStation station) {
        this.station = station;
    }
    
    public rNavette getNavette() {
        return navette;
    }

    public void setNavette(rNavette navette) {
        this.navette = navette;
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
        if (!(object instanceof rQuai)) {
            return false;
        }
        rQuai other = (rQuai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Quai[ id=" + id + " ]";
    }
    */
}
