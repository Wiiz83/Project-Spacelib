package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;
public class RMecanicien extends RUtilisateur implements Serializable {

    private List<RRevision> revisions;

    public RMecanicien(){
        
    }
    
    public RMecanicien(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<RRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<RRevision> revisions) {
        this.revisions = revisions;
    }
}
