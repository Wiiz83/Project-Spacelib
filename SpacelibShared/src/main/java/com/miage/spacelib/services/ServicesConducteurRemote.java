/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import com.miage.spacelib.exceptions.TransfertInconnuException;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.exceptions.UtilisateurExistantException;
import com.miage.spacelib.ressources.RTransfert;
import java.util.ArrayList;
import javax.ejb.Remote;
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

/**
 *
 * @author Mahdi
 */
@Remote
public interface ServicesConducteurRemote {

    public Long login(String login, String motdepasse) throws UsagerInconnuException;

    public RTransfert reserverTransfert(Long idConducteur, Long idTransfert) throws UsagerInconnuException, TransfertInconnuException;

    public ArrayList<RTransfert> obtenirTransfertsConducteur(Long idConducteur) throws UsagerInconnuException;

}
