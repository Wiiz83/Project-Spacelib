/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibadmin;

import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.services.ServicesAdminRemote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mahdi
 */
public class CLIAdmin {

    private final ServicesAdminRemote servicesAdmin;
    private final Scanner scanner = new Scanner(System.in);
    private final CLIUtils utils = new CLIUtils();

    public CLIAdmin(ServicesAdminRemote serviceAdmibn) {
        this.servicesAdmin = serviceAdmibn;
    }

    public void run() throws NombreNavettesInvalideException {
        System.out.println(ascii_spacelib);
        System.out.println("Création de station");
        List<RStation> stations = this.servicesAdmin.obtenirStations();
        
        String nomStation = utils.saisirChaine(scanner, "Nom de la station: ");
        String localisation = utils.saisirChaine(scanner, "Localisation: ");
        Long nb_quais = utils.saisirEntier(scanner, "Nombre de quais: ", new Long(0), Long.MAX_VALUE);
        Long nb_navettes = utils.saisirEntier(scanner, "Nombre de navettes: ", new Long(0), Long.MAX_VALUE);
        ArrayList<Long> capacites_possibles = new ArrayList<>();
        capacites_possibles.add(new Long(2));
        capacites_possibles.add(new Long(5));
        capacites_possibles.add(new Long(10));
        capacites_possibles.add(new Long(15));
        ArrayList<Integer> capacites = new ArrayList<>();

        for (int i = 0; i < nb_navettes; i++) {
            capacites.add((Integer) (int) (long) utils.saisirEntier(scanner, "Capcité navette " + (i + 1) + ": ", capacites_possibles));
        }
        Map<Long,Integer> tempsTrajets = new HashMap<>();
        stations.forEach((station) -> {
            tempsTrajets.put(station.getId(), (Integer) (int) (long) utils.saisirEntier(scanner, "Temps du trajet vers " + station.getNom() + ": ", new Long (0),Long.MAX_VALUE));
        });
        
        
        this.servicesAdmin.creerStation(nomStation, localisation, nb_quais, capacites,tempsTrajets);
        System.out.println("Succès.");
    }

    private final String ascii_spacelib
            = "   _____                           _  _  _     \n"
            + "  / ____|                         | |(_)| |    \n"
            + " | (___   _ __    __ _   ___  ___ | | _ | |__  \n"
            + "  \\___ \\ | '_ \\  / _` | / __|/ _ \\| || || '_ \\  \n"
            + "  ____) || |_) || (_| || (__|  __/| || || |_) |\n"
            + " |_____/ | .__/  \\__,_| \\___|\\___||_||_||_.__/ \n"
            + "         | |                                   \n"
            + "         |_|                \n";
}
