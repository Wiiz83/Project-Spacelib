<%@page import="com.miage.spacelib.services.WebServicesConducteur"%>
<%@page import="com.miage.spacelib.services.WebServicesConducteur_Service"%>
<%@page import="com.miage.spacelib.services.RStation"%>
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
                    <h3 style='color:white; font-size: 20px; font-weight: bold; padding: 10px;'>Navettes nécessitant une révision</h3>
                    <form method="post" action="DebutRevision">
                        <table class="table table-bordered" align="center" style="justify-content:center;align-items:center;width:100%;height:100%;">
                            <thead>
                                <tr>
                                    <th class="col-md-3">
                                        Priorité
                                    </th>
                                    <th class="col-md-3"> 
                                        Station de départ
                                    </th>
                                    <th class="col-md-3">
                                        Station d'arrivée
                                    </th>
                                    <th class="col-md-3">
                                        Réserver transfert
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                
                                 WebServicesConducteur_Service service = new WebServicesConducteur_Service();
                                 WebServicesConducteur port = service.getWebServicesConducteurPort();
                                 
                                 port.

                                    rstations = port.obtenirStations();
                                    if (rstations != null) {
                                        for (RStation r : rstations) {
                                            out.println("<tr>");
                                            out.println("<td> Station n° " + r.getId() + "</td>");
                                            out.println("<td>" + r.getNom() + "</td>");
                                            out.println("<td>" + r.getLocalisation() + "</td>");
                                            out.println("<td>" + r.getNbQuais() + "</td>");
                                            out.println("</form>");
                                            out.println("</tr>");
                                        }
                                    } else {

                                    }
                                %>
                            </tbody>
                        </table>  

                    </form>
        </div>
        <div class="footer">
            <p> &copy; 2018 Spacelib Company. All Rights Reserved</p>
        </div>
    </body>
</html>
