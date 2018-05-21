package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.NombreNavettesInvalideException;
import com.miage.spacelib.ressources.RNavette;
import com.miage.spacelib.ressources.RQuai;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ServicesAdminRemote {
    public Long creerStation (String localisation, String nom, Long nbquais, ArrayList<Integer> nbPlacesNavettes ) throws NombreNavettesInvalideException;    

}
