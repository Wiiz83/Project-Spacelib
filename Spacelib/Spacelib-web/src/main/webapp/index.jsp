<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Server is running !</h1>
        
        
        <%
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:derby://localhost:1527/SpacelibDB";
            String username="SpacelibDB";
            String password="SpacelibDB";
            String query="SELECT * FROM MECANICIEN";
            Connection conn=DriverManager.getConnection(url,username,password);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
        {

        %>
            <tr><td><%rs.getInt("ID"); %></td></tr>
            <tr><td><%rs.getString("LOGIN"); %></td></tr>
            <tr><td><%rs.getString("NOM"); %></td></tr>
                <%

        }
        %>
            </table>
            <%
            rs.close();
            stmt.close();
            conn.close();
            }
        catch(Exception e)
        {
            e.printStackTrace();
            }




        %>

        
        
        
        
        
        
        
    </body>
</html>
