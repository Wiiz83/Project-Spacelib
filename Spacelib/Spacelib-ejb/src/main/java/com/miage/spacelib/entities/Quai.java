package com.miage.spacelib.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

@Entity
public class Quai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_STATION", nullable = false)
    private Station station;
    
    //@OneToOne(optional = true, fetch = FetchType.LAZY)
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_NAVETTE", nullable = true)
    private Navette navette;
    
    public enum QuaiStatut {Disponible,Occupe}
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuaiStatut statut;

    public Quai(){
        
    }
    
    public Quai(Station station){
        this.station = station;
        this.statut = QuaiStatut.Disponible;
    }

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
    
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
    
    public Navette getNavette() {
        return navette;
    }

    public void setNavette(Navette navette) {
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
        if (!(object instanceof Quai)) {
            return false;
        }
        Quai other = (Quai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Quai[ id=" + id + " ]";
    }
    
}
