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
public class Reservation implements Serializable {
    
    public static final String statutDébutRéservation = "voyage initié";
    public static final String statutFinRéservation = "voyage achevé";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Navette navette;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usager usager;
    
    @Column(nullable = false)
    private int nbPassagers;
    
    @OneToOne(mappedBy="reservation")
    private Quai quaiDepart;
    
    @OneToOne(mappedBy="reservation")
    private Quai quaiArrivée;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCréation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDepart;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateArrivée;
    
    @Column(nullable = false)
    private String statut;

    public Reservation(){
        
    }
    
    public Reservation(int nb, Navette n, Usager u, Calendar dd, Calendar da, Quai qd, Quai qa){
        this.nbPassagers = nb;
        this.usager = u;
        this.navette = n;
        this.dateCréation = Calendar.getInstance();
        this.dateDepart = dd;
        this.dateArrivée = da;
        this.quaiArrivée = qa;
        this.quaiDepart = qd;
        this.statut = statutDébutRéservation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDateCréation() {
        return dateCréation;
    }

    public void setDateCréation(Calendar dateCréation) {
        this.dateCréation = dateCréation;
    }

    public Calendar getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Calendar dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Calendar getDateArrivée() {
        return dateArrivée;
    }

    public void setDateArrivée(Calendar dateArrivée) {
        this.dateArrivée = dateArrivée;
    }

    public Quai getQuaiDepart() {
        return quaiDepart;
    }

    public void setQuaiDepart(Quai quaiDepart) {
        this.quaiDepart = quaiDepart;
    }

    public Quai getQuaiArrivée() {
        return quaiArrivée;
    }

    public void setQuaiArrivée(Quai quaiArrivée) {
        this.quaiArrivée = quaiArrivée;
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

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
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
