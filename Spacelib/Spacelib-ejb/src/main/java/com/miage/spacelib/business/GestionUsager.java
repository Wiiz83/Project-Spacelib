/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.exceptions.UsagerInconnuException;
import com.miage.spacelib.repositories.UsagerFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@Stateless
public class GestionUsager implements GestionUsagerLocal {
    
    @EJB
    private UsagerFacadeLocal usagerFacade;

    @Override
    public Long authentifier(String login, String motdepasse) throws UsagerInconnuException {
        final Usager usager = this.usagerFacade.findByLoginAndPassword(login, motdepasse);
        if(usager == null){
            throw new UsagerInconnuException("Ce compte de mécanicien n'existe pas.");
        } else {
            return usager.getId();
        }

    }

    
}
