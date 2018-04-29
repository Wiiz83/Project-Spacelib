/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author uzanl
 */
@Entity
public class Revision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy="revision")
    private Navette navette;
    
    @OneToOne(mappedBy="revision")
    private Mecanicien mecanicien;

    public Revision(){
        
    }
    
    public Revision(Navette n, Mecanicien m){
        this.mecanicien = m;
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

    public Mecanicien getMecanicien() {
        return mecanicien;
    }

    public void setMecanicien(Mecanicien mecanicien) {
        this.mecanicien = mecanicien;
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
        if (!(object instanceof Revision)) {
            return false;
        }
        Revision other = (Revision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Revision[ id=" + id + " ]";
    }
    
}
