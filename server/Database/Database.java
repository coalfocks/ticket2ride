package server.Database;

import java.io.File;
import java.sql.*;

/**
 * Created by colefox on 2/6/17.
 */

public class Database
{
    Connection connection;

    public Database()
    {
        loadDriver();
        //DAO.getInstance().setDB(this);
    }
    /**
     * Load the driver to talk to the database
     */
    public void loadDriver()
    {
        try
        {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            System.out.print("Class Not found error\n");
        }
    }
    /**
     * Open a connection with the data base
     */
    public void openConnection()
    {
        //String dbName = "database.sqlite";
        File directory = new File("db");
        if(!directory.exists())
        {
            try
            {
                directory.mkdirs();
            }
            catch(SecurityException se)
            {
                System.out.println("Error creating DB folder... program will not execute");
                return;
            }
        }

        String dbName = "db" + File.separator + "database.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbName;
        connection = null;

        try
        {
            connection = DriverManager.getConnection(connectionURL);
            createIfNotExist();
        }
        catch(SQLException e)
        {
            System.out.print("SQL error\n");
            e.printStackTrace();
            System.out.print(e);
        }
        return;


    }

    public void startTransaction()
    {
        openConnection();
        try
        {
            connection.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            System.out.print("turn off auto commit error");
            e.printStackTrace();
        }
    }

    public void closeTransaction(boolean commit)
    {
        try
        {
            if(commit)
            {
                connection.commit();
            }
            else
            {
                connection.rollback();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.print("Close transaction commit error\n");
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (SQLException e) {
                System.out.println("Cant close connection");
                e.printStackTrace();
            }
        }
        connection = null;
    }

    public void createIfNotExist() throws SQLException
    {

        String sql = "CREATE TABLE IF NOT EXISTS USERS"+
                "("+
                "playerID INTEGER PRIMARY KEY autoincrement," +
                "username varchar(64),"+
                "password varchar(64),"+
                "inGame INTEGER" +
                ");";

        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.executeUpdate();

    }

}

