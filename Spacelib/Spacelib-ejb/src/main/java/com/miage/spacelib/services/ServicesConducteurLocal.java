package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import com.miage.spacelib.ressources.RStation;
import com.miage.spacelib.ressources.RStatsStation;
import com.miage.spacelib.ressources.RTransfert;
import com.miage.spacelib.ressources.RTransfertNecessaire;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

@Local
public interface ServicesConducteurLocal {
    
    public Long login(String login, String motdepasse) throws UsagerInconnuException;
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse) throws UtilisateurExistantException;
    
    public RTransfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException;
    
    public ArrayList<RTransfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException;
    
    List<RTransfertNecessaire> obtenirTransfertsNecessaires();
    
    List<RStatsStation> stats();
}
