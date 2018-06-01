/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibborne;

import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.services.ServicesUsagerRemote;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            RMIUsagerServiceManager rmiMgr = new RMIUsagerServiceManager();
            ServicesUsagerRemote serviceUsager = rmiMgr.getUsagerRemoteSvc();
            try {
                (new CLIBorne(serviceUsager)).run();
            } catch (VoyageInconnuException | IllegalAccessException | InvocationTargetException | UsagerInconnuException | QuaiInexistantException | QuaiIndisponibleException | TempsTrajetInconnuException | StationInconnuException ex) {
                erreur(ex);

            }

        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
