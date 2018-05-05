<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Server is running !</h1>
          <table>
        <%
        try
        {
            InitialContext ctx = new InitialContext();
            DataSource  ds = (DataSource) ctx.lookup("java:app/jdbc/SpacelibDataSource");
            Connection  conn = ds.getConnection();
            Statement  stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM MECANICIEN");
            while(rs.next()){
        %>
            <tr>
                <td><%= rs.getInt("ID") %></td>
                <td><%= rs.getString("LOGIN") %></td>
                <td><%= rs.getString("NOM") %></td>
            </tr>
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
