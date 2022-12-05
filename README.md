# tomcatApplication

 A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container
 
 This project is a distrubuted three-tier web-based application which uses servlets and JSP technology running on a Tomcat container/server to access and maintain a persistent MySQL database using JDBC.
 
 The first-tier handles root-level, client-level and data-entry-level clients on their own respective JSP pages to to enter arbitrary 
SQL commands into a window (i.e. a form) and submit them to a server application for processing.

The second-tier servlets are in charge of handling the SQL command interface for the users. 

The third-tier (back-end) is the persistent MySQL database described above and is under control of the 
MySQL DBMS server. This database was created on my own and I will upload the files later on in case you want to try running and adding to this project. 

