package com.miage.servlets;

import com.miage.spacelib.services.QuaiIndisponibleException_Exception;
import com.miage.spacelib.services.QuaiInexistantException_Exception;
import com.miage.spacelib.services.RTransfert;
import com.miage.spacelib.services.StationInconnuException_Exception;
import com.miage.spacelib.services.TempsTrajetInconnuException_Exception;
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

public class Reservation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("idUsager") == null) {
            request.setAttribute("messageErreur", "Erreur : Merci de vous connecter");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else {
            String idTransfert = request.getParameter("idTransfert");
            Long idStationDepart = Long.valueOf(idTransfert.substring(0, idTransfert.indexOf("-")));
            Long idStationArrivee = Long.valueOf(idTransfert.substring(idTransfert.indexOf("-") +1, idTransfert.length()));
            Long idConducteur = (Long) session.getAttribute("idUsager");

            WebServicesConducteur_Service service = new WebServicesConducteur_Service();
            WebServicesConducteur port = service.getWebServicesConducteurPort();

            RTransfert transfert;
            
            try {
                System.out.println(idConducteur);
                System.out.println(idStationDepart);
                System.out.println(idStationArrivee);
                transfert = port.reserverTransfert(idConducteur, idStationDepart, idStationArrivee);
                
                long idQuaiDepart = transfert.getQuaiDepart();
                long idNavette = transfert.getNavette();
                request.setAttribute("idQuaiDepart", idQuaiDepart);
                request.setAttribute("idNavette", idNavette);
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            } catch (QuaiIndisponibleException_Exception | QuaiInexistantException_Exception | StationInconnuException_Exception | TempsTrajetInconnuException_Exception | UsagerInconnuException_Exception ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("messageErreur", "Erreur : " + ex.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
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
