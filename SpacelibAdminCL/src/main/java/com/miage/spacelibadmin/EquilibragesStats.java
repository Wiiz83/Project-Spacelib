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
public class EquilibragesStats {

    private final ServicesAdminRemote servicesAdmin;

    public EquilibragesStats(ServicesAdminRemote serviceAdmibn) {
        this.servicesAdmin = serviceAdmibn;
    }

    public void run() {
        List<RStatsStation> stats = this.servicesAdmin.stats();

        for (RStatsStation s : stats) {
            System.out.println("Station : "
                    + s.station.getNom()
                    + " (" + s.nbNavettesArrimees
                    + "/" + s.station.getNbQuais() + ")"
                    + "  Dans 10 jours: +" + s.nbNavettesEntrantes10jours
                    + " | -" + s.nbNavettesSortantes10jours
                    + "  ==> " + (double) (s.nbNavettesArrimees + s.nbNavettesEntrantes10jours - s.nbNavettesSortantes10jours) / (double) s.station.getNbQuais()
            );
        }
        System.out.println("Equilibrages proposés:");
        List<Map.Entry<RStation, RStation>> eq = this.servicesAdmin.transfertsEquilibrage();

        for (Map.Entry<RStation, RStation> s : eq) {
            System.out.println(s.getKey().getNom()
                    + " --> "
                    + s.getValue().getNom()
            );
        }
    }

}
