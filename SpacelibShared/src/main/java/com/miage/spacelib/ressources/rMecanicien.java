package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;
public class rMecanicien extends rUtilisateur implements Serializable {

    private List<rRevision> revisions;

    public rMecanicien(){
        
    }
    
    public rMecanicien(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<rRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<rRevision> revisions) {
        this.revisions = revisions;
    }
}
