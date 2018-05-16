package com.miage.spacelib.business;

import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.TempsTrajet;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.ReservationClotureeException;
import com.miage.spacelib.exceptions.ReservationInconnuException;
import com.miage.spacelib.exceptions.ReservationPasseeException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.repositories.TempsTrajetFacadeLocal;
import com.miage.spacelib.repositories.TransfertFacadeLocal;
import com.miage.spacelib.repositories.UsagerFacadeLocal;
import com.miage.spacelib.repositories.VoyageFacadeLocal;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GestionVoyage implements GestionVoyageLocal {

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @EJB
    private VoyageFacadeLocal voyageFacade;

    @EJB
    private TransfertFacadeLocal transfertFacade;

    @EJB
    private RevisionFacadeLocal revisionFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private TempsTrajetFacadeLocal tpstrajetFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    public static final String estVoyage = "Voyage";
    public static final String estTransfert = "Transfert";
    public static final String estProchaineReservation = "estProchaineReservation";
    public static final String estVoyageEnCours = "estVoyageEnCours";
    public static final String estAucun = "Aucun";

    @Override
    public void annulerVoyage(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
    }

    @Override
    public Voyage voyageEnCours(Long idUsager) throws UsagerInconnuException {
        return null;
    }

    @Override
    public ArrayList<Voyage> obtenirVoyagesPrevusUsager(Long idUsager) throws UsagerInconnuException {
        final Usager usager = this.usagerFacade.find(idUsager);
        if (usager == null) {
            throw new UsagerInconnuException("Ce compte d'usager n'existe pas.");
        }

        ArrayList<Voyage> voyages = new ArrayList(voyageFacade.findAllVoyagesPrevusByUsager(usager));

        return voyages;
    }

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException {
    }

    @Override
    public Voyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {

        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Param idUsager : " + idUsager);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Param idStationDepart : " + idStationDepart);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Param idStationArrivee : " + idStationArrivee);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Param NbPassagers : " + NbPassagers);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Param dateDepart : " + dateDepart);

        final Usager usager = this.usagerFacade.find(idUsager);
        if (usager == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Ce compte d'usager n'existe pas.");
            throw new UsagerInconnuException("Ce compte d'usager n'existe pas.");
        }

        final Station stationDepart = this.stationFacade.find(idStationDepart);
        if (stationDepart == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "La station de départ n'existe pas.");
            throw new StationInconnuException("La station de départ n'existe pas.");
        }

        final Station stationArrive = this.stationFacade.find(idStationArrivee);
        if (stationArrive == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "La station d'arrivée n'existe pas.");
            throw new StationInconnuException("La station d'arrivée n'existe pas.");
        }

        final TempsTrajet tpsTrajet = this.tpstrajetFacade.findByStations(stationDepart, stationArrive);
        if (tpsTrajet == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Le temps de trajet n'existe pas entre ces deux stations.");
            throw new TempsTrajetInconnuException("Le temps de trajet n'existe pas entre ces deux stations.");
        }

        List<Quai> quaisStationDepart = this.quaiFacade.recupererListeQuaisParStation(stationDepart);
        if (quaisStationDepart.size() <= 0) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Il n'existe pas de quais pour la station de départ.");
            throw new QuaiInexistantException("Il n'existe pas de quais pour la station de départ.");
        }

        List<Quai> quaisStationArrive = this.quaiFacade.recupererListeQuaisParStation(stationArrive);
        if (quaisStationArrive.size() <= 0) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Il n'existe pas de quais pour la station d'arrivée.");
            throw new QuaiInexistantException("Il n'existe pas de quais pour la station d'arrivée.");
        }

        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Usager : " + usager);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Station de départ : " + stationDepart);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Station d'arrivée : " + stationArrive);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Temps de trajet : " + tpsTrajet);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Quais de la station de départ : " + quaisStationDepart);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Quais de la station d'arrivée : " + quaisStationArrive);

        /*
             RAPPEL : 1 ARRIVEE MAX A LIEU LE MATIN ET 1 DEPART MAX A LIEU L'APRES MIDI
         */
 /*
            ALGORITHME POUR TROUVER LE QUAI DE DEPART ET LA NAVETTE DE TRANSPORT 
         */
        Navette navetteFinale = null;
        Quai quaiDepartFinal = null;
        outerloop:
        for (Quai q : quaisStationDepart) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Début d'analyse du quai de la station de départ : " + q);

            /*
                Le jour du départ, on vérifie qu'il n'y pas déjà un transfert ou un voyage qui part de ce quai
             */
            Voyage voyageMemeJourQuai = this.voyageFacade.findVoyageDepartJourDateEtQuai(dateDepart, q);
            Transfert transfertMemeJourQuai = this.transfertFacade.findTransfertDepartJourDateEtQuai(dateDepart, q);
            if ((voyageMemeJourQuai != null) || (transfertMemeJourQuai != null)) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Il y a déjà une réservation qui part de ce quai ce le même jour.");
                continue outerloop;
            }


            /*
                Analyse de la précédente arrivée = Peut être avant ou le jour même. Si aucune arrivée avant, erreur : prochain quai.
             */
            Voyage precedentVoyage = this.voyageFacade.findPlusProcheVoyageArriveADateEtQuai(dateDepart, q);
            Transfert precedentTransfert = this.transfertFacade.findPlusProcheTransfertArriveADateEtQuai(dateDepart, q);
            Navette navette = null;
            String lePlusTard = lequelEstLePlusTard(precedentVoyage, precedentTransfert);
            switch (lePlusTard) {
                case estAucun:
                    Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il n'y a jamais eu de voyage ni de transfert sur ce quai. Il n'y a donc pas de navette disponible sur ce quai. On passe au prochain quai.");
                    continue outerloop;
                case estVoyage:
                    navette = precedentVoyage.getNavette();
                    break;
                case estTransfert:
                    navette = precedentTransfert.getNavette();
                    break;
            }
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La navette " + navette + " est arrivée au quai " + q);

            /*
            On vérifie que cette navette n'a pas de voyage ou de transfert qui :
            - décollent avant ou le même jour que nous
            ET
            - arrivent strictement après notre date de départ
             */
            boolean autresVoyagesPrevus = false;
            boolean autresTransfertPrevus = false;
            autresVoyagesPrevus = this.voyageFacade.verifierSiAutresVoyagesPrevusSurNavetteADate(dateDepart, navette);
            autresTransfertPrevus = this.transfertFacade.verifierSiAutresTransfertsPrevusSurNavetteADate(dateDepart, navette);
            if ((autresVoyagesPrevus == false) && (autresTransfertPrevus == false)) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il n'y a pas de réservations prévus à cette date de départ.");
            } else {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La navette " + navette + " a déjà des réservations prévus à cette date. On passe au prochain quai.");
                continue;
            }

            /*
            Est ce qu'il y a des prochaines réservations prévues plus tard ou le même jour du départ avec cette navette à ce quai (qui ont donc déjà été effectuées avant nous) ?
             */
            Transfert prochainTransfert = this.transfertFacade.findPlusProcheTransfertDepartDeNavetteADateEtQuai(dateDepart, q, navette);
            Voyage prochainVoyage = this.voyageFacade.findPlusProcheVoyageDepartDeNavetteADateEtQuai(dateDepart, q, navette);
            System.out.println(prochainTransfert + " " + prochainVoyage);
            Calendar DateDuProchainDepart = null;
            boolean prochainDepartPrevuQuaiDepartAvecNavette = false;
            String LePlutTot = lequelEstLePlusTot(prochainVoyage, prochainTransfert);
            switch (LePlutTot) {
                case estAucun:
                    prochainDepartPrevuQuaiDepartAvecNavette = false;
                    break;
                case estVoyage:
                    prochainDepartPrevuQuaiDepartAvecNavette = true;
                    DateDuProchainDepart = prochainVoyage.getDateDepart();
                    break;
                case estTransfert:
                    prochainDepartPrevuQuaiDepartAvecNavette = true;
                    DateDuProchainDepart = precedentTransfert.getDateDepart();
                    break;
            }
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Le prochain départ de ce quai est le " + DateDuProchainDepart);

            /*
            Pour les prochains voyages / transferts de cette navette au départ de ce quai, on vérifie que la navette pourra être ramener à temps (allée + retour)
             */
            if (prochainDepartPrevuQuaiDepartAvecNavette == true) {
                int totalTpsAlleeRetour = ((tpsTrajet.getTemps()) * 2);
                Calendar prochaineDateSiRetourNavetteAQuai = (Calendar) dateDepart.clone();
                prochaineDateSiRetourNavetteAQuai.add(Calendar.DATE, totalTpsAlleeRetour);
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Résultat du calcul de temps allée retour pour ramener la navette = " + totalTpsAlleeRetour);
                String DatePlusTot = DateLaPlusTot(dateDepart, prochaineDateSiRetourNavetteAQuai);
                switch (DatePlusTot) {
                    case estAucun:
                        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "ECHEC ! Prochain quai.");
                        continue outerloop;
                    case estProchaineReservation:
                        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "ECHEC ! Le prochain départ est avant la date de départ souhaitée. Prochain quai.");
                        continue outerloop;
                    case estVoyageEnCours:
                        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "SUCCES ! Le prochain départ est après la date de retour de la navcette.");
                        break;
                }
            }

            
            /*
                Pour finir on vérifie que la navette remplisse les conditions nécessaires pour le voyage : nombres de places et de voyages suffisants.
            */
            if (EstOKPassagersEtVoyagesNavette(navette, NbPassagers, prochainDepartPrevuQuaiDepartAvecNavette) == true) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "SUCCES FINAL ! La navette a assez de voyages restants pour faire celui-ci. Quai de départ et navette validés.");
                quaiDepartFinal = q;
                navetteFinale = navette;
                break outerloop;
            } else {
                continue outerloop;
            }
        }

        
        
        
        
        
        ////////////////////////////////////////////////// CHECKPOINT ////////////////////////////////////////////////////////
        if ((quaiDepartFinal == null) || (navetteFinale == null)) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Il n'y a pas de navette disponible au départ de cette station.");
            throw new QuaiIndisponibleException("Il n'y a pas de navette disponible au départ de cette station.");
        } else {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Le quai de départ choisi est le " + quaiDepartFinal);
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La navette pour le voyage est la " + navetteFinale);
        }
        ////////////////////////////////////////////////// CHECKPOINT ////////////////////////////////////////////////////////

        
        
        
        
        
        /*
            ALGORITHME POUR TROUVER LE QUAI D'ARRIVE
            Si il y a une navette qui arrive avant, il faut vérifier qu'elle sera parti quand la navette de l'usager arrivera !
         */
        Calendar dateArrivee = (Calendar) dateDepart.clone();
        dateArrivee.add(Calendar.DATE, (tpsTrajet.getTemps()));
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "DATE DE DEPART PREVU = " + dateDepart);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "TEMPS TRAJET = " + tpsTrajet.getTemps());
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "DATE D'ARRIVEE CALCULEE = " + dateArrivee);

        Quai quaiArriveFinal = null;

        outerloop:
        for (Quai q : quaisStationArrive) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Début d'analyse du quai de la station d'arrivée : " + q);
            
            
            /*
                Le jour de l'arrivée, on vérifie qu'il n'y pas déjà un transfert ou un voyage qui arrive de ce quai
             */
            Voyage voyageMemeJourQuai = this.voyageFacade.findVoyageArriveeJourDateEtQuai(dateArrivee, q);
            Transfert transfertMemeJourQuai = this.transfertFacade.findTransfertArriveeJourDateEtQuai(dateArrivee, q);
            if ((voyageMemeJourQuai != null) || (transfertMemeJourQuai != null)) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Il y a une réservation qui arrive déjà sur ce quai à cette date d'arrivée.");
                continue outerloop;
            }
            

            /*
                Analyse de la précédente arrivée = Peut être avant ou le jour même. Si aucune arrivée avant, succès !
             */
            Voyage precedentVoyage = this.voyageFacade.findPlusProcheVoyageArriveADateEtQuai(dateArrivee, q);
            Transfert precedentTransfert = this.transfertFacade.findPlusProcheTransfertArriveADateEtQuai(dateArrivee, q);
            Navette autreNavette = null;
            String lePlusTard = lequelEstLePlusTard(precedentVoyage, precedentTransfert);
            switch (lePlusTard) {
                case estAucun:
                    Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "SUCCES ! Il n'y a pas de précédent voyage ni transfert sur le quai d'arrivée.");
                    quaiArriveFinal = q;
                    break outerloop;
                case estVoyage:
                    autreNavette = precedentVoyage.getNavette();
                    break;
                case estTransfert:
                    autreNavette = precedentTransfert.getNavette();
                    break;
            }
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La navette " + autreNavette + " est arrivée au quai " + q);
            
            

            /*
            On vérifie que cette navette repart STRICTEMENT avant notre arrivée
             */
            boolean autresVoyagesPrevus = false;
            boolean autresTransfertPrevus = false;
            autresVoyagesPrevus = this.voyageFacade.verifierSiNavettePossedeDepartVoyageAvantDate(dateArrivee, autreNavette);
            autresTransfertPrevus = this.transfertFacade.verifierSiNavettePossedeDepartTransfertAvantDate(dateArrivee, autreNavette);
            if ((autresVoyagesPrevus == false) && (autresTransfertPrevus == false)) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "ECHEC ! La navette " + autreNavette + " sera encore arrimée au quai quand nous allons arriver.");
                continue;
            } else {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "SUCCES ! La navette " + autreNavette + " ne sera plus arrimée au quai quand nous allons arriver.");
                quaiArriveFinal = q;
                break outerloop;
            }

        }

        
        
        /////////////////////////////// FINALISATION ////////////////////////////////
        Voyage voyageFinal = null;
        if ((navetteFinale != null) && (quaiDepartFinal != null) && (quaiArriveFinal != null)) {
            voyageFinal = this.voyageFacade.creerVoyage(navetteFinale, usager, quaiDepartFinal, quaiArriveFinal, NbPassagers, dateDepart, dateArrivee);
        } else {

            if (quaiDepartFinal != null) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "ECHEC FINAL ! Il n'y a pas de quai de départ disponible.");
                throw new QuaiIndisponibleException("Il n'y a pas de quai de départ disponible.");
            } else if (quaiArriveFinal != null) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "ECHEC FINAL ! Il n'y a pas de quai d'arrivée disponible.");
                throw new QuaiIndisponibleException("Il n'y a pas de quai d'arrivée disponible.");
            } else if (navetteFinale != null) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "ECHEC FINAL ! Il n'y a pas de navette disponible.");
                throw new QuaiIndisponibleException("Il n'y a pas de navette disponible.");
            }
        }
        /////////////////////////////// FINALISATION ////////////////////////////////

        return voyageFinal;

    }

    private boolean EstOKPassagersEtVoyagesNavette(Navette n, int nb, boolean alleretour) {
        boolean result = false;

        if (n.getNbPlaces() >= nb) {
            if (alleretour == true) {
                if (n.getNbVoyages() > 1) { // car allée et retour = 2 voyages !
                    result = true;
                }
            } else {
                if (n.getNbVoyages() > 0) {
                    result = true;
                }
            }
        }

        return result;
    }

    /*
    private Navette choisirNavetteLaPlusRecente(String lePlusTard, Voyage precedentVoyage, Transfert precedentTransfert) {
        Navette nf = null;

        if (lePlusTard.equals(estTransfert)) {
            nf = precedentTransfert.getNavette();
        } else if (lePlusTard.equals(estVoyage)) {
            nf = precedentVoyage.getNavette();
        }

        return nf;

    }*/
    private String lequelEstLePlusTot(Voyage voyage, Transfert transfert) {

        String resa = null;
        Calendar dateVoyage = null;
        Calendar dateTransfert = null;

        if ((voyage != null) && (transfert != null)) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il y au moins un voyage ET un transfert prévus pour cette navette sur ce quai.");
            dateVoyage = voyage.getDateArrivee();
            dateTransfert = transfert.getDateArrivee();

            if (dateVoyage.compareTo(dateTransfert) > 0) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Le prochain départ pour cette navette sur ce quai est un transfert.");
                resa = estTransfert;
            } else if (dateVoyage.compareTo(dateTransfert) < 0) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Le prochain départ pour cette navette sur ce quai est un voyage.");
                resa = estVoyage;
            } else {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Un problème est apparu pour déterminer la date de départ du prochain voyage et transfert le plus proche sur ce quai.");
                resa = estAucun;
            }

        } else if ((voyage == null) && (transfert == null)) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il n'y a n'y a ni voyage ni transfert prévus prochainement pour cette navette sur ce quai.");
            resa = estAucun;
        } else if (voyage == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il y a au moins un transfert pour cette navette prévus sur ce quai.");
            resa = estTransfert;
        } else if (transfert == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il y a au moins un voyage prévus pour cette navette sur ce quai.");
            resa = estVoyage;
        }

        return resa;
    }

    private String lequelEstLePlusTard(Voyage voyage, Transfert transfert) {

        String resa = null;
        Calendar dateVoyage = null;
        Calendar dateTransfert = null;

        if ((voyage != null) && (transfert != null)) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il y au moins un voyage ET un transfert prévus pour cette navette sur ce quai.");
            dateVoyage = voyage.getDateArrivee();
            dateTransfert = transfert.getDateArrivee();

            if (dateVoyage.compareTo(dateTransfert) > 0) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La dernière arrivée la plus récente sur ce quai est un voyage.");
                resa = estVoyage;
            } else if (dateVoyage.compareTo(dateTransfert) < 0) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La dernière arrivée la plus récente sur ce quai est un transfert.");
                resa = estTransfert;
            } else {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.SEVERE, "Un problème est apparu pour déterminer la date d'arrivée du dernier voyage et transfert la plus récente sur ce quai.");
                resa = estAucun;
            }

        } else if ((voyage == null) && (transfert == null)) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il n'y a n'y a ni voyage ni transfert prévus prochainement pour cette navette sur ce quai.");
            resa = estAucun;
        } else if (voyage == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il y a au moins un transfert pour cette navette prévus sur ce quai.");
            resa = estTransfert;
        } else if (transfert == null) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il y a au moins un voyage prévus pour cette navette sur ce quai.");
            resa = estVoyage;
        }

        return resa;
    }

    private String DateLaPlusTot(Calendar voyageEnCours, Calendar prochaineReservation) {
        String datePlusTot = null;

        if (voyageEnCours.compareTo(prochaineReservation) > 0) {
            datePlusTot = estVoyageEnCours;
        } else if (voyageEnCours.compareTo(prochaineReservation) < 0) {
            datePlusTot = estProchaineReservation;
        } else if (voyageEnCours.compareTo(prochaineReservation) == 0) {
            datePlusTot = estAucun;
        }

        return datePlusTot;
    }

}
