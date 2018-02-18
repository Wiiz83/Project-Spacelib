/*
 * Copyright (C) 2018 Rémi Venant $lt;remi.venant@gmail.com$gt;.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package fr.miage.toulouse.spacelibadmin;

import fr.miage.toulouse.spacelib.services.ServicesAdminRemote;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Rémi Venant $lt;remi.venant@gmail.com$gt;
 */
public class RMIAdminServiceManager {
    private final static String GLASSFISH_ORB_HOST = "localhost";
    private final static String GLASSFISH_ORB_PORT = "3700";
    private final static String SERVICES_ADMIN_EJB_URI = "java:global/Spacelib-ear/Spacelib-ejb-0.0.1-SNAPSHOT/ServicesAdmin!fr.miage.toulouse.spacelib.services.ServicesAdmin";

    private InitialContext namingContext;
    private ServicesAdminRemote adminRemoteSvc;

    public RMIAdminServiceManager() throws NamingException {
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
        this.adminRemoteSvc = (ServicesAdminRemote) this.namingContext.lookup(SERVICES_ADMIN_EJB_URI);
    }

    public ServicesAdminRemote getadminRemoteSvc() {
        return adminRemoteSvc;
    }
}
