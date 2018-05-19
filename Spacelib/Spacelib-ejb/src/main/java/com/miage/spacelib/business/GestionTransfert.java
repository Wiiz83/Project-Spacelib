/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.exceptions.StationInconnuException;
import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionTransfert implements GestionTransfertLocal {

    @Override
    public ArrayList<Transfert> obtenirTransfertsNecessaires(Long idStationDepart, Long idStationArrivee) throws StationInconnuException {
    
    }

    @Override
    public Transfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException {
    
    }

    @Override
    public ArrayList<Transfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException {
    
    }


}
