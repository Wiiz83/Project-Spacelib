package com.miage.spacelibmecanicien.servlets;

import com.miage.spacelibmecanicien.model.UtilisateurExistantException_Exception;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien;
import com.miage.spacelibmecanicien.model.WebServicesMecanicien_Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Enregistrement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String login = request.getParameter("login");
        String motdepasse = request.getParameter("motdepasse");

        WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
        WebServicesMecanicien port = service.getWebServicesMecanicienPort();
        
        try {
            port.creerCompte(nom, prenom, login, motdepasse);
            
            request.setAttribute("messageInfo", "Votre compte a été créé avec succès.");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
              
        } catch (UtilisateurExistantException_Exception ex) {
            Logger.getLogger(Enregistrement.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("messageErreur", "Erreur : " + ex.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
