/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business.equilibrage;

import com.miage.spacelib.entities.Station;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 *
 * @author Mahdi
 */
public class EquilibrageResultat {

    private final Map<Station, Map<Station, Integer>> transfertsSortants;
    private final Map<Station, Integer> compteurTransfertsEntrants;
    private final Map<Station, InfoStation> infosStations;

    EquilibrageResultat(Map<Station, InfoStation> infoStations) {
        this.transfertsSortants = new HashMap<>();
        compteurTransfertsEntrants = new HashMap<>();
        this.infosStations = infoStations;
    }

    public List<Station> transfertsOrdonnes(Station station_depart) {
        return transfertsSortants.get(station_depart)
                .keySet()
                .stream()
                .sorted((s1, s2) -> Double.compare(ratioDispo(s1), ratioDispo(s2)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Entry<Station, Station>> listeTransferts() {
        List<Entry<Station, Station>> transferts = new ArrayList<>();
        List<Station> stations_ordonnees = transfertsSortants
                .keySet()
                .stream()
                .sorted((s1, s2) -> Double.compare(ratioDispo(s1), ratioDispo(s2)))
                .collect(Collectors.toCollection(ArrayList::new));
        stations_ordonnees.forEach((Station s) -> {
            transfertsSortants.get(s).keySet().forEach((dest) -> {
                transferts.add(new AbstractMap.SimpleEntry<>(s, dest));
            });
        });
        return transferts;
    }

    void ajouterTransfert(Station depart, Station arrivee) {
        Map<Station, Integer> arriv_cpt = transfertsSortants.getOrDefault(depart, new HashMap<>());
        arriv_cpt.put(arrivee, arriv_cpt.getOrDefault(arrivee, 0) + 1);
        this.transfertsSortants.put(depart, arriv_cpt);
        this.compteurTransfertsEntrants.put(arrivee, this.compteurTransfertsEntrants.getOrDefault(arrivee, 0) + 1);
    }

    int nbTransfertsSortants(Station station) {
        int n = 0;
        if (transfertsSortants.containsKey(station)) {
            n = transfertsSortants
                    .get(station)
                    .values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }
        return n;

    }

    int nbTransfertsEntrants(Station station) {
        return compteurTransfertsEntrants.getOrDefault(station, 0);
    }

    private double ratioDispo(Station s) {
        return (double) nbNavettesStation(s) / (double) nbQuaisStation(s);
    }

    private int nbQuaisStation(Station s) {
        return this.infosStations.get(s).nbQuais;
    }

    private int nbNavettesStation(Station station) {
        return this.infosStations.get(station).nbNavettes;
    }
}
