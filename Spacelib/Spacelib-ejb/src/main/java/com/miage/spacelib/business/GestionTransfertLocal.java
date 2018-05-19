package com.miage.spacelib.business;

import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface GestionTransfertLocal {
    
    public ArrayList<Transfert> obtenirTransfertsNecessaires(Long idStationDepart, Long idStationArrivee) throws StationInconnuException;
    
    public Transfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException;
    
    public ArrayList<Transfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException;
    
}
