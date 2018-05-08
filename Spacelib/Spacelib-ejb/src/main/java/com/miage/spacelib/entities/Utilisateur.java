package com.miage.spacelib.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/*
Doc @MappedSuperclass :
https://jmdoudoux.developpez.com/cours/developpons/java/chap-jpa.php
https://docs.jboss.org/hibernate/annotations/3.4/reference/fr/html/entity.html
http://ecariou.perso.univ-pau.fr/cours/web/cours-JPA.pdf
https://docs.oracle.com/javaee/6/tutorial/doc/bnbqn.html

Exemples : 
https://www.concretepage.com/hibernate/example-mappedsuperclass-hibernate
https://docs.jboss.org/hibernate/jpa/2.1/api/javax/persistence/MappedSuperclass.html
*/

@MappedSuperclass
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="NOM", nullable = false)
    private String nom;

    @Column(name="PRENOM", nullable = false)
    private String prenom;
    
    @Column(name="LOGIN", nullable = false)
    private String login;

    @Column(name="MOT_DE_PASSE", nullable = false)
    private String motdepasse;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_CREATION", nullable = false)
    private Calendar dateCreation;
    
    public Utilisateur(){
        
    }
    
    public Utilisateur(String n, String p, String l, String m){
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
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
