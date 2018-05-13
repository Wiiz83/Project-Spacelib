package com.miage.spacelib.ressources;

import java.io.Serializable;

public class RAdministrateur extends RUtilisateur implements Serializable {


    public RAdministrateur(){
        
    }
    
    public RAdministrateur(String n, String p, String l, String m){
        super(n, p, l, m);
    }
    
}
