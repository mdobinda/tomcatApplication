<%--
  Created by IntelliJ IDEA.
  User: Minnie
  Date: 12/4/2022
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>

<%--
Name: Magdalena Dobinda
Course: CNT 4714 Fall 2022
Assignment title: Project 4 – A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container
Date:  December 4th, 2022

Class:  Enterprise Computing
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- start scriptlet --%>
<%
    String table = (String) session.getAttribute("dbString");
%>

<html lang="en">
<head>
    <title>CNT4714 Project 4 - Fall 2022 </title>
    <meta charset="utf-8">
    <style type="text/css">
        <!--
        body { background-color: #f2d7d9; color: #748da6; font-family: verdana, arial, sans-serif; font-size: 1.4em;  }
        input[type="submit"] {background-color: yellow; font-weight:bold;}
        textarea {background-color: #debace; color:white; font-size:20px; border-radius: 2%; border-color: #cca5bb; border-width: medium;}
        span {color:white;}
        #name {color:white; font-size:25px;}
        #welcome {color: #748da6; font-size:30px;}
        #subtitle {color: #9cb4cc; font-size:25px;}
        #title {color: #000000; font-size:15px;}
        #user {color: blue; font-size:15px;}
        #default {color:green; font-size:10px;}
        button {background-color: #748da6; color: white;
            padding: 12px 12px; border: none; border-radius: 8px; width:175px;}
        table, td {border: 1px solid white;}
        th {border: 3px solid white;}
        #tablehead {font-size:25px;}
        #error {background-color: #a20a0a; color:white; padding: 5px 5px; font-size:17px;}
        #succ {background-color: #799351; color:white; padding: 5px 5px; font-size:17px;}
        -->
    </style>

    <script src="reset.js"></script>
</head>

<body>

<div style="text-align: center;">
    <form action="/TestingProject2Pt3_war_exploded/rootServletSession" method="get">

        <h2 id="welcome">
            Welcome to the Fall 2022 Project 4 Enterprise Database System
        </h2>

        <h1 id="subtitle">
            A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container
        </h1>

        <img src="tomcat.png" width="20%" height="15%" alt="Tom and Jerry">
        <br>

        <label id="title">
            You are connected to the database as a <label id="user"> root-level </label> user.
            <br>
            Please enter any valid SQL query or update commands.
            <br>
        </label>
        <br>
        <textarea rows="7" cols="50" name="query">select * from suppliers</textarea>
        <br>
        <br>
        <button type="submit" class="button">Execute Command</button>
        <button type="reset" class="button">Reset Form</button>
        <button type="reset" class="button">Clear Results</button>
    </form>
</div>

</form>
<center>
    <h1 id='tablehead'>Database Results: </h1>
    <table id="resultstable">
        <%=table%>
    </table>
</center>
<br><br><br><br>
</body>
</html>