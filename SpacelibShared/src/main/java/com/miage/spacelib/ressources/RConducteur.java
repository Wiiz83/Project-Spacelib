package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.List;

public class RConducteur extends RUtilisateur implements Serializable {

    private List<RTransfert> transferts;

    public RConducteur(){
        
    }
    
    public RConducteur(String n, String p, String l, String m){
        super(n, p, l, m);
    }

    public List<RTransfert> getTransferts() {
        return transferts;
    }

    public void setTransferts(List<RTransfert> transferts) {
        this.transferts = transferts;
    }

}
