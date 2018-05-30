/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Station;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Mahdi
 */
public class Equilibrage {

    public List<Entry<Station, Station>> calculerTrajets(List<Station> stations) {
        List<Station> stations_occ_10p = filtrer(
                stations,
                s -> nbNavettesStation(s) / s.getNbQuais() < 0.10
        );
        List<Station> stations_occ_90p = filtrer(
                stations,
                s -> nbNavettesStation(s) / s.getNbQuais() > 0.90
        );
        List<Entry<Station, Station>> trajets_10p = equilibrer_stations_moins_10p(stations, stations_occ_10p, stations_occ_90p, new ArrayList<>());
        return equilibrer_stations_plus_90p(stations, stations_occ_90p, stations_occ_10p, trajets_10p);
    }

    private List<Entry<Station, Station>> equilibrer_stations_moins_10p(List<Station> stations, List<Station> stations_occ_10p, List<Station> stations_occ_90p, ArrayList<Entry<Station, Station>> transferts) {
        List<Station> stations_10p_equilibrees = new ArrayList<>();
        List<Station> retraits_interdits = stations_occ_90p;
        while (stations_occ_10p.size() >= stations_10p_equilibrees.size()) {
            Station s_inf = stationLaMoinsOccupee(stations_occ_10p, stations_10p_equilibrees);
            if (ratioApresAjout(s_inf) > 0.9) {
                stations_10p_equilibrees.add(s_inf);
                continue;
            }
            while (!stations_10p_equilibrees.contains(s_inf)) {
                Station s_sup = stationPlusOccupee(stations, retraits_interdits, stations_occ_90p);
                if (s_sup == null) {
                    return transferts;
                }
                if (ratioApresRetrait(s_sup) < 0.10) {
                    retraits_interdits.add(s_sup);
                } else {
                    transferts.add(transfert(s_sup, s_inf));
                    if (ratioDispo(s_inf) >= 0.20) {
                        stations_10p_equilibrees.add(s_inf);
                    }
                }
            }
        }
        return transferts;
    }

    private List<Entry<Station, Station>> equilibrer_stations_plus_90p(List<Station> stations, List<Station> stations_occ_90p, List<Station> stations_occ_10p, List<Entry<Station, Station>> trajets_10p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Entry<Station, Station> transfert(Station depart, Station arrivee) {
        return new AbstractMap.SimpleEntry<>(depart, arrivee);
    }

    private Station stationLaMoinsOccupee(List<Station> stations_occ_10p, List<Station> stations_10p_equilibrees) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double ratioApresAjout(Station s_inf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Station stationPlusOccupee(List<Station> stations, List<Station> retraits_interdits, List<Station> stations_occ_90p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double ratioApresRetrait(Station s_sup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private double ratioDispo(Station s_inf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int nbNavettesStation(Station station) {
        //return filtrer(station.getQuais(), q -> q.getStatut() == QuaiStatut.Occupe).size();
        
        // --> commenté par Lucas le 30/05 : 
        // la méthode getStatut() n'existe plus sur l'entité Quai 
        // car l'attribut statut a été supprimé 
        // car il n'était pas manipulé et géré dans le backend
        // et n'est pas demandé dans les specs
        
        return 0;
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
}
