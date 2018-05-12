package com.miage.spacelib.ressources;

import java.io.Serializable;

public class rAdministrateur extends rUtilisateur implements Serializable {


    public rAdministrateur(){
        
    }
    
    public rAdministrateur(String n, String p, String l, String m){
        super(n, p, l, m);
    }
    
}
