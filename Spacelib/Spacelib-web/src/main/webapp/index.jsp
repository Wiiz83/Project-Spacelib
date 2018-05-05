<%@page import="java.util.List"%>
<%@page import="com.miage.spacelib.services.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
         /*
           WebServicesGetData service = new WebServicesGetData();
         List<com.miage.spacelib.entities.Mecanicien> mecaniciens = service.getMecanos();
        
        for (com.miage.spacelib.entities.Mecanicien m : mecaniciens) {
            out.println(m.getNom());
        }
*/
         WSTest service = new WSTest();
         List<com.miage.spacelib.entities.Mecanicien> mecaniciens = service.findAll();
        
        for (com.miage.spacelib.entities.Mecanicien m : mecaniciens) {
            out.println(m.getNom());
        }

        %>
    </body>
</html>
