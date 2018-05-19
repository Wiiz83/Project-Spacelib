package com.miage.spacelib.business;

import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.repositories.StationFacadeLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GestionTransfert implements GestionTransfertLocal {

    @EJB
    private StationFacadeLocal stationFacade;
    
    
    @Override
    public ArrayList<Transfert> obtenirTransfertsNecessaires(Long idStationDepart, Long idStationArrivee) throws StationInconnuException {
        final Station stationDepart = this.stationFacade.find(idStationDepart);
        if(stationDepart == null) throw new StationInconnuException("Cette station de départ n'existe pas.");
        
        final Station stationArrivee = this.stationFacade.find(idStationArrivee);
        if(stationArrivee == null) throw new StationInconnuException("Cette station d'arrivée n'existe pas.");
        
        // De today à today + 10 jours
        
        return null;
    }

    @Override
    public Transfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException {
        return null;
    }

    @Override
    public ArrayList<Transfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException {
        return null;
    }


}
