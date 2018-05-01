package com.miage.spacelib.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MECANICIEN")
public class Mecanicien extends Utilisateur {

    private static final long serialVersionUID = 1L;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mecanicien")
    private List<Revision> revisions;

    public Mecanicien(){
        
    }
    
    public Mecanicien(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }
}
