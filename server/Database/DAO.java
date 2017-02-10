package server.Database;

/**
 * Created by colefox on 2/6/17.
 */
import server.User;

import java.awt.*;
import java.sql.*;


    public class DAO
    {
        private static DAO instance;
        private static Database db;
        private static Cursor cursor;


        private DAO()
        {
            this.db = new Database();
        }

        /**
         * gets the instance, if exists. if not, creates instance and initializes it
         *
         * @return the instance
         */
        public static DAO getInstance()
        {
            if (instance == null)
            {
                instance = new DAO();
            }
            return instance;
        }
        //set db method
        public void setDB(Database sqLiteDatabase)
        {
            this.db = sqLiteDatabase;
        }

        public Database getDB()
        {
            return this.db;
        }

       public User getUser(String username) throws SQLException
       {
           if (username == null)
           {
               return null;
           }

           PreparedStatement stmt = null;
           ResultSet rs = null;
           User user = null;
           try
           {
               db.startTransaction();
               String sql = "SELECT * FROM users WHERE users.username = ?";
               stmt = db.connection.prepareStatement(sql);
               stmt.setString(1, username);
               rs = stmt.executeQuery();

               while(rs.next())
               {
                   user = new User();
                   user.setUsername(rs.getString(2));
                   user.setPassword(rs.getString(3));
                   user.setPlayerID(rs.getInt(1));
                   user.setInGame(rs.getInt(4));
               }
           }
           catch(SQLException e)
           {
               System.out.println(e.getMessage());
           }
           finally
           {
               if(stmt != null)
                   stmt.close();
               if (rs != null)
                   rs.close();
               db.closeTransaction(true);
           }
           return user;
       }

       public boolean addUser(User u)
       {
           if (u == null)
           {
               return false;
           }

           PreparedStatement stmt = null;
           try
           {
               String sql = "INSERT OR IGNORE INTO users (username, password, inGame)" +
                       "VALUES (?,?,?" +
                        ");";
               db.startTransaction();
               stmt = db.connection.prepareStatement(sql);
               stmt.setString(1, u.getUsername());
               stmt.setString(2, u.getPassword());
               stmt.setInt(3, 0);

               stmt.executeUpdate();

           }
           catch(SQLException e)
           {
               System.out.println(e.getMessage());
               return false;
           }
           db.closeTransaction(true);

           return true;

       }

}
