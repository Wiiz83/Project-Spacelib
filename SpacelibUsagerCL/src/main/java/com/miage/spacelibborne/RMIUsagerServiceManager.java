/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibborne;

import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.miage.spacelib.services.ServicesUsagerRemote;
/**
 *
 * @author Mahdi
 */
public class RMIUsagerServiceManager {
     private final static String GLASSFISH_ORB_HOST = "localhost";
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String SERVICES_USAGER_EJB_URI = "java:global/Spacelib-ear/Spacelib-ejb-1.0-SNAPSHOT/ServicesUsager!com.miage.spacelib.services.ServicesUsagerRemote";

    private InitialContext namingContext;
    private ServicesUsagerRemote usagerRemoteSvc;

    public RMIUsagerServiceManager() throws NamingException {
        this.initJndi();
        this.retrieveRemoteServicesAdmin();
    }

    private void initJndi() throws NamingException {
        Properties jNDIProperties = new Properties();
        jNDIProperties.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialHost", GLASSFISH_ORB_HOST);
        jNDIProperties.setProperty("org.omg.CORBA.ORBInitialPort", GLASSFISH_ORB_PORT);
        this.namingContext = new InitialContext(jNDIProperties);
    }

    private void retrieveRemoteServicesAdmin() throws NamingException {
        this.usagerRemoteSvc = (ServicesUsagerRemote) this.namingContext.lookup(SERVICES_USAGER_EJB_URI);
    }

    public ServicesUsagerRemote getUsagerRemoteSvc() {
        return usagerRemoteSvc;
    }
}
