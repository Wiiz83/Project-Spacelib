/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.spacelibmecanicien.servlets;

import com.miage.spacelibmecanicien.model.MecanicienInconnuException_Exception;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author uzanl
 */
public class Inscription extends HttpServlet {

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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String motdepasse = request.getParameter("motdepasse");

        WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
        WebServicesMecanicien port = service.getWebServicesMecanicienPort();

        try {
            port.authentifier(login, motdepasse);
            request.setAttribute("messageInfo", "Message modifié avec succès");
            RequestDispatcher rd = request.getRequestDispatcher("Navettes");
            rd.forward(request, response);
            
        } catch (MecanicienInconnuException_Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "Erreur : " + ex.getMessage());
            request.setAttribute("messageErreur", "Erreur : " + ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("Index");
            rd.forward(request, response);
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
