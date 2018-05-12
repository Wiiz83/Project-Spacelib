package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;

public class rUsager extends rUtilisateur implements Serializable {

    private List<rVoyage> voyages;

    public rUsager(){
        
    }
    
    public rUsager(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<rVoyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<rVoyage> voyages) {
        this.voyages = voyages;
    }

    
    
}
