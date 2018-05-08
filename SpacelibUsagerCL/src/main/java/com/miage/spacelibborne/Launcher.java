/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibborne;

import com.miage.spacelib.services.ServicesUsagerRemote;
import javax.naming.NamingException;

/**
 *
 * @author Mahdi
 */
public class Launcher {
     public static void main(String[] args){
        try{
            RMIUsagerServiceManager rmiMgr = new RMIUsagerServiceManager();
            ServicesUsagerRemote serviceUsager = rmiMgr.getUsagerRemoteSvc() ; 
          //  serviceUsager.creerStation(new Long(3));
        }catch(NamingException ex){
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
     }        
}
