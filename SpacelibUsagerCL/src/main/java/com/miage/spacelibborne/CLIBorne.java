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
import java.util.Scanner;
import com.miage.spacelib.ressources.RUsager;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahdi
 */
public class CLIBorne {

    private enum CHOIX_PROCESS {
        DEPART, ARRIVEE
    };

    private final ServicesUsagerRemote serviceUsager;
    private final Scanner scanner = new Scanner(System.in);
    private final CLIUtils utils = new CLIUtils();

    public CLIBorne(ServicesUsagerRemote serviceUsager) {
        this.serviceUsager = serviceUsager;
    }

    public void run() throws IllegalAccessException, InvocationTargetException, UsagerInconnuException {

        System.out.println("Spacelib - Borne");
        Long idStation = ChoisirStationCourante();
        Long usager = obtenirUsager();
        CHOIX_PROCESS process = obtenirProcess();
        if (process == CHOIX_PROCESS.DEPART) {
            depart(usager, idStation);
        } else {
            arrivée(usager, idStation);
        }

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
        Long idUsager = null;
        idUsager = this.serviceUsager.login(mdp, mdp);
        return idUsager;
    }

    private Long ChoisirStationCourante() throws IllegalAccessException, InvocationTargetException {
        ArrayList<RStation> stations = new ArrayList<>();
        stations = this.serviceUsager.obtenirStations();
        afficherListeStations(stations);
        return utils.saisirEntier(scanner, "Station de départ: ", getIDsStations(stations));
    }

    private Long obtenirUsager() throws UsagerInconnuException {
        boolean aUnCompte = utils.yesNoQuestion(scanner, "Avez vous un compte ?");
        if (aUnCompte) {
            return loginCompte();
        } else {
            return creerCompte();
        }

    }

    private Long loginCompte() throws UsagerInconnuException {
        String login = utils.saisirChaine(scanner, "Login: ");
        String mdp = utils.saisirChaine(scanner, "Mot de passe: ");
        Long usager = null;
        usager = verifierUsager(login, mdp);
        return usager;
    }

    private Long creerCompte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CHOIX_PROCESS obtenirProcess() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void depart(Long usager, Long idStation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void arrivée(Long usager, Long idStation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
