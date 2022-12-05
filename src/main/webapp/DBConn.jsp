<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>

<%--
  Created by IntelliJ IDEA.
  User: Minnie
  Date: 12/3/2022
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>

<%--Just used this page to test database connections initially!--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%

    Statement stmt;
    Connection con;
    String url = "jdbc:mysql://localhost:3306/project4";

    try {

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, "root", "root");
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from suppliers;");
        while(rs.next()) {
            out.println(rs.getString(1)+ " "+rs.getString(2)+" "+rs.getString(3)+"<br>");

        }

        con.close();

    }

    catch (Exception ex) {
        out.println(ex);
    }

%>

</body>
</html>
