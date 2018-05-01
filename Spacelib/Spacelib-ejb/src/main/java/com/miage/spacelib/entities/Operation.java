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

@Entity
public class Operation implements Serializable {
    
    public static final String statutDébutRévision = "début de révision";
    public static final String statutFinRévision = "fin de révision";
    public static final String statutDébutRéservation = "voyage initié";
    public static final String statutFinRéservation = "voyage achevé";
    public static final String statutRévisionNécessaire = "révision nécessaire";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Navette navette;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCréation;
    
    @OneToOne(mappedBy="operation")
    private Revision revision;
    
    @OneToOne(mappedBy="operation")
    private Reservation reservation;
    
    @Column(nullable = false)
    private String statut;
    
    public Operation(){
        
    }
    
    public Operation(Navette n, Revision r, String s){
        this.navette = n;
        this.revision = r;
        this.dateCréation = Calendar.getInstance();
        this.statut = s;
    }
    
    public Operation(Navette n, Reservation r, String s){
        this.navette = n;
        this.reservation = r;
        this.dateCréation = Calendar.getInstance();
        this.statut = s;
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

    public Calendar getDateCréation() {
        return dateCréation;
    }

    public void setDateCréation(Calendar dateCréation) {
        this.dateCréation = dateCréation;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Operation[ id=" + id + " ]";
    }
    
}
