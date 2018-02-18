
package fr.miage.toulouse.spacelibadmin;

import javax.naming.NamingException;
import fr.miage.toulouse.spacelib.services.ServicesAdminRemote;


public class Launcher {
    
    
    public static void main(String[] args){
        try{
            RMIAdminServiceManager rmiMgr = new RMIAdminServiceManager();
            ServicesAdminRemote service = rmiMgr.getadminRemoteSvc() ; 
            service.creerStation(new Long(3));
        }catch(NamingException ex){
            System.err.println("Erreur d'initialisation RMI : " + ex.getMessage());
            System.err.println(ex.getExplanation());
        }
        
    }
}
