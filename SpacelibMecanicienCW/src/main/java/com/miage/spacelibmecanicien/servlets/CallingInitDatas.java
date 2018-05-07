/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibmecanicien.servlets;

import com.miage.spacelibmecanicien.model.WebServicesMecanicien;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien_Service;

/**
 *
 * @author uzanl
 */
public class CallingInitDatas {
    
    public static void main(String args[]){
        System.out.println(initDatas());
    }
    
    public static int initDatas(){
        WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
        WebServicesMecanicien port = service.getWebServicesMecanicienPort();
        return  port.initDatas();
    }
    
}
