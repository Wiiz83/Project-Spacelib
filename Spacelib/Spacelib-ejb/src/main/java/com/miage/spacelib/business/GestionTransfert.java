package com.miage.spacelib.business;

import com.miage.spacelib.business.equilibrage.Equilibrage;
import com.miage.spacelib.business.equilibrage.EquilibrageResultat;
import com.miage.spacelib.entities.Conducteur;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.TempsTrajet;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.repositories.ConducteurFacadeLocal;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.repositories.TempsTrajetFacadeLocal;
import com.miage.spacelib.repositories.TransfertFacadeLocal;
import com.miage.spacelib.repositories.UsagerFacadeLocal;
import com.miage.spacelib.repositories.VoyageFacadeLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GestionTransfert implements GestionTransfertLocal {

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

    @EJB
    private ConducteurFacadeLocal conducteurFacade;

    public static final String estVoyage = "Voyage";
    public static final String estTransfert = "Transfert";
    public static final String estProchaineReservation = "estProchaineReservation";
    public static final String estVoyageEnCours = "estVoyageEnCours";
    public static final String estAucun = "Aucun";

    @Override
    public ArrayList<Transfert> obtenirTransfertsNecessaires() {
        throw new UnsupportedOperationException("Utiliser transfertsEquilibrage()");
    }

    public List<Map.Entry<Station, Station>> transfertsEquilibrage() {
        List<Station> stations = stationFacade.findAll();
        Map<Station, Integer> variations = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 10);
        stations.forEach((s) -> {
            variations.put(s,
                    this.stationFacade.nbNavetteEntrantes(s.getId(), cal)
                    - this.stationFacade.nbNavetteSortantes(s.getId(), cal)
            );
        });
        Equilibrage eq = new Equilibrage(variations);
        EquilibrageResultat res = eq.obtenirResultats();
        return res.listeTransferts();
    }

    @Override
    public ArrayList<Transfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException {
        final Conducteur conducteur = this.conducteurFacade.find(idConducteur);
        if (conducteur == null) {
            throw new UsagerInconnuException("Ce compte de conducteur n'existe pas.");
        }
        ArrayList<Transfert> transferts = new ArrayList(transfertFacade.findAllTransfertsPrevusByConducteur(conducteur));
        return transferts;
    }

    @Override
    public Transfert reserverTransfert(Long idConducteur, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {

        // est ce que le conducteur existe ?
        final Conducteur conducteur = this.conducteurFacade.find(idConducteur);
        if (conducteur == null) {
            throw new UsagerInconnuException("Ce compte de conducteur n'existe pas.");
        }

        // est ce que la station de départ existe ?
        final Station stationDepart = this.stationFacade.find(idStationDepart);
        if (stationDepart == null) {
            throw new StationInconnuException("La station de départ n'existe pas.");
        }

        // est ce que la station d'arrivée existe ?
        final Station stationArrive = this.stationFacade.find(idStationArrivee);
        if (stationArrive == null) {
            throw new StationInconnuException("La station d'arrivée n'existe pas.");
        }

        // est ce que le temps de trajet entre la station d'arrivée et la station de départ existe ?
        final TempsTrajet tpsTrajet = this.tpstrajetFacade.findByStations(stationDepart, stationArrive);
        if (tpsTrajet == null) {
            throw new TempsTrajetInconnuException("Le temps de trajet n'existe pas entre ces deux stations.");
        }

        // est ce que la station de départ a au moins un quai ?
        List<Quai> quaisStationDepart = this.quaiFacade.recupererListeQuaisParStation(stationDepart);
        if (quaisStationDepart.size() <= 0) {
            throw new QuaiInexistantException("Il n'existe pas de quais pour la station de départ.");
        }

        // est ce que la station d'arrivée a au moins un quai ?
        List<Quai> quaisStationArrive = this.quaiFacade.recupererListeQuaisParStation(stationArrive);
        if (quaisStationArrive.size() <= 0) {
            throw new QuaiInexistantException("Il n'existe pas de quais pour la station d'arrivée.");
        }

        Navette navetteFinale = null;
        Quai quaiDepartFinal = null;
        Quai quaiArriveFinal = null;

        outerloop:
        for (Quai q : quaisStationDepart) {

            Navette navette = null;
            Calendar dateArriveePrecedenteReservation = null;

            // on récupère la navette de la précédente arrivée du quai en cours
            Voyage precedentVoyage = this.voyageFacade.findPlusProcheVoyageArriveADateEtQuai(dateDepart, q);
            Transfert precedentTransfert = this.transfertFacade.findPlusProcheTransfertArriveADateEtQuai(dateDepart, q);
            String lePlusTard = lequelEstLePlusTard(precedentVoyage, precedentTransfert);
            switch (lePlusTard) {
                case estAucun:
                    // est ce que le quai possède une navette arrimée au moment de la réservation ?
                    Navette navetteArrimee = q.getNavette();
                    if (navetteArrimee != null) {
                        navette = navetteArrimee;
                        dateArriveePrecedenteReservation = Calendar.getInstance();
                    } else {
                        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il n'y a jamais eu de voyage ni de transfert sur ce quai. Il n'y a donc pas de navette disponible sur ce quai. On passe au prochain quai.");
                        continue outerloop;
                    }
                case estVoyage:
                    navette = precedentVoyage.getNavette();
                    dateArriveePrecedenteReservation = precedentVoyage.getDateArrivee();
                    break;

                case estTransfert:
                    navette = precedentTransfert.getNavette();
                    dateArriveePrecedenteReservation = precedentTransfert.getDateArrivee();
                    break;
            }

            // est ce que cette navette a déjà un voyage ou un transfert prévu plus tard ?
            boolean autresVoyagesPrevus = false;
            boolean autresTransfertPrevus = false;
            autresVoyagesPrevus = this.voyageFacade.verifierSiAutresVoyagesPrevusSurNavette(dateArriveePrecedenteReservation, navette);
            autresTransfertPrevus = this.transfertFacade.verifierSiAutresTransfertsPrevusSurNavette(dateArriveePrecedenteReservation, navette);
            if ((autresVoyagesPrevus == false) && (autresTransfertPrevus == false)) {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Il n'y a pas de réservations prévus à cette date de départ.");
            } else {
                Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "La navette " + navette + " a déjà des réservations prévus. On passe au prochain quai.");
                continue;
            }

            // Pour finir on vérifie que la navette remplisse les conditions nécessaires pour le voyage : nombres de places et de voyages suffisants.
            if (EstOKPassagersEtVoyagesNavette(navette, NbPassagers) == true) {
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

        /*
           ALGORITHME POUR TROUVER LE QUAI D'ARRIVE
           Si il y a une navette qui arrive avant, il faut vérifier qu'elle sera parti quand la navette de l'usager arrivera !
         */
        Calendar dateArrivee = (Calendar) dateDepart.clone();
        dateArrivee.add(Calendar.DATE, (tpsTrajet.getTemps()));
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "DATE DE DEPART PREVU = " + dateDepart);
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "TEMPS TRAJET = " + tpsTrajet.getTemps());
        Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "DATE D'ARRIVEE CALCULEE = " + dateArrivee);

        outerloop:
        for (Quai q : quaisStationArrive) {
            Logger.getLogger(GestionVoyage.class.getName()).log(Level.INFO, "Début d'analyse du quai de la station d'arrivée : " + q);

            // Analyse de la précédente arrivée = Peut être avant ou le jour même. Si aucune arrivée avant, succès !
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

            // On vérifie que cette navette repart STRICTEMENT avant notre arrivée
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
        Transfert transfertFinal = null;
        if ((navetteFinale != null) && (quaiDepartFinal != null) && (quaiArriveFinal != null)) {
            transfertFinal = new Transfert(NbPassagers, navetteFinale, Transfert.statutDebutTransfert, conducteur, dateDepart, dateArrivee, quaiDepartFinal, quaiArriveFinal);
            this.transfertFacade.create(transfertFinal);
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

        return transfertFinal;
    }

    private boolean EstOKPassagersEtVoyagesNavette(Navette n, int nb) {
        boolean result = false;
        if ((n.getNbPlaces() >= nb) && (n.getNbVoyages() > 0)) {
            result = true;
        }
        return result;
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
}
