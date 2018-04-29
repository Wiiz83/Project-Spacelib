/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.services;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;

/**
 *
 * @author uzanl
 */
@WebService(serviceName = "WebServicesMecanicien")
@Stateless()
public class WebServicesMecanicien {

    @EJB
    private ServicesMecanicienLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"
    
    @WebMethod(operationName = "toto")
    public String toto(){
        return ejbRef.toto();
    }
    
}
