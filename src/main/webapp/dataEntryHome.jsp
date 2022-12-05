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
    String query = (String) session.getAttribute("query");
    String snum = (String) session.getAttribute("snum");
    String sname = (String) session.getAttribute("sname");
    String status = (String) session.getAttribute("status");
    String city = (String) session.getAttribute("city");


    String table = (String) session.getAttribute("dbString");
%>

<html lang="en">
<head>
    <title>CNT4714 Project 4 - Fall 2022 </title>
    <meta charset="utf-8">
    <style type="text/css">
        <!--
        body { background-color: #fff8ea; color: #594545; font-family: verdana, arial, sans-serif; font-size: 1.4em;  }
        input[type="submit"] {background-color: yellow; font-weight:bold;}
        textarea {background-color: #debace; color:white; font-size:20px; border-radius: 2%; border-color: #cca5bb; border-width: medium;}
        span {color:white;}
        #name {color:white; font-size:25px;}
        #welcome {color: #594545; font-size:30px;}
        #subtitle {color: #815b5b; font-size:25px;}
        #title {color: #000000; font-size:15px;}
        #user {color: purple; font-size:15px;}
        #default {color:green; font-size:10px;}
        button {background-color: #9e7676; color: white;
            padding: 12px 12px; border: none; border-radius: 8px; width:250px; margin-top: 0.5%;}
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
<center>
    <form action="/TestingProject2Pt3_war_exploded/dataEntryServletSession" method="get">

        <h2 id="welcome">
            Welcome to the Fall 2022 Project 4 Enterprise Database System
        </h2>

        <h1 id="subtitle">
            A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container
        </h1>

        <img src="tomcat.png" width="20%" height="15%" alt="Tom and Jerry">
        <br>

        <label id="title">
            You are connected to the database as a <label id="user"> data-entry-level </label> user.
            <br>
            Please enter any valid SQL query or update commands.
        </label>


    </form>
</center>

<form action="/TestingProject2Pt3_war_exploded/dataEntryServletSession" method="get">
    <div id="supplier">

        <center>
            <p id='supplierTitle' style="font-size: small">Supplier Record Insert</p>
            <table id="supplyTable" style="text-align: center">
                <tr>
                    <th>snum</th>
                    <th>sname</th>
                    <th>status</th>
                    <th>city</th>
                </tr>
                <tr>
                    <td> <input type="text" name="snum"> </td>
                    <td> <input type="text" name="sname"> </td>
                    <td> <input type="text" name="status">  </td>
                    <td> <input type="text" name="city">  </td>
                </tr>
            </table>

            <button type="submit" class="button">Enter Supplier Record Into Database</button>
            <button type="reset" class="button">Clear Data and Results</button>
        </center>
    </div>

</form>

<form action="/TestingProject2Pt3_war_exploded/partsRecordInsertServlet" method="get">
    <div id="parts">

        <center>
            <p id='partsTitle' style="font-size: small">Part Record Insert</p>
            <table id="partsTable" style="text-align: center">
                <tr>
                    <th>pnum</th>
                    <th>pname</th>
                    <th>color</th>
                    <th>weight</th>
                    <th>weight</th>
                </tr>
                <tr>
                    <td> <input type="text" name="pnum"> </td>
                    <td> <input type="text" name="pname"> </td>
                    <td> <input type="text" name="color">  </td>
                    <td> <input type="text" name="weight">  </td>
                    <td> <input type="text" name="city">  </td>
                </tr>
            </table>

            <button type="submit" class="button">Enter Part Record Into Database</button>
            <button type="reset" class="button">Clear Data and Results</button>
        </center>
    </div>

</form>

<form action="/TestingProject2Pt3_war_exploded/jobRecordInsertServlet" method="get">
    <div id="job">

        <center>
            <p id='jobTitle' style="font-size: small">Jobs Record Insert</p>
            <table id="jobTable" style="text-align: center">
                <tr>
                    <th>jnum</th>
                    <th>jname</th>
                    <th>numworkers</th>
                    <th>city</th>
                </tr>
                <tr>
                    <td> <input type="text" name="jnum"> </td>
                    <td> <input type="text" name="jname"> </td>
                    <td> <input type="text" name="numworkers">  </td>
                    <td> <input type="text" name="city">  </td>
                </tr>
            </table>

            <button type="submit" class="button">Enter Job Record Into Database</button>
            <button type="reset" class="button">Clear Data and Results</button>
        </center>
    </div>

</form>

<form action="/TestingProject2Pt3_war_exploded/shipmentRecordInsertServlet" method="get">
    <div id="shipment">

        <center>
            <p id='shipmentTitle' style="font-size: small">Shipment Record Insert</p>
            <table id="shipmentTable" style="text-align: center">
                <tr>
                    <th>snum</th>
                    <th>pnum</th>
                    <th>jnum</th>
                    <th>quantity</th>
                </tr>
                <tr>
                    <td> <input type="text" name="snum2"> </td>
                    <td> <input type="text" name="pnum2"> </td>
                    <td> <input type="text" name="jnum">  </td>
                    <td> <input type="text" name="quantity">  </td>
                </tr>
            </table>

            <button type="submit" class="button">Enter Shipment Record Into Database</button>
            <button type="reset" class="button">Clear Data and Results</button>
        </center>
    </div>

</form>




<center>
    <h1 id='tablehead'>Database Results</h1>
    <table id="resultstable">
        <%=table%>
    </table>
</center>
<br><br><br><br>
</body>
</html>