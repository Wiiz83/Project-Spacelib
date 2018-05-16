<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@page import="javax.xml.datatype.DatatypeConstants"%>
<%@page import="javax.xml.datatype.DatatypeConstants"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.miage.spacelib.services.RStation"%>
<%@page import="com.miage.spacelib.services.RVoyage"%>
<%@page import="com.miage.spacelib.services.InvocationTargetException_Exception"%>
<%@page import="com.miage.spacelib.services.IllegalAccessException_Exception"%>
<%@page import="com.miage.spacelib.services.WebServicesUsager"%>
<%@page import="com.miage.spacelib.services.WebServicesUsager_Service"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web Usagers</title>
        <link href="css/style.css" rel='stylesheet' type='text/css'/>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Simple Tab Forms Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />

    </head>
    <body>
        <%
            if (session.getAttribute("idUsager") == null) {
                request.setAttribute("messageErreur", "Erreur : Merci de vous connecter");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        %>
        <h1><a href="index.jsp"><img src="images/logo.png" alt="Spacelib logo" height="90px"></a></h1>
        <div class="main-content-nologin">
            <div id="menu">
                <ul>
                    <li><a href="accueil.jsp">Accueil</a></li>
                    <li><a href="voyages.jsp">Mes voyages</a></li>
                    <li><a href="reservation.jsp">Réserver un voyage</a></li>
                    <li><a href="annulation.jsp">Annuler un voyage</a></li>
                    <li><a href="index.jsp">Déconnexion</a></li>
                </ul>
            </div>
            <h3 style='color:white; font-size: 20px; font-weight: bold; padding: 10px;'>Mes voyages</h3>
            <table class="table table-bordered" align="center" style="justify-content:center;align-items:center;width:100%;height:100%;">
                <thead>
                    <tr>
                        <th class="col-md-1">
                            Numéro du voyage
                        </th>
                        <th class="col-md-2">
                            Date de la réservation
                        </th>
                        <th class="col-md-1">
                            Nombre de passagers
                        </th>
                        <th class="col-md-2"> 
                            Date de départ
                        </th>
                        <th class="col-md-1">
                            Station de départ
                        </th>
                        <th class="col-md-1">
                            Numéro du quai de départ
                        </th>
                        <th class="col-md-2"> 
                            Date d'arrivée
                        </th>
                        <th class="col-md-1">
                            Station d'arrivée
                        </th>
                        <th class="col-md-1">
                            Numéro du quai d'arrivée
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                        List<RVoyage> rvoyages = null;
                        WebServicesUsager_Service service = new WebServicesUsager_Service();
                        WebServicesUsager port = service.getWebServicesUsagerPort();

                        String stringToConvert = String.valueOf(session.getAttribute("idUsager"));
                        Long convertedLong = Long.parseLong(stringToConvert);

                        rvoyages = port.obtenirVoyagesUsager(convertedLong);
                        if (rvoyages.size() > 0) {
                            for (RVoyage v : rvoyages) {

                                //récupérer station d'arrivée et départ
                                RStation stationD = port.obtenirStation(v.getQuaiDepart());
                                RStation stationA = port.obtenirStation(v.getQuaiArrivee());
                                
                                XMLGregorianCalendar xmlDateD = v.getDateDepart();
                                XMLGregorianCalendar xmlDateA = v.getDateArrivee();
                                xmlDateD.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
                                xmlDateD.setTime(DatatypeConstants.FIELD_UNDEFINED,DatatypeConstants.FIELD_UNDEFINED,DatatypeConstants.FIELD_UNDEFINED,DatatypeConstants.FIELD_UNDEFINED);
                                xmlDateA.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
                                xmlDateA.setTime(DatatypeConstants.FIELD_UNDEFINED,DatatypeConstants.FIELD_UNDEFINED,DatatypeConstants.FIELD_UNDEFINED,DatatypeConstants.FIELD_UNDEFINED);

                                if ((stationD != null) && (stationA != null)) {
                                    out.println("<tr>");
                                    out.println("<td>" + v.getId() + "</td>");
                                    out.println("<td>" + v.getDateCreation() + "</td>");
                                    out.println("<td>" + v.getNbPassagers() + "</td>");
                                    out.println("<td>" + xmlDateD + "</td>");
                                    out.println("<td>" + stationD.getNom() + "</td>");
                                    out.println("<td>" + v.getQuaiDepart() + "</td>");
                                    out.println("<td>" + xmlDateA + "</td>");
                                    out.println("<td>" + stationA.getNom() + "</td>");
                                    out.println("<td>" + v.getQuaiArrivee()+ "</td>");
                                    out.println("</form>");
                                    out.println("</tr>");
                                } else {
                                    out.println("<p style='color:white; font-weight: bold; padding: 10px;'>Erreur stations</p>");
                                }
                            }
                        } else {
                            out.println("<p style='color:white; font-weight: bold; padding: 10px;'>Vous n'avez pas de voyage prévu.</p>");
                        }
                    %>
                </tbody>
            </table>  
        </div>
        <div class="footer">
            <p> &copy; 2018 Spacelib Company. All Rights Reserved</p>
        </div>
    </body>
</html>
