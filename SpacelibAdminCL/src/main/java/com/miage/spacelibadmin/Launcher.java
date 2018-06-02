/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibadmin;

import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.services.ServicesAdminRemote;
import java.util.ArrayList;
import java.util.Scanner;
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

    public static void main(String[] args) throws NombreNavettesInvalideException {
        try {
            RMIAdminServiceManager rmiMgr = new RMIAdminServiceManager();
            ServicesAdminRemote serviceAdmin = rmiMgr.getAdminRemoteSvc();
            System.err.println("Ajout de stations : 1 \nStats et équilibrages proposés: 2 ");
            CLIUtils utils = new CLIUtils();
            Scanner scanner = new Scanner(System.in);
            ArrayList<Long> choix = new ArrayList<>();
            choix.add(new Long(1));
            choix.add(new Long(2));
            Long c= utils.saisirEntier(scanner, "Choix: ", choix);
            if (c == 2) {
                (new EquilibragesStats(serviceAdmin)).run();
            } else {
                (new CLIAdmin(serviceAdmin)).run();
            }

        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
