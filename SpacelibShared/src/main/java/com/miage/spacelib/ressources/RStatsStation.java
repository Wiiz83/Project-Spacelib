/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.ressources;

import java.io.Serializable;

/**
 *
 * @author Mahdi
 */
public class RStatsStation implements Serializable {
    public RStation station;
    public int nbNavettesArrimees;
    public int nbNavettesSortantes10jours;
    public int nbNavettesEntrantes10jours;
}
