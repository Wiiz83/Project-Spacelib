/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibmecanicien.servlets;

import com.miage.spacelibmecanicien.model.MecanicienInconnuException_Exception;
import com.miage.spacelibmecanicien.model.NavetteInconnuException_Exception;
import com.miage.spacelibmecanicien.model.QuaiInexistantException_Exception;
import com.miage.spacelibmecanicien.model.Revision;
import com.miage.spacelibmecanicien.model.RevisionInexistanteException_Exception;
import com.miage.spacelibmecanicien.model.Station;
import com.miage.spacelibmecanicien.model.StationInconnuException_Exception;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien_Service;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author uzanl
 */
public class Login extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String motdepasse = request.getParameter("motdepasse");
        long idStation = Long.parseLong(request.getParameter("idStation"));

        WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
        WebServicesMecanicien port = service.getWebServicesMecanicienPort();
        
        try {
            long idMecanicien = port.authentifierAvecStationRattachement(login, motdepasse, idStation);
            
            HttpSession session = request.getSession();
            session.setAttribute("idStation", idStation);
            session.setAttribute("idMecanicien", idMecanicien);
            
            Revision revision = port.consulterRevisionEnCours(idMecanicien, idStation);

            if(revision != null){
                request.setAttribute("revision", revision);
                RequestDispatcher rd = request.getRequestDispatcher("FinRevisionJSP");
                rd.forward(request, response);
            } else {
                request.setAttribute("idStation", idStation);
                RequestDispatcher rd = request.getRequestDispatcher("DebutRevisionJSP");
                rd.forward(request, response);
            }
            
        } catch (MecanicienInconnuException_Exception | StationInconnuException_Exception | QuaiInexistantException_Exception | RevisionInexistanteException_Exception | NavetteInconnuException_Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("messageErreur", "Erreur : " + ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("Index");
            rd.forward(request, response);
        }
    }
    
        
    
    public static List<Station> getStationsList(){
        WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
        WebServicesMecanicien port = service.getWebServicesMecanicienPort();
        List<Station> stations = port.recupererListeStations();
        return stations;
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
