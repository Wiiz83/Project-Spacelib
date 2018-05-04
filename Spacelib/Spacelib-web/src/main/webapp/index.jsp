<%@page import="java.util.List"%>
<%@page import="com.miage.spacelib.services.WebServicesGetData"%>
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
         WebServicesGetData service = new WebServicesGetData();
         List<com.miage.spacelib.entities.Station> stations = service.getStations();
        
        for (com.miage.spacelib.entities.Station s : stations) {
            out.println(s.getNom());
        }
        %>
    </body>
</html>
