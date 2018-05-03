package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Reservation implements Serializable {
    
    public static final String statutDebutReservation = "voyage initié";
    public static final String statutFinReservation = "voyage achevé";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Navette navette;
    
    @Column(nullable = false)
    private int nbPassagers;
    
    @OneToOne(mappedBy="reservation")
    private Quai quaiDepart;
    
    @OneToOne(mappedBy="reservation")
    private Quai quaiArrivee;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDepart;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateArrivee;
    
    @Column(nullable = false)
    private String statut;

    public Reservation(){
        
    }
    
    public Reservation(int nb, Navette n, Calendar dd, Calendar da, Quai qd, Quai qa){
        this.nbPassagers = nb;
        this.navette = n;
        this.dateCreation = Calendar.getInstance();
        this.dateDepart = dd;
        this.dateArrivee = da;
        this.quaiArrivee = qa;
        this.quaiDepart = qd;
        this.statut = statutDebutReservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
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

    public Quai getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(Quai quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public Quai getQuaiArrivee() {
        return quaiArrivee;
    }

    public void setQuaiArrivee(Quai quaiArrivee) {
        this.quaiArrivee = quaiArrivee;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Navette getNavette() {
        return navette;
    }

    public void setNavette(Navette navette) {
        this.navette = navette;
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
