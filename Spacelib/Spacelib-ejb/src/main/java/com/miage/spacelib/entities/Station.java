package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="STATION")
public class Station implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    private List<Quai> quais;
    
    @Column(name="LOCALISATION", nullable = false)
    private String localisation;
    
    @Column(name="NOMBRE_QUAIS", nullable = false)
    private int nbQuais;
    
    @Column(name="NOM", nullable = false)
    private String nom;
    
    public Station(){
        
    }
    
    public Station(String l, int nb, String n){
        this.localisation = l;
        this.nbQuais = nb;
        this.nom = n;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getNbQuais() {
        return nbQuais;
    }

    public void setNbQuais(int nbQuais) {
        this.nbQuais = nbQuais;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Quai> getQuais() {
        return quais;
    }

    public void setQuais(List<Quai> quais) {
        this.quais = quais;
    }
/*
    public TempsTrajet getTpsTrajet() {
        return tpsTrajet;
    }

    public void setTpsTrajet(TempsTrajet tpsTrajet) {
        this.tpsTrajet = tpsTrajet;
    }
    */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Station[ id=" + id + " ]";
    }
    
}
