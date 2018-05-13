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
import java.util.Calendar;
import java.util.List;
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

    @Override
    public void annulerVoyage(Long idClient, Long idReservation) throws UsagerInconnuException, ReservationInconnuException, ReservationPasseeException, ReservationClotureeException {
    }

    @Override
    public Voyage voyageEnCours(Long idUsager) throws UsagerInconnuException {
        return null;
    }

    @Override
    public void finaliserVoyage(Long idVoyage) throws VoyageInconnuException {
    }

    @Override
    public Voyage reserverVoyage(Long idUsager, Long idStationDepart, Long idStationArrivee, int NbPassagers, Calendar dateDepart) throws QuaiInexistantException ,QuaiIndisponibleException, InvocationTargetException, IllegalAccessException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException {

        final Usager usager = this.usagerFacade.find(idUsager);
        if (usager == null) {
            throw new UsagerInconnuException("Ce compte de mécanicien n'existe pas.");
        }

        final Station stationDepart = this.stationFacade.find(idStationDepart);
        if (stationDepart == null) {
            throw new StationInconnuException("La station de départ n'existe pas.");
        }

        final Station stationArrive = this.stationFacade.find(idStationArrivee);
        if (stationArrive == null) {
            throw new StationInconnuException("La station d'arrivée n'existe pas.");
        }

        System.out.println("STATION DEPART " + stationDepart);
        System.out.println("STATION ARRIVEE " + stationArrive);
        final TempsTrajet tpsTrajet = this.tpstrajetFacade.findByStations(stationDepart, stationArrive);
        if (tpsTrajet == null) {
            throw new TempsTrajetInconnuException("Le temps de trajet n'existe pas entre ces deux stations.");
        }

        /*
            ALGORITHME POUR TROUVER LE QUAI DE DEPART ET LA NAVETTE DE TRANSPORT 
         */
        Navette navetteFinale = null;
        Quai quaiDepartFinal = null;
        List<Quai> quaisStationDepart = this.quaiFacade.recupererListeQuaisParStation(stationDepart);
        
        if(quaisStationDepart == null){
            throw new QuaiInexistantException("Il n'existe pas de quais pour cette station.");
        }
        
        outerloop:
        for (Quai q : quaisStationDepart) {

            // on récupère la plus proche arrivée à ce quai avant départ 
            Voyage precedentVoyage = this.voyageFacade.findPlusProcheVoyageArriveADateEtQuai(dateDepart, q);
            // on récupère la plus proche transfert à ce quai avant départ 
            Transfert precedentTransfert = this.transfertFacade.findPlusProcheTransfertArriveADateEtQuai(dateDepart, q);

            // on prend la plus proche résa entre le dernier transfert et le dernier voyage
            Navette navette = null;

            System.out.println(dateDepart);
            System.out.println(q);

            System.out.println("PRECEDENT VOYAGE " + precedentVoyage);
            System.out.println("PRECEDENT TRANSFERT " + precedentTransfert);

            Calendar mostRecentDate = null;

            if ((precedentVoyage != null) && (precedentTransfert != null)) {
                Calendar dateArrVoyage = precedentVoyage.getDateArrivee();
                Calendar dateArrTransfert = precedentTransfert.getDateArrivee();
                if (dateArrVoyage.compareTo(dateArrTransfert) > 0) {
                    // voyage est plus récent, on prend cette navette
                    navette = precedentVoyage.getNavette();
                    mostRecentDate = precedentVoyage.getDateArrivee();
                } else if (dateArrVoyage.compareTo(dateArrTransfert) < 0) {
                    // transfert est plus récent, on prend cette navette
                    navette = precedentTransfert.getNavette();
                    mostRecentDate = precedentTransfert.getDateArrivee();
                } else if (dateArrVoyage.compareTo(dateArrTransfert) == 0) {
                    // les deux arrivent le même jour : Problème !
                    System.out.println("How to get here?");
                } else {
                    System.out.println("How to get here?");
                }
            } else if ((precedentTransfert == null) && (precedentVoyage == null)) {
                // Pas de transferts précédents sur ce quai
                continue; // on passe au prochain quai
            } else if (precedentTransfert == null) {
                navette = precedentVoyage.getNavette();
                mostRecentDate = precedentVoyage.getDateArrivee();
            } else if (precedentVoyage == null) {
                navette = precedentTransfert.getNavette();
                mostRecentDate = precedentTransfert.getDateArrivee();
            } else {
                // Pas de transferts précédents sur ce quai
                continue; // on passe au prochain quai
            }

            // on vérifie il y a un prochain voyage ou transfert avant le départ souhaité
            Voyage prochainVoyage = this.voyageFacade.findPlusProcheVoyageDepartADateEtQuai(mostRecentDate, q);
            Transfert prochainTransfert = this.transfertFacade.findPlusProcheTransfertDepartADateEtQuai(mostRecentDate, q);

            Calendar prochainDepartLePlusTot = null;

            if ((prochainVoyage != null) && (prochainTransfert != null)) {
                Calendar dateDepVoyage = prochainVoyage.getDateArrivee();
                Calendar dateDepTransfert = prochainTransfert.getDateArrivee();

                if (dateDepVoyage.compareTo(dateDepTransfert) > 0) {
                    System.out.println("le prochain voyage est plus loin");
                    prochainDepartLePlusTot = prochainTransfert.getDateArrivee();
                } else if (dateDepVoyage.compareTo(dateDepTransfert) < 0) {
                    System.out.println("le prochain transfert est plus loin");
                    prochainDepartLePlusTot = prochainVoyage.getDateArrivee();
                } else if (dateDepVoyage.compareTo(dateDepTransfert) == 0) {
                    System.out.println("les deux partent le même jour : Problème !");
                } else {
                    System.out.println("How to get here?");
                }
            } else if ((prochainTransfert == null) && (prochainVoyage == null)) {
                // Do nothing
            } else if (prochainTransfert == null) {
                prochainDepartLePlusTot = prochainVoyage.getDateArrivee();
            } else if (prochainVoyage == null) {
                prochainDepartLePlusTot = prochainTransfert.getDateArrivee();
            }

            System.out.println("Voyage prochain : " + prochainVoyage);
            System.out.println("Transfert prochain : " + prochainTransfert);
            System.out.println("prochainDepartLePlusTot : " + prochainDepartLePlusTot);

            // il y a un prochain départ prévu 
            if (prochainDepartLePlusTot != null) {
                if (dateDepart.compareTo(prochainDepartLePlusTot) > 0) {
                    System.out.println("prochain départ avant la date de départ souhaitée : NO GO ");
                    throw new QuaiIndisponibleException("Il n'y a pas de quai de départ disponible pour cette station.");
                } else if (dateDepart.compareTo(prochainDepartLePlusTot) < 0) {
                    System.out.println("prochain départ après la date de départ souhaitée : GO ");

                    int totalTpsAlleeRetour = tpsTrajet.getTemps() * 2;
                    Calendar prochaineDateSiRetourNavetteAQuai = dateDepart;
                    prochaineDateSiRetourNavetteAQuai.add(Calendar.DATE, totalTpsAlleeRetour);

                    if (prochaineDateSiRetourNavetteAQuai.compareTo(prochainDepartLePlusTot) > 0) {
                        System.out.println("la date de retour de la navette à ce quai pour la prochaine réservation arrivera trop tard : NO GO");
                        throw new QuaiIndisponibleException("Il n'y a pas de quai de départ disponible pour cette station.");
                    } else if (prochaineDateSiRetourNavetteAQuai.compareTo(prochainDepartLePlusTot) < 0) {
                        System.out.println("la date de retour de la navette à ce quai pour la prochaine réservation arrivera avant : GO");

                        // on vérifie si la navette a assez de voyages restants pour faire celui-ci
                        if (navette.getNbVoyages() > 0) {
                            quaiDepartFinal = q;
                            navetteFinale = navette;
                            break outerloop;
                        }
                    } else if (prochaineDateSiRetourNavetteAQuai.compareTo(prochainDepartLePlusTot) == 0) {
                        System.out.println("la date de retour de la navette à ce quai pour la prochaine réservation arrivera le jour même, c'est risqué : NO GO");
                        throw new QuaiIndisponibleException("Il n'y a pas de quai de départ disponible pour cette station.");
                    } else {
                        System.out.println("How to get here?");
                    }

                } else if (dateDepart.compareTo(prochainDepartLePlusTot) == 0) {
                    System.out.println("prochain départ même jour que la date de départ souhaitée : NO GO ");
                    throw new QuaiIndisponibleException("Il n'y a pas de quai de départ disponible pour cette station.");
                } else {
                    System.out.println("How to get here?");
                }
                // il n'y a pas de prochain départ prévu 
            } else {
                quaiDepartFinal = q;
                navetteFinale = navette;
                break outerloop;
            }

        }

        System.out.println("quaiDepartFinal : " + quaiDepartFinal);
        System.out.println("navetteFinale : " + navetteFinale);


        /*
            ALGORITHME POUR TROUVER LE QUAI D'ARRIVE
         */
        Calendar dateArrivee = dateDepart;
        dateArrivee.add(Calendar.DATE, tpsTrajet.getTemps());
        Quai quaiArriveFinal = null;
        List<Quai> quaisStationArrive = this.quaiFacade.recupererListeQuaisParStation(stationArrive);
        
        if(quaisStationArrive.size() <= 0){
            throw new QuaiInexistantException("Il n'existe pas de quais pour cette station.");
        }
        System.out.println("quaisStationArrive " +quaisStationArrive);
        
        outerloop:
        for (Quai q : quaisStationArrive) {
            // on récupère la plus proche arrivée à ce quai avant arrivée 
            Voyage precedentVoyage = this.voyageFacade.findPlusProcheVoyageArriveADateEtQuai(dateArrivee, q);
            // on récupère la plus proche transfert à ce quai avant arrivée 
            Transfert precedentTransfert = this.transfertFacade.findPlusProcheTransfertArriveADateEtQuai(dateArrivee, q);

            Calendar mostRecentDate = null;

            if ((precedentVoyage != null) && (precedentTransfert != null)) {
                // on prend la plus proche résa entre le dernier transfert et le dernier voyage
                Calendar dateArrVoyage = precedentVoyage.getDateArrivee();
                Calendar dateArrTransfert = precedentTransfert.getDateArrivee();
                if (dateArrVoyage.compareTo(dateArrTransfert) > 0) {
                    // voyage est plus récent
                    mostRecentDate = precedentVoyage.getDateArrivee();
                } else if (dateArrVoyage.compareTo(dateArrTransfert) < 0) {
                    // transfert est plus récent
                    mostRecentDate = precedentTransfert.getDateArrivee();
                } else if (dateArrVoyage.compareTo(dateArrTransfert) == 0) {
                    // les deux arrivent le même jour : Problème !
                    System.out.println("How to get here?");
                } else {
                    System.out.println("How to get here?");
                }
            } else if ((precedentTransfert == null) && (precedentVoyage == null)) {
                // do nothing
            } else if (precedentTransfert == null) {
                mostRecentDate = precedentVoyage.getDateArrivee();
            } else if (precedentVoyage == null) {
                mostRecentDate = precedentTransfert.getDateArrivee();
            } else {
                // do nothing
            }
            
            System.out.println("mostRecentDate : " + mostRecentDate);


            if (mostRecentDate == null) {
                quaiArriveFinal = q;
                break outerloop;
            } else {
                // on vérifie il y a un prochain voyage ou transfert avant le départ souhaité
                Voyage prochainVoyage = this.voyageFacade.findPlusProcheVoyageDepartADateEtQuai(mostRecentDate, q);
                Transfert prochainTransfert = this.transfertFacade.findPlusProcheTransfertDepartADateEtQuai(mostRecentDate, q);
                Calendar prochainDepartLePlusTot = null;

                if ((prochainVoyage != null) && (prochainTransfert != null)) {
                    Calendar dateDepVoyage = prochainVoyage.getDateArrivee();
                    Calendar dateDepTransfert = prochainTransfert.getDateArrivee();

                    if (dateDepVoyage.compareTo(dateDepTransfert) > 0) {
                        System.out.println("le prochain voyage est plus loin");
                        prochainDepartLePlusTot = prochainTransfert.getDateArrivee();
                    } else if (dateDepVoyage.compareTo(dateDepTransfert) < 0) {
                        System.out.println("le prochain transfert est plus loin");
                        prochainDepartLePlusTot = prochainVoyage.getDateArrivee();
                    } else if (dateDepVoyage.compareTo(dateDepTransfert) == 0) {
                        System.out.println("les deux partent le même jour : Problème !");
                    } else {
                        System.out.println("How to get here?");
                    }
                } else if ((prochainTransfert == null) && (prochainVoyage == null)) {
                   // do nothing
                } else if (prochainTransfert == null) {
                    mostRecentDate = precedentVoyage.getDateArrivee();
                } else if (prochainVoyage == null) {
                    mostRecentDate = precedentTransfert.getDateArrivee();
                } else {
                    // do nothing
                }

                if (prochainDepartLePlusTot == null) {
                    // il n'y pas de départ prévu ! la navette qui est arrimée au quai y reste pour une durée indeterminée
                    throw new QuaiIndisponibleException("Il n'y a pas de quai d'arrivée disponible pour cette station.");
                } else {
                    if (dateArrivee.compareTo(prochainDepartLePlusTot) > 0) {
                        System.out.println("prochain départ avant la date d'arrivée souhaitée : GO ");
                        quaiArriveFinal = q;
                        break outerloop;
                    } else if (dateArrivee.compareTo(prochainDepartLePlusTot) < 0) {
                        System.out.println("prochain départ après la date d'arrivée souhaitée : NO GO ");
                        throw new QuaiIndisponibleException("Il n'y a pas de quai d'arrivée disponible pour cette station.");
                    } else if (dateArrivee.compareTo(prochainDepartLePlusTot) == 0) {
                        System.out.println("prochain départ même jour que la date d'arrivée souhaitée : NO GO ");
                        throw new QuaiIndisponibleException("Il n'y a pas de quai d'arrivée disponible pour cette station.");
                    } else {
                        System.out.println("How to get here?");
                    }
                }

            }

        }

        Voyage voyageFinal = null;
        if ((navetteFinale != null) && (quaiDepartFinal != null) && (quaiArriveFinal != null)) {
            voyageFinal = this.voyageFacade.creerVoyage(navetteFinale, usager, quaiDepartFinal, quaiArriveFinal, NbPassagers, dateDepart, dateArrivee);
        } else {
            System.out.println("navetteFinale "+navetteFinale);
            System.out.println("quaiDepartFinal " +quaiDepartFinal);
            System.out.println("quaiArriveFinal " +quaiArriveFinal);
            
            System.out.println("PROBLEME");
        }

        return voyageFinal;

    }

}
