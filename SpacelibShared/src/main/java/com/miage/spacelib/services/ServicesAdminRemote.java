package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RStatsStation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

@Remote
public interface ServicesAdminRemote {
    public Long creerStation (String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes, Map<Long,Integer> tempsTrajets ) throws NombreNavettesInvalideException;    
    public ArrayList<RStation> obtenirStations();
    List<Map.Entry<RStation, RStation>> transfertsEquilibrage();
    List<RStatsStation> stats();
}
