/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business.equilibrage;

import com.miage.spacelib.entities.Station;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Mahdi
 */
public class Equilibrage {

    private final List<Station> stations;
    private final EquilibrageResultat resultat;
    private final Map<Station, InfoStation> infosStations;
    private final Map<Station, Integer> variations;

    public Equilibrage(Map<Station, Integer> stationsEtVariations, Map<Station, InfoStation> infoStations) {
        this.stations = new ArrayList<>(stationsEtVariations.keySet());
        resultat = new EquilibrageResultat();
        this.variations = stationsEtVariations;
        this.infosStations = infoStations;
    }

    public EquilibrageResultat obtenirResultats() {
        calculerTrajets();
        return resultat;
    }

    private void calculerTrajets() {
        List<Station> stations_occ_10p = filtrer(
                stations,
                s -> nbNavettesStation(s) / nbQuaisStation(s) < 0.10
        );
        List<Station> stations_occ_90p = filtrer(
                stations,
                s -> nbNavettesStation(s) / nbQuaisStation(s) > 0.90
        );
        equilibrer_stations_moins_10p(stations, stations_occ_10p, stations_occ_90p);
        equilibrer_stations_plus_90p(stations, stations_occ_90p, stations_occ_10p);
    }

    private void equilibrer_stations_moins_10p(List<Station> stations, List<Station> stations_occ_10p, List<Station> stations_occ_90p) {
        List<Station> stations_10p_equilibrees = new ArrayList<>();
        List<Station> retraits_interdits = stations_occ_90p;
        while (stations_occ_10p.size() >= stations_10p_equilibrees.size()) {
            Station s_inf = stationLaMoinsOccupee(stations_occ_10p, stations_10p_equilibrees);
            if (ratioApresAjout(s_inf) > 0.9) {
                stations_10p_equilibrees.add(s_inf);
                continue;
            }
            while (!stations_10p_equilibrees.contains(s_inf) && stations.size() < retraits_interdits.size()) {
                Station s_sup = stationPlusOccupeePriorite(stations, retraits_interdits, stations_occ_90p);
                if (ratioApresRetrait(s_sup) < 0.10) {
                    retraits_interdits.add(s_sup);
                } else {
                    transfert(s_sup, s_inf);
                    if (ratioDispo(s_inf) >= 0.20) {
                        stations_10p_equilibrees.add(s_inf);
                    }
                }
            }
        }
    }

    private void equilibrer_stations_plus_90p(List<Station> stations, List<Station> stations_occ_90p, List<Station> stations_occ_10p) {
        List<Station> stations_90p_equilibrees = new ArrayList<>();
        List<Station> ajouts_interdits = stations_occ_10p;
        while (stations_occ_90p.size() >= stations_90p_equilibrees.size()) {
            Station s_sup = stationPlusOccupee(stations_occ_90p, stations_90p_equilibrees);
            if (ratioApresRetrait(s_sup) < 0.10) {
                stations_90p_equilibrees.add(s_sup);
                continue;
            }
            while (!stations_90p_equilibrees.contains(s_sup) && stations.size() < ajouts_interdits.size()) {
                Station s_inf = stationLaMoinsOccupee(stations, ajouts_interdits);
                if (ratioApresAjout(s_inf) > 0.90) {
                    ajouts_interdits.add(s_inf);
                } else {
                    transfert(s_sup, s_inf);
                    if (ratioDispo(s_sup) <= 0.80) {
                        stations_90p_equilibrees.add(s_sup);
                    }
                }
            }
        }
    }

    private void transfert(Station depart, Station arrivee) {
        this.resultat.ajouterTransfert(depart, arrivee);
    }

    private Station stationLaMoinsOccupee(List<Station> stations, List<Station> stations_interdites) {
        return stations
                .stream()
                .filter(s -> !stations_interdites.contains(s))
                .min((s1, s2) -> Double.compare(ratioDispo(s1), ratioDispo(s2))).get();
    }

    private Station stationPlusOccupee(List<Station> stations, List<Station> stations_interdites) {
        return stations
                .stream()
                .filter(s -> !stations_interdites.contains(s))
                .max((s1, s2) -> Double.compare(ratioDispo(s1), ratioDispo(s2))).get();
    }

    private Station stationPlusOccupeePriorite(List<Station> stations, List<Station> stations_interdites, List<Station> stations_prioritaires) {
        Optional<Station> result = stations_prioritaires
                .stream()
                .filter(s -> !stations_interdites.contains(s))
                .max((s1, s2) -> Double.compare(ratioDispo(s1), ratioDispo(s2)));
        if (result.isPresent()) {
            return result.get();
        } else {
            return stationPlusOccupee(stations, stations_interdites);
        }
    }

    private double ratioApresAjout(Station s) {
        return (nbNavettesStation(s) + 1) / nbQuaisStation(s);
    }

    private double ratioApresRetrait(Station s) {
        return (nbNavettesStation(s) - 1) / nbQuaisStation(s);
    }

    private double ratioDispo(Station s) {
        return nbNavettesStation(s) / nbQuaisStation(s);
    }

    private int nbNavettesStation(Station station) {
        int nb_db = this.infosStations.get(station).nbNavettes;
        return nb_db
                - this.resultat.nbTransfertsSortants(station)
                + this.resultat.nbTransfertsEntrants(station)
                + this.variations.get(station);
    }

    private <E> ArrayList<E> filtrer(List<E> all, Predicate<E> filter, Comparator<E> c) {
        ArrayList<E> filtered = all.stream().filter(filter).collect(Collectors.toCollection(ArrayList::new));
        if (c == null) {
            return filtered;
        } else {
            filtered.sort(c);
        }
        return filtered;
    }

    private <E> ArrayList<E> filtrer(List<E> all, Predicate<E> filter) {
        return filtrer(all, filter, null);
    }

    private int nbQuaisStation(Station s) {
        return this.infosStations.get(s).nbQuais;
    }
}
