package com.miage.servlets;

import com.miage.spacelib.services.UsagerInconnuException_Exception;
import com.miage.spacelib.services.WebServicesConducteur;
import com.miage.spacelib.services.WebServicesConducteur_Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String login = request.getParameter("login");
        String motdepasse = request.getParameter("motdepasse");

        WebServicesConducteur_Service service = new WebServicesConducteur_Service();
        WebServicesConducteur port = service.getWebServicesConducteurPort();
        
        try {
            Long idConducteur = port.login(login, motdepasse);
            
            HttpSession session = request.getSession();
            session.setAttribute("idUsager", idConducteur);
            RequestDispatcher rd = request.getRequestDispatcher("accueil.jsp");
            rd.forward(request, response);
            
        } catch (UsagerInconnuException_Exception ex) {
            Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, ex);
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
