/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibadmin;

import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RStatsStation;
import com.miage.spacelib.services.ServicesAdminRemote;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.naming.NamingException;

/**
 *
 * @author Mahdi
 */
public class StatsStations {

    private final ServicesAdminRemote servicesAdmin;

    public StatsStations(ServicesAdminRemote serviceAdmibn) {
        this.servicesAdmin = serviceAdmibn;
    }

    public void run() {
      /*  List<RStatsStation> stats = this.servicesAdmin.stats();
       
        for (RStatsStation s : stats) {
            System.out.println("Station : "
                    + s.station.getNom()
                    + " Quais: " + s.station.getNbQuais()
                    + " Navettes: " + s.nbNavettesArrimees
                    + " Navettes sortantes (10 jours): " + s.nbNavettesSortantes10jours
                    + " Navettes entrantes (10 jours): " + s.nbNavettesSortantes10jours
            );
        }*/
      System.out.println("Equilibrages proposés:");
         List<Map.Entry<RStation, RStation>> eq = this.servicesAdmin.transfertsEquilibrage();
        
        for (Map.Entry<RStation, RStation> s : eq) {
            System.out.println(s.getKey().getNom()
                    + " --> "
                    + s.getValue().getNom()
            );
        }
    }


    public static void main(String[] args) {
        try {
            RMIAdminServiceManager rmiMgr = new RMIAdminServiceManager();
            ServicesAdminRemote serviceAdmin = rmiMgr.getAdminRemoteSvc();
            while (true) {
                    (new StatsStations(serviceAdmin)).run();               
            }

        } catch (NamingException ex) {
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
    }
}
