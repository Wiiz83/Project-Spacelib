/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author uzanl
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Navette navette;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Client client;
    
    @Column(nullable = false)
    private int nbPassagers;
    
    @OneToOne(mappedBy="reservation")
    private Station stationDepart;
    
    @OneToOne(mappedBy="reservation")
    private Station stationArrivée;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCréation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDepart;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateArrivée;
    
    public enum ReservationStatut {VoyageInitié,VoyageAchevé,VoyageAnnulé}
    @Column(nullable = false)
    private ReservationStatut statut;

    public Reservation(){
        
    }
    
    public Reservation(int nb, Navette n, Client c){
        this.nbPassagers = nb;
        this.client = c;
        this.navette = n;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Navette getNavette() {
        return navette;
    }

    public void setNavette(Navette navette) {
        this.navette = navette;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Reservation[ id=" + id + " ]";
    }
    
}
