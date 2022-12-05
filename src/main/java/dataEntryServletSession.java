/*
  Name: Magdalena Dobinda
  Course: CNT 4714 Fall 2022
  Assignment title: Project 4 – A Servlet/JSP-based Multi-tiered Enterprise Application Using a Tomcat Container
  Date:  December 4th, 2022

  Class:  Enterprise Computing
*/


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@WebServlet(value = "/dataEntryServletSession")
public class dataEntryServletSession extends HttpServlet {

    public String filePath_root;
    public String filePath_client;
    public Properties rootUser;
    public Properties clientUser;

    public String dbString;
    public String query;
    public String secondQuery;
    public String snum;
    public String sname;
    public String status;
    public String city;

    public boolean firstLoad = true;
    public String test;
    public ResultSet resultSet;
    public ResultSetMetaData metadata;
    public int success;


    // reading in property files
    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;

        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            fis.close();
        }

        return prop;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // get connected to database as a dataentry-level user
        dbString = "";

        this.filePath_root = "root.properties";
        this.filePath_client = "client.properties";
        this.rootUser = readPropertiesFile("C:\\Users\\Minnie\\TestingProject2Pt3\\src\\main\\webapp\\root.properties");
        this.clientUser = readPropertiesFile("C:\\Users\\Minnie\\TestingProject2Pt3\\src\\main\\webapp\\client.properties");

        StringBuilder table = new StringBuilder();

        snum = request.getParameter("snum");
        sname = request.getParameter("sname");
        status = request.getParameter("status");
        city = request.getParameter("city");

        // insert into shipments
        //values ('S5', 'P6', 'J6', 400);

        query = "insert into suppliers values (\"" + snum + "\", \" " + sname + "\", " + status + ", \"" +city+ "\")" ;

        try
        {
            if (query == null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("dbString", "");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
                dispatcher.forward(request, response);

                firstLoad = false;

                return;
            }

            // driver is here
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(rootUser.getProperty("URL"), rootUser.getProperty("username"), rootUser.getProperty("password"));

            // we only need insert query code here
            if (query.charAt(0) == 'i' || query.charAt(0) == 'I')
            {
                try
                {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                    test = "INSERT";

                    if (query.contains("shipments"))
                    {
                        secondQuery = "update suppliers\n\tset status = status + 5\n\t\twhere snum in (select snum\n\t\t\tfrom shipments\n\t\t\twhere quantity > 100)";

                        success = statement.executeUpdate(secondQuery);

                        table.append("<tr><td id='succ'><center>Business Logic Detected! - Updating Supplier Status<br>Updated " + success + " supplier status marks.</center></td></tr>");
                        dbString = table.toString();

                        table = null;
                        firstLoad = false;
                    }
                    else
                    {
                        table.append( query +"<tr><td id='succ'><center>Successfully inserted!</center></td></tr>");
                        dbString = table.toString();

                        table = null;
                        firstLoad = false;
                    }
                }
                catch (SQLIntegrityConstraintViolationException e)
                {
                    table.append("<tr><td id='error'><center>Error: " + e + "!!!</center></td></tr>");
                    dbString = table.toString();

                    table = null;
                    firstLoad = false;
                }
            }
            else
            {
                return;
            }

        }
        catch (SQLException | ClassNotFoundException e)
        {
            if (firstLoad)
            {
                table.append("<tr><td></td></tr>");
                dbString = table.toString();

                table = null;
                firstLoad = false;
            }
            else
            {
                table.append("<tr><td id='error'>Error: " + e + "!!!!</td></tr>");
                dbString = table.toString();

                table = null;
                firstLoad = false;
            }
        }
        catch (IndexOutOfBoundsException ee)
        {
            table.append("<tr><td id='error'>Error: empty command line!</td></tr>");
            dbString = table.toString();

            table = null;
            firstLoad = false;
        } catch (ServletException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        session.setAttribute("dbString", dbString);
        session.setAttribute("query", query);
        session.setAttribute("test", test);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataEntryHome.jsp");
        dispatcher.forward(request, response);
    }

    // The doPost() method handles the execution of the user request, i.e., their SQL command
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }
}
