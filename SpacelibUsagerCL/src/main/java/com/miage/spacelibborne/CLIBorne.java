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
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RVoyage;
import com.miage.spacelib.services.ServicesConducteurRemote;
import com.miage.spacelib.services.ServicesUsagerRemote;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Objects;
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
    private final ServicesConducteurRemote serviceCond;
    private final Scanner scanner = new Scanner(System.in);
    private final CLIUtils utils = new CLIUtils();

    public CLIBorne(ServicesUsagerRemote serviceUsager, ServicesConducteurRemote serviceCond) {
        this.serviceCond = serviceCond;
        this.serviceUsager = serviceUsager;
    }

    public void run() throws UsagerInconnuException, IllegalAccessException, InvocationTargetException, QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, StationInconnuException, VoyageInconnuException, UtilisateurExistantException {
        System.out.println(ascii_spacelib);
        ArrayList<RStation> stations = this.serviceUsager.obtenirStations();
        Long idStationCourante = ChoisirStationCourante(stations);
        while (true) {
            String typeClient = utils.saisirChaine(scanner, "Type de client? ");
            try {
                if (typeClient.equalsIgnoreCase("Conducteur")) {
                    Long idConducteur = obtenirConducteur();
                    runConducteur(idStationCourante, idConducteur, stations);
                } else if (typeClient.equalsIgnoreCase("Usager")) {
                    Long idUsager = obtenirUsager();
                    runUsager(idStationCourante, idUsager, stations);
                } else {
                    System.out.println("Aucun utilisateur reconnu. Veuillez reessayer svp.");
                }
            } catch (UsagerInconnuException ex) {
                System.out.println("Aucun utilisateur reconnu. Veuillez reessayer.");
            }

        }
    }

    private void runUsager(Long idStationCourante, Long idUsager, ArrayList<RStation> stations) throws UsagerInconnuException, QuaiInexistantException, VoyageInconnuException, QuaiIndisponibleException, TempsTrajetInconnuException, StationInconnuException {
        CHOIX_PROCESS process = obtenirProcess(idUsager);
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
        return utils.saisirEntier(scanner, "Station courante: ", getIDsStations(stations));
    }

    private Long obtenirUsager() throws UsagerInconnuException, UtilisateurExistantException {
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

    private Long creerCompte() throws UtilisateurExistantException {
        String nom = utils.saisirChaine(scanner, "Nom: ");
        String prenom = utils.saisirChaine(scanner, "Prenom: ");
        String login = utils.saisirChaine(scanner, "Login: ");
        String mdp = utils.saisirChaine(scanner, "Mot de passe: ");
        return this.serviceUsager.creerCompte(nom, prenom, login, mdp);
    }

    private CHOIX_PROCESS obtenirProcess(Long idUsager) throws UsagerInconnuException {
        try {
            RVoyage voyage = this.serviceUsager.voyageEnCours(idUsager);
            if (voyage == null) {
                return CHOIX_PROCESS.DEPART;
            } else {
                return CHOIX_PROCESS.ARRIVEE;
            }
        } catch (VoyageInconnuException ex) {
            return CHOIX_PROCESS.DEPART;
        }
    }

    private void depart(Long usager, Long idStationDepart, ArrayList<RStation> stationsArrivee) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {
        afficherListeStations(stationsArrivee);
        Long idStationArrivee = utils.saisirEntier(scanner, "Station d'arrivée: ", getIDsStations(stationsArrivee));
        Long nbPassagers = utils.saisirEntier(scanner, "Nombre de passagers: ", new Long(0), Long.MAX_VALUE);
        RVoyage voyage = this.serviceUsager.reserverVoyage(usager, idStationDepart, idStationArrivee, (int) (long) nbPassagers, Calendar.getInstance());
        System.out.println("Réservation réussie. Rendez vous au quai " + voyage.getQuaiDepart());
    }

    private void arrivee(Long usager) throws UsagerInconnuException, VoyageInconnuException {
        RVoyage voyageEncours = this.serviceUsager.voyageEnCours(usager);
        if (utils.yesNoQuestion(scanner, "Finaliser le voyage en cours? ")) {
            this.serviceUsager.finaliserVoyage(voyageEncours.getId());
        }
    }

    /// Conducteur
    private void runConducteur(Long idStationCourante, Long idConducteur, ArrayList<RStation> stations) throws UsagerInconnuException, VoyageInconnuException {
        Long idTransfertEnCours = transfertEnCours(idConducteur);
        if (idTransfertEnCours != null) {
            finaliserTransfert(idTransfertEnCours);
        } else {
            System.out.println("Aucun transfert en cours");
        }
    }

    private Long transfertEnCours(Long idConducteur) throws UsagerInconnuException, VoyageInconnuException {
        return this.serviceCond.transfertEnCours(idConducteur).getId();
    }

    private void finaliserTransfert(Long idTransfertEnCours) throws VoyageInconnuException {
        if (utils.yesNoQuestion(scanner, "Finaliser le transfert en cours? ")) {
            this.serviceCond.finaliserVoyage(idTransfertEnCours);
        }
    }

    private Long obtenirConducteur() throws UsagerInconnuException {
        String login = utils.saisirChaine(scanner, "Login: ");
        String mdp = utils.saisirChaine(scanner, "Mot de passe: ");
        return this.serviceCond.login(login, mdp);
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
