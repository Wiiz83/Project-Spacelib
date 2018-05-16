package com.miage.spacelibusager.servlets;

import com.miage.spacelib.services.QuaiIndisponibleException_Exception;
import com.miage.spacelib.services.QuaiInexistantException_Exception;
import com.miage.spacelib.services.RVoyage;
import com.miage.spacelib.services.StationInconnuException_Exception;
import com.miage.spacelib.services.TempsTrajetInconnuException_Exception;
import com.miage.spacelib.services.UsagerInconnuException_Exception;
import com.miage.spacelib.services.WebServicesUsager;
import com.miage.spacelib.services.WebServicesUsager_Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Reservation extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("idUsager") == null) {
            request.setAttribute("messageErreur", "Erreur : Merci de vous connecter");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else {
            Long idStationDepart = Long.valueOf(request.getParameter("idStationDepart"));
            Long idStationArrivee = Long.valueOf(request.getParameter("idStationArrivee"));

            if (Objects.equals(idStationDepart, idStationArrivee)) {
                request.setAttribute("messageErreur", "Erreur : Les stations de départ et d'arrivée ne peuvent pas être identiques.");
                RequestDispatcher rd = request.getRequestDispatcher("reservation.jsp");
                rd.forward(request, response);
            }

            Long idUsager = (Long) session.getAttribute("idUsager");

            int nbpassagers = Integer.parseInt(request.getParameter("nbpassagers"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            //Calendar dateDepart;
            XMLGregorianCalendar dateDepart = null;

            try {
                date = sdf.parse(request.getParameter("ddepart"));
                //dateDepart = Calendar.getInstance();
                //dateDepart.setTime(date);
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(date);
                dateDepart = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            } catch (ParseException | DatatypeConfigurationException ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            }

            WebServicesUsager_Service service = new WebServicesUsager_Service();
            WebServicesUsager port = service.getWebServicesUsagerPort();

            RVoyage vvoyage;

            try {
                vvoyage = port.reserverVoyage(idUsager, idStationDepart, idStationArrivee, nbpassagers, dateDepart);
                RequestDispatcher rd = request.getRequestDispatcher("voyages.jsp");
                rd.forward(request, response);
            } catch (QuaiIndisponibleException_Exception | QuaiInexistantException_Exception | StationInconnuException_Exception | TempsTrajetInconnuException_Exception | UsagerInconnuException_Exception ex) {
                Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("messageErreur", "Erreur : " + ex.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("reservation.jsp");
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
