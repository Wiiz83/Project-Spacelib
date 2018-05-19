package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.ressources.RTransfert;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ServicesConducteurLocal {
    
    public Long login(String login, String motdepasse) throws UsagerInconnuException;
    
    public void creerCompte(String nom, String prenom, String login, String motdepasse);

    public ArrayList<RTransfert> obtenirTransfertsNecessaires(Long idStationDepart, Long idStationArrivee) throws StationInconnuException;
    
    public RTransfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException;
    
    public ArrayList<RTransfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException;
    
}
