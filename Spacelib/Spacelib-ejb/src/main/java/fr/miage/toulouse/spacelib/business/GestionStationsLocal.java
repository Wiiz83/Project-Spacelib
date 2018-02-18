/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.toulouse.spacelib.business;

import javax.ejb.Local;

/**
 *
 * @author mahdi
 */
@Local
public interface GestionStationsLocal {
    public void creerStation (Long id); 
}
