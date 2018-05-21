/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibadmin;

import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.services.ServicesAdminRemote;
import javax.naming.NamingException;

/**
 *
 * @author Mahdi
 */
public class Launcher {

    private static void erreur(String msg) {
        System.out.println("Erreur: " + msg);
    }

    private static void erreur(Exception ex) {
        erreur(ex.getMessage());
    }

    public static void main(String[] args) {
        try {
            RMIAdminServiceManager rmiMgr = new RMIAdminServiceManager();
            ServicesAdminRemote serviceUsager = rmiMgr.getAdminRemoteSvc();
            while (true) {
                try {
                    (new CLIAdmin(serviceUsager)).run();
                } catch ( NombreNavettesInvalideException ex) {
                    erreur(ex);
                }
            }

        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
