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
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RVoyage;
import com.miage.spacelib.services.ServicesUsagerRemote;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Objects;

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

    public void run() throws UsagerInconnuException, IllegalAccessException, InvocationTargetException, QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, StationInconnuException, VoyageInconnuException {
        System.out.println(ascii_spacelib);
        ArrayList<RStation> stations = this.serviceUsager.obtenirStations();
        Long idStationCourante = ChoisirStationCourante(stations);
        Long idUsager = obtenirUsager();
        CHOIX_PROCESS process = obtenirProcess();
        if (process == CHOIX_PROCESS.DEPART) {
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

    private Long ChoisirStationCourante(ArrayList<RStation> stations) throws IllegalAccessException, InvocationTargetException {
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
        Long usager = verifierUsager(login, mdp);
        return usager;
    }

    private Long creerCompte() {
        String nom = utils.saisirChaine(scanner, "Nom: ");
        String prenom = utils.saisirChaine(scanner, "Prenom: ");
        String login = utils.saisirChaine(scanner, "Login: ");
        String mdp = utils.saisirChaine(scanner, "Mot de passe: ");
        return this.serviceUsager.creerCompte(nom, prenom, login, mdp);
    }

    private CHOIX_PROCESS obtenirProcess() {
        ArrayList<Long> options = new ArrayList<>();
        options.add(new Long(1));
        options.add(new Long(2));
        System.out.println("1. Emprunter une navette");
        System.out.println("2. Finaliser un voyage");
        Long option = utils.saisirEntier(scanner, "Choissez une option: ", options);
        if (option == 1) {
            return CHOIX_PROCESS.DEPART;
        } else {
            return CHOIX_PROCESS.ARRIVEE;
        }
    }

    private void depart(Long usager, Long idStationDepart, ArrayList<RStation> stationsArrivee) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        Long nbPassagers = utils.saisirEntier(scanner, "Nombre de passagers: ", new Long(0), new Long(16));
        afficherListeStations(stationsArrivee);
        Long idStationArrivee = utils.saisirEntier(scanner, "Station de départ: ", getIDsStations(stationsArrivee));
        RVoyage voyage = this.serviceUsager.reserverVoyage(usager, idStationDepart, idStationArrivee, (int) (long) nbPassagers, Calendar.getInstance());
        System.out.println("Réservation réussie. Rendez vous au quai" + voyage.getQuaiArrivee());
    }

    private void arrivee(Long usager) throws UsagerInconnuException, VoyageInconnuException {
        RVoyage voyageEncours = this.serviceUsager.voyageEnCours(usager);
        if (utils.yesNoQuestion(scanner, "Finaliser le voyage en cours? ")) {
            this.serviceUsager.finaliserVoyage(voyageEncours.getId());
        }
    }
    
    
    private final String ascii_spacelib =
 "   _____                           _  _  _     \n"
+"  / ____|                         | |(_)| |    \n"
+" | (___   _ __    __ _   ___  ___ | | _ | |__  \n"
+"  \\___ \\ | '_ \\  / _` | / __|/ _ \\| || || '_ \\  \n"
+"  ____) || |_) || (_| || (__|  __/| || || |_) |\n"
+" |_____/ | .__/  \\__,_| \\___|\\___||_||_||_.__/ \n"
+"         | |                                   \n"
+"         |_|                \n" ;
}
