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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.Properties;

@WebServlet(value = "/clientServletSession")
public class clientServletSession extends HttpServlet {

    String filePath_root;
    String filePath_client;
    Properties rootUser;
    Properties clientUser;

    public String dbString;
    public String query;
    public String secondQuery;
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
        // get connected to database as a root-level user
        dbString = "";
        filePath_root = "root.properties";
        filePath_client = "client.properties";
        rootUser = readPropertiesFile("C:\\Users\\Minnie\\TestingProject2Pt3\\src\\main\\webapp\\root.properties");
        clientUser = readPropertiesFile("C:\\Users\\Minnie\\TestingProject2Pt3\\src\\main\\webapp\\client.properties");

        StringBuilder table = new StringBuilder();
        query = request.getParameter("query");

        try
        {
            if (query == null)
            {
                HttpSession session = request.getSession();
                session.setAttribute("dbString", "");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientHome.jsp");
                dispatcher.forward(request, response);

                firstLoad = false;

                return;
            }

            // driver here
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(clientUser.getProperty("URL"), clientUser.getProperty("username"), clientUser.getProperty("password"));

            //insert query
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
                        table.append("<tr><td id='succ'><center>Successfully inserted!</center></td></tr>");
                        dbString = table.toString();

                        table = null;
                        firstLoad = false;
                    }
                }
                catch (SQLIntegrityConstraintViolationException e)
                {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);

                    table.append("<tr><td id='error'><center>Error: " + sw.toString()  + "!!!</center></td></tr>");
                    e.printStackTrace();
                    dbString = table.toString();

                    table = null;
                    firstLoad = false;
                }
            }
            //delete query
            else if (query.charAt(0) == 'd' || query.charAt(0) == 'D')
            {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                test = "DELETE";
            }
            //update query
            else if (query.charAt(0) == 'u' || query.charAt(0) == 'U')
            {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                test = "UPDATE";

                //business logic

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
                    table.append("<tr><td id='succ'><center>Successfully updated!</center></td></tr>");
                    dbString = table.toString();

                    table = null;
                    firstLoad = false;
                }
            }
            else if (query.charAt(0) == 's' || query.charAt(0) == 'S')
            {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                metadata = resultSet.getMetaData();

                int col = metadata.getColumnCount();

                table.append("<tr>");

                for (int i = 1; i <= col; i++)
                {
                    String colName = metadata.getColumnName(i);
                    table.append("<th>" + colName + "</th>");
                }

                table.append("</tr>");

                while (resultSet.next())
                {
                    String info = "";
                    table.append("<tr>");
                    for (int i = 1; i <= col; i++)
                    {
                        info = resultSet.getString(i);
                        table.append("<td>" + info + "</td>");
                    }
                    table.append("</tr>");
                }

                dbString = table.toString();

                table = null;
            }
            // replace query
            else if(query.charAt(0) == 'r' || query.charAt(0) == 'R') {
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
                        table.append("<tr><td id='succ'><center>Successfully inserted!</center></td></tr>");
                        dbString = table.toString();

                        table = null;
                        firstLoad = false;
                    }
                }
                catch (SQLIntegrityConstraintViolationException e)
                {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);

                    table.append("<tr><td id='error'><center>Error: " + sw.toString()  + "!!!</center></td></tr>");
                    e.printStackTrace();
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
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);

                table.append("<tr><td>testing update error" + sw.toString() + "</td></tr>");
                dbString = table.toString();

                table = null;
                firstLoad = false;
            }
            else
            {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);

                table.append("<tr><td id='error'>Error: " + sw.toString() + " !!!!</td></tr>");
                e.printStackTrace();
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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientHome.jsp");
        dispatcher.forward(request, response);
    }

    // The doPost() method handles the execution of the user request, i.e., their SQL command
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request, response);
    }
}
