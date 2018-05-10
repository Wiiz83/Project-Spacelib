<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Web Usagers</title>
        <link href="css/style.css" rel='stylesheet' type='text/css'/>
        <link href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Simple Tab Forms Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

        <script src="js/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#horizontalTab').easyResponsiveTabs({
                    type: 'default', //Types: default, vertical, accordion           
                    width: 'auto', //auto or any width like 600px
                    fit: true   // 100% fit in a container
                });
            });
        </script>
    </head>
    <body>
        
        <%
            session.setAttribute("idStation", null);
            session.setAttribute("idMecanicien", null);
        %>
        <script src='//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script><script src="//m.servedby-buysellads.com/monetization.js" type="text/javascript"></script>
        <h1><img src="images/logo.png" alt="Spacelib logo" height="90px"></h1>
        <!---728x90--->
        <script src='//publisher.eboundservices.com/dynamicAds/dynamicScript.js'></script>
        <div class="main-content">

            <div class="right-w3">
                <div class="sap_tabs">	
                    <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
                        <ul>
                            <li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>Se connecter</span></li>
                            <li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>S'enregistrer</span></li>
                            <div class="clear"></div>
                        </ul>
                        <div class="agile-tb glass">
                            <%
                                String msginfo = (String) request.getAttribute("messageErreur");
                                if (msginfo != null) {
                                    out.println("<p style='color:white; font-weight: bold; padding: 10px;'>" + msginfo + "</p>");
                                }
                                
                                String numeroQuai = String.valueOf(request.getAttribute("idQuai"));
                                if (numeroQuai != null && !numeroQuai.isEmpty() && numeroQuai != "null") {
                                    out.println("<p style='background-color: green; color:white; font-weight: bold; padding: 10px;'>La navette se trouve au quai numéro " + numeroQuai + "</p></br>");
                                }
                            %>
                            <div class="tab-2 resp-tab-content" aria-labelledby="tab_item-0">
                                <form method="post" action="Login">					
                                    <input placeholder="Login" name="login" class="mail" type="text" required>
                                    <input placeholder="Mot de passe" name="motdepasse" class="lock" type="password" required>

                                    <select name="idStation"> 
                                        <%
                                           
                                            Iterator it = Login.getStationsList().iterator();
                                            String v = null;

                                            while (it.hasNext () 
                                                ) {
                                                Station station = (Station) it.next();
                                                v = station.getNom();
                                        %>	
                                        <option value="<%= station.getId()%>"> <%= v%></option> 
                                        <%
                                            }
                                        %> 
                                    </select>

                                    <input type="submit" value="Connexion"/>
                                </form>
                            </div>
                            <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
                                <form method="post" action="Inscription" >				
                                    <input placeholder="Nom" name="nom" type="text" required>
                                    <input placeholder="Prénom"name="prenom" type="text" required>						
                                    <input placeholder="Login" name="login" type="text" required>	
                                    <input placeholder="Mot de passe" name="motdepasse" type="password" required>
                                    <input type="submit" value="Inscription"/>
                                </form>
                            </div>	
                        </div>
                    </div> 
                </div> 			        					            	      
            </div>	
        </div>
        <!---728x90--->
        <script src='//publisher.eboundservices.com/dynamicAds/dynamicScript.js'></script>
        <div class="footer">
            <p> &copy; 2018 Spacelib Company. All Rights Reserved</p>
        </div>
        <!---728x90--->
        <script src='//publisher.eboundservices.com/dynamicAds/dynamicScript.js'></script>
        <script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
    </body>
</html>