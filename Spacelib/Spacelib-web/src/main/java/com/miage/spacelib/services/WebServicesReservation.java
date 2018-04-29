/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;

/**
 *
 * @author uzanl
 */
@WebService(serviceName = "WebServicesReservation")
@Stateless()
public class WebServicesReservation {
    
    @EJB
    private ServicesReservationLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"
    
    
    
}
