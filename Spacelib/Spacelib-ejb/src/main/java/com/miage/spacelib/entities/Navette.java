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

@Entity
public class Navette implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public enum NavetteStatut {Disponible,ARéviser,EnRévision,EnVol}
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NavetteStatut statut;
    
    // 2, 5, 10 ou 15 places
    @Column(nullable = false)
    private int nbPlaces;
    
    @Column(nullable = false)
    private int nbVoyages;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private List<Reservation> reservations;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private List<Revision> revisions;
    
    // TODO : A voir si on laisse ?
    @OneToOne(mappedBy="navette")
    private Quai quai;
    
    public Navette(){
        
    }
    
    public Navette(int nb, String s){
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
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
