package com.miage.spacelib.business;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.exceptions.QuaiIndisponibleException;
import com.miage.spacelib.exceptions.QuaiInexistantException;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TempsTrajetInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.VoyageInconnuException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

@Local
public interface GestionTransfertLocal {

    public ArrayList<Transfert> obtenirTransfertsNecessaires(); // ne pas utiliser

    public List<Map.Entry<Station, Station>> transfertsEquilibrage();

    public ArrayList<Transfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException;

    public void finaliserTransfert(Long idTransfert) throws VoyageInconnuException;
    
    public Transfert transfertEnCours(Long idConducteur) throws UsagerInconnuException, VoyageInconnuException;
            
    public Transfert reserverTransfert(Long idUsager, Long idStationDepart, Long idStationArrivee) throws QuaiInexistantException, QuaiIndisponibleException, TempsTrajetInconnuException, UsagerInconnuException, StationInconnuException;
}
