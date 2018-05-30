package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Navette implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(mappedBy = "navette", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Quai quai;
    
    // 2, 5, 10 ou 15 places
    @Column(nullable = false)
    private int nbPlaces;
    
    @Column(nullable = false)
    private int nbVoyages;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private List<Voyage> voyages;
 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private List<Transfert> transferts;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "navette")
    private List<Revision> revisions;
    
    public Navette(){
        
    }
    
    public Navette(int nb){
        this.nbPlaces = nb;
        this.nbVoyages = 3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @XmlTransient
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
