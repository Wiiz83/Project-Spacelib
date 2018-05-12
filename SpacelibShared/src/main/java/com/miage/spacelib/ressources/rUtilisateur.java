package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public class rUtilisateur implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String login;

    private String motdepasse;

    private Calendar dateCreation;
    
    public rUtilisateur(){
        
    }
    
    public rUtilisateur(String n, String p, String l, String m){
        this.nom = n;
        this.prenom = p;
        this.login = l;
        this.motdepasse = m;
        this.dateCreation = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
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
        if (!(object instanceof rUtilisateur)) {
            return false;
        }
        rUtilisateur other = (rUtilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Utilisateur[ id=" + id + " ]";
    }
    
}
