<%@page import="com.miage.spacelibmecanicien.model.WebServicesMecanicien_Service"%>
<%@page import="com.miage.spacelibmecanicien.model.WebServicesMecanicien"%>
<%@page import="com.miage.spacelibmecanicien.controller.DebutRevision"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        prout : 
        <%
         out.println(DebutRevision.getStuff());
         
         WebServicesMecanicien_Service service = new WebServicesMecanicien_Service();
         WebServicesMecanicien port = service.getWebServicesMecanicienPort();
         
         out.println(port.toto());


        %>
        
        
        
        
    </body>
</html>
