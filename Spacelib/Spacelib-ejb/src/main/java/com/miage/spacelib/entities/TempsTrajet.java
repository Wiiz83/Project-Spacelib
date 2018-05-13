package com.miage.spacelib.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TEMPS_TRAJET")
public class TempsTrajet implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_STATION_DEPART", nullable = true)
    private Station stationDepart;
    
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_STATION_ARRIVEE", nullable = true)
    private Station stationArrivee;
    
    @Column(name="TEMPS", nullable = false)
    private int temps;
    
    public TempsTrajet(){
        
    }
    
    public TempsTrajet(Station sd, Station sa, int temps){
        this.stationDepart = sd;
        this.stationArrivee = sa;
        this.temps = temps;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Station getStationDepart() {
        return stationDepart;
    }

    public void setStationDepart(Station stationDepart) {
        this.stationDepart = stationDepart;
    }

    public Station getStationArrivee() {
        return stationArrivee;
    }

    public void setStationArrivee(Station stationArrivee) {
        this.stationArrivee = stationArrivee;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
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
        if (!(object instanceof TempsTrajet)) {
            return false;
        }
        TempsTrajet other = (TempsTrajet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.TempsTrajet[ id=" + id + " ]";
    }
    
}
