/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author uzanl
 */
@Entity
public class Navette implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public enum NombrePlaces {P2,P5,P10,P15}
    
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private NombrePlaces nbPlaces;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private Reservation reservation;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private Revision revision;
    
    @OneToOne(mappedBy="navette")
    private Quai quai;
    
    public Navette(){
        
    }
    
    public Navette(NombrePlaces n){
        this.nbPlaces = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public NombrePlaces getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(NombrePlaces nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public Quai getQuai() {
        return quai;
    }

    public void setQuai(Quai quai) {
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
        if (!(object instanceof Navette)) {
            return false;
        }
        Navette other = (Navette) object;
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
