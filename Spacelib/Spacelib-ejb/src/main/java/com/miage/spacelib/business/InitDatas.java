/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelib.business;

import com.miage.spacelib.entities.Administrateur;
import com.miage.spacelib.entities.Conducteur;
import com.miage.spacelib.entities.Mecanicien;
import com.miage.spacelib.entities.Navette;
import com.miage.spacelib.entities.Quai;
import com.miage.spacelib.entities.Revision;
import com.miage.spacelib.entities.Station;
import com.miage.spacelib.entities.Transfert;
import com.miage.spacelib.entities.Usager;
import com.miage.spacelib.entities.Voyage;
import com.miage.spacelib.repositories.AdministrateurFacadeLocal;
import com.miage.spacelib.repositories.ConducteurFacadeLocal;
import com.miage.spacelib.repositories.MecanicienFacadeLocal;
import com.miage.spacelib.repositories.NavetteFacadeLocal;
import com.miage.spacelib.repositories.QuaiFacadeLocal;
import com.miage.spacelib.repositories.RevisionFacadeLocal;
import com.miage.spacelib.repositories.StationFacadeLocal;
import com.miage.spacelib.repositories.TransfertFacadeLocal;
import com.miage.spacelib.repositories.UsagerFacadeLocal;
import com.miage.spacelib.repositories.VoyageFacadeLocal;
import java.sql.*;
import javax.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.*;

@Stateless
public class InitDatas {
    
    @EJB
    private AdministrateurFacadeLocal administrateurFacade;
    
    @EJB
    private ConducteurFacadeLocal conducteurFacade;
    
    @EJB
    private MecanicienFacadeLocal mecanicienFacade;
    
    @EJB
    private NavetteFacadeLocal navetteFacade;
    
    @EJB
    private QuaiFacadeLocal quaiFacade;
    
    @EJB
    private RevisionFacadeLocal revisionFacade;
    
    @EJB
    private StationFacadeLocal stationFacade;
    
    @EJB
    private TransfertFacadeLocal transfertFacade;
    
    @EJB
    private UsagerFacadeLocal usagerFacade;
    
    @EJB
    private VoyageFacadeLocal voyageFacade;
    
    /*
    InitDatas(){
        
    }
    
    public static void main(String args[]){
        InitDatas id = new InitDatas();
        id.DEV_ONLY_InitBD();
         
        InitialContext ctx;
        try {
           System.getProperty(Context.PROVIDER_URL);
            ctx = new InitialContext();/*
            
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            env.put(Context.PROVIDER_URL, "jdbc:derby://localhost:1527/SpacelibDB");
            env.put(Context.SECURITY_PRINCIPAL, "SpacelibDB");
            env.put(Context.SECURITY_CREDENTIALS, "SpacelibDB");

            ctx = new InitialContext(env);
            DataSource ds = (DataSource) ctx.lookup("java:app/jdbc/SpacelibDataSource");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO ADMINISTRATEUR (DATE_CREATION, LOGIN, MOT_DE_PASSE, NOM, PRENOM) VALUES (CURRENT_TIMESTAMP, 'adminl', 'admin', 'UZAN', 'Lucas')");

            rs.close();
            stmt.close();
            conn.close();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(InitDatas.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }*/

    public int DEV_ONLY_InitBD() {
        
        // Supression des données existantes
        List<Voyage> current_voyage_bd = voyageFacade.findAll();
        for (Voyage el : current_voyage_bd) {
            voyageFacade.remove(el);
        }
        
        List<Transfert> current_transfert_bd = transfertFacade.findAll();
        for (Transfert el : current_transfert_bd) {
            transfertFacade.remove(el);
        }
        
        List<Revision> current_revision_bd = revisionFacade.findAll();
        for (Revision el : current_revision_bd) {
            revisionFacade.remove(el);
        }
        
        
        List<Quai> current_quai_bd = quaiFacade.findAll();
        for (Quai el : current_quai_bd) {
            quaiFacade.remove(el);
        }
        
        List<Navette> current_navette_bd = navetteFacade.findAll();
        for (Navette el : current_navette_bd) {
            navetteFacade.remove(el);
        }
        
        List<Administrateur> current_admin_bd = administrateurFacade.findAll();
        for (Administrateur el : current_admin_bd) {
            administrateurFacade.remove(el);
        }
        
        List<Conducteur> current_conduc_bd = conducteurFacade.findAll();
        for (Conducteur el : current_conduc_bd) {
            conducteurFacade.remove(el);
        }
        
        List<Mecanicien> current_meca_bd = mecanicienFacade.findAll();
        for (Mecanicien el : current_meca_bd) {
            mecanicienFacade.remove(el);
        }
        
        List<Usager> current_usager_bd = usagerFacade.findAll();
        for (Usager el : current_usager_bd) {
            usagerFacade.remove(el);
        }

        List<Station> current_station_bd = stationFacade.findAll();
        for (Station el : current_station_bd) {
            stationFacade.remove(el);
        }
        
        Administrateur a1 = new Administrateur("UZAN", "Lucas", "lulu", "lulu");
        Administrateur a2 = new Administrateur("HENTATI", "Mahdi", "mama", "mama");
        Administrateur a3 = new Administrateur("ROQUES-GEOFFROY", "Philippe", "roro", "roro");
        administrateurFacade.create(a1);
        administrateurFacade.create(a2);
        administrateurFacade.create(a3);
        
        Conducteur c1 = new Conducteur("UZAN", "Lucas", "lulu", "lulu");
        Conducteur c2 = new Conducteur("HENTATI", "Mahdi", "mama", "mama");
        Conducteur c3 = new Conducteur("ROQUES-GEOFFROY", "Philippe", "roro", "roro");
        conducteurFacade.create(c1);
        conducteurFacade.create(c2);
        conducteurFacade.create(c3);
        
        Mecanicien m1 = new Mecanicien("UZAN", "Lucas", "lulu", "lulu");
        Mecanicien m2 = new Mecanicien("HENTATI", "Mahdi", "mama", "mama");
        Mecanicien m3 = new Mecanicien("ROQUES-GEOFFROY", "Philippe", "roro", "roro");
        mecanicienFacade.create(m1);
        mecanicienFacade.create(m2);
        mecanicienFacade.create(m3);
        
        Usager u1 = new Usager("UZAN", "Lucas", "lulu", "lulu");
        Usager u2 = new Usager("HENTATI", "Mahdi", "mama", "mama");
        Usager u3 = new Usager("ROQUES-GEOFFROY", "Philippe", "roro", "roro");
        usagerFacade.create(u1);
        usagerFacade.create(u2);
        usagerFacade.create(u3);
        
        Navette n = new Navette();
        n.setNbPlaces(5);
        n.setNbVoyages(3);
        n.setStatut(Navette.NavetteStatut.Disponible);
        navetteFacade.create(n);

        Navette n2 = new Navette();
        n2.setNbPlaces(5);
        n2.setNbVoyages(3);
        n2.setStatut(Navette.NavetteStatut.Disponible);
        navetteFacade.create(n2);

        Navette n3 = new Navette();
        n3.setNbPlaces(5);
        n3.setNbVoyages(3);
        n3.setStatut(Navette.NavetteStatut.Disponible);
        navetteFacade.create(n3);

        Navette n4 = new Navette();
        n4.setNbPlaces(3);
        n4.setNbVoyages(0);
        n4.setStatut(Navette.NavetteStatut.Disponible);
        navetteFacade.create(n4);

        Navette n5 = new Navette();
        n5.setNbPlaces(3);
        n5.setNbVoyages(0);
        n5.setStatut(Navette.NavetteStatut.Disponible);
        navetteFacade.create(n5);
        
        
        Navette n6 = new Navette();
        n6.setNbPlaces(3);
        n6.setNbVoyages(0);
        n6.setStatut(Navette.NavetteStatut.Disponible);
        navetteFacade.create(n6);
        
        Station s1 = new Station("sol.d", 15, "Terre");
        Station s2 = new Station("pegasi.b", 15, "Dimidium");
        Station s3 = new Station("delphini.b", 15, "Arion");
        Station s4 = new Station("cancri.c", 15, "Brahe");
        Station s5 = new Station("epsilonTauri.b", 15, "Amateru");
        Station s6 = new Station("gammaCepheiA.b", 15, "Tadmor");
        
        
        
        List<Quai> listeQuai = new ArrayList<>();
        Quai q = new Quai(s1);
        quaiFacade.create(q);
        listeQuai.add(q);
        Quai q2 = new Quai(s1);
        quaiFacade.create(q2);
        listeQuai.add(q2);
        Quai q3 = new Quai(s1);
        quaiFacade.create(q3);
        listeQuai.add(q3);
        s1.setQuais(listeQuai);
        
        
        /*
        QUAIS AVEC NAVETTES
        */
        listeQuai = new ArrayList<>();
        Quai q4 = new Quai(s2);
        q4.setNavette(n4);
        quaiFacade.create(q4);
        listeQuai.add(q4);
        Quai q5 = new Quai(s2);
        q5.setNavette(n5);
        quaiFacade.create(q5);
        listeQuai.add(q5);
        Quai q6 = new Quai(s2);
        q6.setNavette(n6);
        quaiFacade.create(q6);
        listeQuai.add(q6);
        s2.setQuais(listeQuai);
        
        
        listeQuai = new ArrayList<>();
        Quai q7 = new Quai(s3);
        quaiFacade.create(q7);
        listeQuai.add(q7);
        Quai q8 = new Quai(s3);
        quaiFacade.create(q8);
        listeQuai.add(q8);
        Quai q9 = new Quai(s3);
        quaiFacade.create(q9);
        listeQuai.add(q9);
        s3.setQuais(listeQuai);
        
        
        listeQuai = new ArrayList<>();
        Quai q10 = new Quai(s4);
        quaiFacade.create(q10);
        listeQuai.add(q10);
        Quai q11 = new Quai(s4);
        quaiFacade.create(q11);
        listeQuai.add(q11);
        Quai q12 = new Quai(s4);
        quaiFacade.create(q12);
        listeQuai.add(q12);
        s4.setQuais(listeQuai);
        
        
        listeQuai = new ArrayList<>();
        Quai q13 = new Quai(s5);
        quaiFacade.create(q13);
        listeQuai.add(q13);
        Quai q14 = new Quai(s5);
        quaiFacade.create(q14);
        listeQuai.add(q14);
        Quai q15 = new Quai(s5);
        quaiFacade.create(q15);
        listeQuai.add(q15);
        s5.setQuais(listeQuai);
        
        listeQuai = new ArrayList<>();
        Quai q16 = new Quai(s6);
        quaiFacade.create(q16);
        listeQuai.add(q16);
        Quai q17 = new Quai(s6);
        quaiFacade.create(q17);
        listeQuai.add(q17);
        Quai q18 = new Quai(s6);
        quaiFacade.create(q18);
        listeQuai.add(q18);
        s6.setQuais(listeQuai);

        
        stationFacade.create(s1);
        stationFacade.create(s2);
        stationFacade.create(s3);
        stationFacade.create(s4);
        stationFacade.create(s5);
        stationFacade.create(s6);
        
        
        /*
        Révision nécessaire
        Navette n, String s, Quai q
        */
        Revision rn1 = new Revision(n4, Revision.statutRevisionNecessaire, q4);
        Revision rn2 = new Revision(n5, Revision.statutRevisionNecessaire, q5);
        Revision rn3 = new Revision(n6, Revision.statutRevisionNecessaire, q6);
        revisionFacade.create(rn1);
        revisionFacade.create(rn2);
        revisionFacade.create(rn3);
        
        /*
        Révision pris en charge    
        Navette n, String s, Mecanicien m, Quai q
        */       
        Revision r1 = new Revision(n, Revision.statutFinRevision, m1, q);
        Revision r2 = new Revision(n2, Revision.statutFinRevision, m2, q2);
        Revision r3 = new Revision(n3, Revision.statutFinRevision, m3, q3);
        revisionFacade.create(r1);
        revisionFacade.create(r2);
        revisionFacade.create(r3);
        
        /*
        Transfert
        Transfert(int nb, Navette n, String s, Conducteur c, Calendar dd, Calendar da, Quai qd, Quai qa)
        */
        Calendar dd1 = new GregorianCalendar(2018,8,15);
        Calendar df1 = new GregorianCalendar(2018,8,19);
        Transfert t1 = new Transfert(5, n, Transfert.statutDebutTransfert, c1, dd1, df1, q, q2);
        Transfert t2 = new Transfert(5, n2, Transfert.statutDebutTransfert, c2, dd1, df1, q2, q3);
        Transfert t3 = new Transfert(5, n3, Transfert.statutDebutTransfert, c3, dd1, df1, q3, q);
        transfertFacade.create(t1);
        transfertFacade.create(t2);
        transfertFacade.create(t3);
        
        /*
        Voyage
        Voyage(int nb, Navette n, String s, Usager u, Calendar dd, Calendar da, Quai qd, Quai qa)
        */
        Calendar dd2 = new GregorianCalendar(2018,7,12);
        Calendar df2 = new GregorianCalendar(2018,7,25);
        Voyage v1 = new Voyage(5, n4, Voyage.statutDebutVoyage, u1, dd2, df2, q, q2);
        Voyage v2 = new Voyage(5, n5, Voyage.statutDebutVoyage, u2, dd2, df2, q2, q3);
        Voyage v3 = new Voyage(5, n6, Voyage.statutDebutVoyage, u3, dd2, df2, q3, q);
        voyageFacade.create(v1);
        voyageFacade.create(v2);
        voyageFacade.create(v3);
        
        
        return 1;
    }
}
