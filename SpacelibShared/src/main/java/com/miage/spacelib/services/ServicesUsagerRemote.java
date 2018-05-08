/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author uzanl
 */
@Remote
public interface ServicesUsagerRemote {
    public boolean login (String nom, String pass) ;    
}
