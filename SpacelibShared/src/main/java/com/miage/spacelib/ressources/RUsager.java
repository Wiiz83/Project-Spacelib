package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;

public class RUsager extends RUtilisateur implements Serializable {

    private List<RVoyage> voyages;

    public RUsager(){
        
    }
    
    public RUsager(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<RVoyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<RVoyage> voyages) {
        this.voyages = voyages;
    }

    
    
}
