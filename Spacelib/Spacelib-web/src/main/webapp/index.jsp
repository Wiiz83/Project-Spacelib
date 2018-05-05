<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello :)</title>
    </head>
    <style>
        table {
            border-collapse: collapse;
        }

        table, td, th {
            border: 1px solid black;
        }
    </style>
    <body>
        <h1>Server is running !</h1>
        <table>
            <%
                try {
                    InitialContext ctx = new InitialContext();
                    DataSource ds = (DataSource) ctx.lookup("java:app/jdbc/SpacelibDataSource");
                    Connection conn = ds.getConnection();
                    Statement stmt = conn.createStatement();
            %>

            
            <p>Liste des administrateurs :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de création</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                </tr>
                <%
                    ResultSet rs = stmt.executeQuery("SELECT * FROM ADMINISTRATEUR");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            <p>Liste des conducteurs :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de création</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM CONDUCTEUR");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            <p>Liste des mécaniciens :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de création</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM MECANICIEN");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }

                %>
            </table>
            
            
            
            <p>Liste des navettes :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Nombre de places</th>
                    <th>Nombre de voyages restants</th>
                    <th>Statut</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM NAVETTE");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getInt("NBPLACES")%></td>
                    <td><%= rs.getInt("NBVOYAGES")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                </tr>
                <%
                    }

                %>
            </table>
            
            
            
            <p>Liste des opérations :</p>
            <table>
                <tr>
                    <th>Identifiant de navette</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM OPERATION");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("NAVETTE_ID")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            

            <p>Liste des quais :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Statut</th>
                    <th>Identifiant de station</th>
                    <th>Identifiant de navette</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM QUAI");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                    <td><%= rs.getInt("STATIONID")%></td>
                    <td><%= rs.getInt("NAVETTEID")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            <p>Liste des réservations :</p>
            <table>
                <tr>
                    <th>Identifiant de navette</th>
                    <th>Identifiant du quai d'arrivée</th>
                    <th>Identifiant du quai de départ</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM RESERVATION");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("NAVETTE_ID")%></td>
                    <td><%= rs.getInt("QUAIARRIVEE_ID")%></td>
                    <td><%= rs.getInt("QUAIDEPART_ID")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            <p>Liste des révisions :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de création</th>
                    <th>Statut</th>
                    <th>Identifiant du mécanicien</th>
                    <th>Identifiant de la navette</th>
                    <th>Identifiant du quai</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM REVISION");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATECREATION")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                    <td><%= rs.getInt("MECANICIEN_ID")%></td>
                    <td><%= rs.getInt("NAVETTE_ID")%></td>
                    <td><%= rs.getInt("QUAI_ID")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            <p>Liste des stations :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Localisation</th>
                    <th>Nombre de quais</th>
                    <th>Nom de station</th>
                    <th>Statut</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM STATION");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("LOCALISATION")%></td>
                    <td><%= rs.getInt("NOMBRE_QUAIS")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("STATUT")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            <p>Liste des transferts :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date d'arrivée</th>
                    <th>Date de création</th>
                    <th>Date de départ</th>
                    <th>Nombre de passagers</th>
                    <th>Statut</th>
                    <th>Identifiant du conducteur</th>
                    <th>Identifiant de la navette</th>
                    <th>Identifiant du quai d'arrivée</th>
                    <th>Identifiant du quai de départ</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM TRANSFERT");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATEARRIVEE")%></td>
                    <td><%= rs.getString("DATECREATION")%></td>
                    <td><%= rs.getInt("DATEDEPART")%></td>
                    <td><%= rs.getInt("NBPASSAGERS")%></td>
                    <td><%= rs.getInt("STATUT")%></td>
                    <td><%= rs.getString("CONDUCTEURID")%></td>
                    <td><%= rs.getString("NAVETTE_ID")%></td>
                    <td><%= rs.getInt("QUAIARRIVEE_ID")%></td>
                    <td><%= rs.getInt("QUAIDEPART_ID")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            
            <p>Liste des usagers :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date de création</th>
                    <th>Login</th>
                    <th>Mot de passe</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM USAGER");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATE_CREATION")%></td>
                    <td><%= rs.getString("LOGIN")%></td>
                    <td><%= rs.getString("MOT_DE_PASSE")%></td>
                    <td><%= rs.getString("NOM")%></td>
                    <td><%= rs.getString("PRENOM")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            
            
            
            <p>Liste des voyages :</p>
            <table>
                <tr>
                    <th>Identifiant</th>
                    <th>Date d'arrivée</th>
                    <th>Date de création</th>
                    <th>Date de départ</th>
                    <th>Nombre de passagers</th>
                    <th>Statut</th>
                    <th>Identifiant de l'usager</th>
                    <th>Identifiant de la navette</th>
                    <th>Identifiant du quai d'arrivée</th>
                    <th>Identifiant du quai de départ</th>
                </tr>
                <%
                    rs = stmt.executeQuery("SELECT * FROM TRANSFERT");
                    while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getInt("ID")%></td>
                    <td><%= rs.getString("DATEARRIVEE")%></td>
                    <td><%= rs.getString("DATECREATION")%></td>
                    <td><%= rs.getInt("DATEDEPART")%></td>
                    <td><%= rs.getInt("NBPASSAGERS")%></td>
                    <td><%= rs.getInt("STATUT")%></td>
                    <td><%= rs.getString("USAGER_ID")%></td>
                    <td><%= rs.getString("NAVETTE_ID")%></td>
                    <td><%= rs.getInt("QUAIARRIVEE_ID")%></td>
                    <td><%= rs.getInt("QUAIDEPART_ID")%></td>
                </tr>
                <%
                    }
                %>
            </table>
            
            
            <%
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
    </body>
</html>
