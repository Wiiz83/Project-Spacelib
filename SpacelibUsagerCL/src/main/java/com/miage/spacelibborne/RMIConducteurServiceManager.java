/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibborne;

import com.miage.spacelib.services.ServicesConducteurRemote;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author Mahdi
 */
public class RMIConducteurServiceManager {
     private final static String GLASSFISH_ORB_HOST = "localhost";
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String SERVICES_USAGER_EJB_URI = "java:global/Spacelib-ear/Spacelib-ejb-1.0-SNAPSHOT/ServicesConducteur!com.miage.spacelib.services.ServicesConducteurRemote";

    private InitialContext namingContext;
    private ServicesConducteurRemote usagerRemoteSvc;

    public RMIConducteurServiceManager() throws NamingException {
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
        this.usagerRemoteSvc = (ServicesConducteurRemote) this.namingContext.lookup(SERVICES_USAGER_EJB_URI);
    }

    public ServicesConducteurRemote getConducteurRemoteSvc() {
        return usagerRemoteSvc;
    }
}