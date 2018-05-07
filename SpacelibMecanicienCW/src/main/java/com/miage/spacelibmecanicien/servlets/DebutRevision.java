/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibmecanicien.servlets;

import com.miage.spacelibmecanicien.model.MecanicienInconnuException_Exception;
import com.miage.spacelibmecanicien.model.Navette;
import com.miage.spacelibmecanicien.model.NavetteInconnuException_Exception;
import com.miage.spacelibmecanicien.model.NavettePourQuaiInexistantException_Exception;
import com.miage.spacelibmecanicien.model.Quai;
import com.miage.spacelibmecanicien.model.QuaiInconnuException_Exception;
import com.miage.spacelibmecanicien.model.QuaiInexistantException_Exception;
import com.miage.spacelibmecanicien.model.Revision;
import com.miage.spacelibmecanicien.model.RevisionInexistanteException_Exception;
import com.miage.spacelibmecanicien.model.StationInconnuException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien_Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DebutRevision", urlPatterns = {"/DebutRevision"})
public class DebutRevision extends HttpServlet {

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

        if (request.getParameter("idNavette") != null) {

            HttpSession session = request.getSession();
            long idStation = (long) session.getAttribute("idStation");
            long idMecanicien = (long) session.getAttribute("idMecanicien");
            String idNavetteS = request.getParameter("idNavette");
            long idNavette = Long.parseLong(idNavetteS);
            
            WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
            WebServicesMecanicien port = service.getWebServicesMecanicienPort();
            try {
                Quai quai = port.choisirNavetteDebutRevision(idNavette, idStation, idMecanicien);
                long idQuai = quai.getId();
                request.setAttribute("idQuai", idQuai);
                RequestDispatcher rd = request.getRequestDispatcher("Index");
                rd.forward(request, response);
            } catch (MecanicienInconnuException_Exception | NavetteInconnuException_Exception | QuaiInconnuException_Exception ex) {
                Logger.getLogger(DebutRevision.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static List<Revision> getNavetteList(long idStation) throws NavettePourQuaiInexistantException_Exception, QuaiInexistantException_Exception, RevisionInexistanteException_Exception, StationInconnuException_Exception {
        List<Revision> revisions = null;
        WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
        WebServicesMecanicien port = service.getWebServicesMecanicienPort();
        revisions = port.recupererListeNavettesAReviser(idStation);
        return revisions;
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
