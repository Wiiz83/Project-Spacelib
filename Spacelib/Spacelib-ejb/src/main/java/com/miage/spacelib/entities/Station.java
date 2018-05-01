package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.List;
import javafx.util.Pair;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Station implements Serializable {
    
    public static final Pair<String, String> Terre = new Pair<>("sol", "d");
    public static final Pair<String, String> Dimidium = new Pair<>("pegasi", "b");
    public static final Pair<String, String> Arion = new Pair<>("delphini", "b");
    public static final Pair<String, String> Brahe = new Pair<>("cancri", "c");
    public static final Pair<String, String> Amateru = new Pair<>("epsilonTauri", "b");
    public static final Pair<String, String> Tadmor = new Pair<>("gammaCepheiA", "b");
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "station")
    private List<Quai> quais;
    
    @Column(nullable = false)
    private Pair<String, String> localisation;
    
    @Column(nullable = false)
    private int nbQuais;
    
    public enum StationStatut {Disponible,Complet}
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StationStatut statut;
    
    public Station(){
        
    }
    
    public Station(Pair<String, String> l, int nb){
        this.localisation = l;
        this.nbQuais = nb;
        this.statut = StationStatut.Disponible;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pair<String, String> getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Pair<String, String> localisation) {
        this.localisation = localisation;
    }

    public int getNbQuais() {
        return nbQuais;
    }

    public void setNbQuais(int nbQuais) {
        this.nbQuais = nbQuais;
    }

    public StationStatut getStatut() {
        return statut;
    }

    public void setStatut(StationStatut statut) {
        this.statut = statut;
    }
    
    

    public List<Quai> getQuais() {
        return quais;
    }

    public void setQuais(List<Quai> quais) {
        this.quais = quais;
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
