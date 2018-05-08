<%@page import="java.util.logging.Level"%>
<%@page import="com.miage.spacelibmecanicien.model.*"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.List"%>
<%@page import="com.miage.spacelibmecanicien.model.Navette"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.miage.spacelibmecanicien.servlets.DebutRevision"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web Mécaniciens</title>
        <link href="css/style.css" rel='stylesheet' type='text/css'/>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Simple Tab Forms Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />

    </head>
    <body>
        <%
            if(session.getAttribute("idStation") == null || session.getAttribute("idMecanicien") == null){
                request.setAttribute("messageErreur", "Erreur : Merci de vous connecter");
                RequestDispatcher rd = request.getRequestDispatcher("Index");
                rd.forward(request, response);
            }
        %>
        <h1><a href="index.jsp"><img src="images/logo.png" alt="Spacelib logo" height="90px"></a></h1>
        <h3 style='color:white; font-size: 20px; font-weight: bold; padding: 10px;'>Navettes nécessitant une révision</h3>
        <div class="main-content-nologin">
                    <form method="post" action="DebutRevision">
                        <table class="table table-bordered" align="center" style="justify-content:center;align-items:center;width:100%;height:100%;">
                            <thead>
                                <tr>
                                    <th class="col-md-3">
                                        Numéro de navette
                                    </th>
                                    <th class="col-md-3"> 
                                        Numéro de quai
                                    </th>
                                    <th class="col-md-3">
                                        En attente depuis
                                    </th>
                                    <th class="col-md-3">
                                        Début de révision
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if (request.getAttribute("idStation") != null) {

                                        String stringToConvert = String.valueOf(request.getAttribute("idStation"));
                                        Long convertedLong = Long.parseLong(stringToConvert);

                                        try {
                                            List<Revision> revisions = DebutRevision.getNavetteList(convertedLong);
                                            for (Revision r : revisions) {
                                                out.println("<tr>");
                                                out.println("<td> Navette " + r.getNavette().getId() + "</td>");
                                                out.println("<td> Quai " + r.getQuai().getId() + "</td>");
                                                out.println("<td>" + r.getDateCreation() + "</td>");
                                                out.println("<td><button type='submit' class='btn btn-primary' name='idNavette' value='" + r.getNavette().getId() + "'><span class='glyphicon glyphicon-wrench' aria-hidden='true'></span></button></td>");
                                                out.println("</form>");
                                                out.println("</tr>");
                                            }
                                        } catch (NavettePourQuaiInexistantException_Exception ex) {
                                            Logger.getLogger(DebutRevision.class.getName()).log(Level.SEVERE, null, ex);
                                            out.println("<p style='color:white; font-weight: bold; padding: 10px;'>" + ex.getMessage() + "</p>");
                                        } catch (QuaiInexistantException_Exception ex) {
                                            Logger.getLogger(DebutRevision.class.getName()).log(Level.SEVERE, null, ex);
                                            out.println("<p style='color:white; font-weight: bold; padding: 10px;'>" + ex.getMessage() + "</p>");
                                        } catch (RevisionInexistanteException_Exception ex) {
                                            Logger.getLogger(DebutRevision.class.getName()).log(Level.SEVERE, null, ex);
                                            out.println("<p style='color:white; font-weight: bold; padding: 10px;'>" + ex.getMessage() + "</p>");
                                        } catch (StationInconnuException_Exception ex) {
                                            Logger.getLogger(DebutRevision.class.getName()).log(Level.SEVERE, null, ex);
                                            out.println("<p style='color:white; font-weight: bold; padding: 10px;'>" + ex.getMessage() + "</p>");
                                        }
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
