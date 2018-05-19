/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibusager.servlets;

import com.miage.spacelib.services.ReservationClotureeException_Exception;
import com.miage.spacelib.services.ReservationInconnuException_Exception;
import com.miage.spacelib.services.ReservationPasseeException_Exception;
import com.miage.spacelib.services.UsagerInconnuException_Exception;
import com.miage.spacelib.services.WebServicesUsager;
import com.miage.spacelib.services.WebServicesUsager_Service;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Annulation extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.getAttribute("idUsager") == null) {
            request.setAttribute("messageErreur", "Erreur : Merci de vous connecter");
            RequestDispatcher rd = request.getRequestDispatcher("Index");
            rd.forward(request, response);
        } else {
            if (request.getParameter("idVoyage") != null) {

                try {
                    Long idUsager = (Long) session.getAttribute("idUsager");
                    String idVoyageS = request.getParameter("idVoyage");
                    Long idVoyage = Long.parseLong(idVoyageS);

                    WebServicesUsager_Service service = new WebServicesUsager_Service();
                    WebServicesUsager port = service.getWebServicesUsagerPort();
                    port.annulerVoyage(idUsager, idVoyage);
                    
                    request.setAttribute("messageSucces", "Le voyage a bien été supprimé.");
                    RequestDispatcher rd = request.getRequestDispatcher("annulation.jsp");
                    rd.forward(request, response);
                } catch (ReservationClotureeException_Exception | ReservationInconnuException_Exception | ReservationPasseeException_Exception | UsagerInconnuException_Exception ex) {
                    Logger.getLogger(Annulation.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("messageErreur", "Erreur : " + ex.getMessage());
                    RequestDispatcher rd = request.getRequestDispatcher("annulation.jsp");
                    rd.forward(request, response);
                }
            }
        }
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
