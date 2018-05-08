/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.webservices;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import com.miage.spacelib.services.ServicesReservationLocal;
/**
 *
 * @author uzanl
 */
@WebService(serviceName = "WebServicesGetData")
@Stateless()
public class WebServicesGetData {

    @EJB
    private ServicesReservationLocal ejbRef;
    
    @WebMethod(operationName = "getStations")
    public List<com.miage.spacelib.entities.Station> getStations(){
            return ejbRef.obtenirStations();
    }
}
