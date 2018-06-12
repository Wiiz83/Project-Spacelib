/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibborne;

import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.services.ServicesUsagerRemote;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Mahdi
 */
public class CLIUsager {

    private enum CHOIX_PROCESS {
        DEPART, ARRIVEE
    };
    private final ServicesUsagerRemote serviceUsager;
    private final Scanner scanner = new Scanner(System.in);
    private final CLIUtils utils = new CLIUtils();

    public CLIUsager(ServicesUsagerRemote serviceUsager) {

    }

    public void run(Long idUsager) {
        CHOIX_PROCESS process = obtenirProcess(idUsager);
        if (process == CLIBorne.CHOIX_PROCESS.DEPART) {
            depart(idUsager, idStationCourante, stationsArrivee(stations, idStationCourante));
        } else {
            arrivee(idUsager);
        }
    }

    private ArrayList<RStation> stationsArrivee(ArrayList<RStation> stations, Long idStationDepart) {
        ArrayList<RStation> stationsDepart = new ArrayList(stations);
        stationsDepart.removeIf((RStation station) -> Objects.equals(station.getId(), idStationDepart));
        return stationsDepart;
    }

    private void afficherListeStations(ArrayList<RStation> stations) {
        stations.forEach((station) -> {
            System.out.println(station.getId() + ". " + station.getNom());
        });
    }

    private ArrayList<Long> getIDsStations(ArrayList<RStation> stations) {
        ArrayList<Long> ids = new ArrayList<>();
        stations.forEach((station) -> {
            ids.add(station.getId());
        });
        return ids;
    }

    private Long verifierUsager(String login, String mdp) throws UsagerInconnuException {
        Long idUsager = this.serviceUsager.login(login, mdp);
        return idUsager;
    }

}
