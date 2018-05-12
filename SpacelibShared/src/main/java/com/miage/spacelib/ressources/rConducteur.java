package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;

public class rConducteur extends rUtilisateur implements Serializable {

    private List<rTransfert> transferts;

    public rConducteur(){
        
    }
    
    public rConducteur(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<rTransfert> getTransferts() {
        return transferts;
    }

    public void setTransferts(List<rTransfert> transferts) {
        this.transferts = transferts;
    }

}
