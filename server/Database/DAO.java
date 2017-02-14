package server.Database;

/**
 * Created by colefox on 2/6/17.
 */
import server.Serializer;
import server.TTRGame;
import server.User;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


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
           }
           return user;
       }

        public ArrayList<TTRGame> getGames()
        {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            TTRGame game = null;
            ArrayList<TTRGame> games = new ArrayList<>();
            try
            {
                db.startTransaction();
                String sql = "SELECT * FROM games";
                stmt = db.connection.prepareStatement(sql);
                rs = stmt.executeQuery();

                while (rs.next())
                {
                    String g = rs.getString(4);
                    game = (TTRGame) Serializer.deserialize(g);
                    games.add(game);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }  catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (stmt != null)
                        stmt.close();
                    if (rs != null)
                        rs.close();
                    db.closeTransaction(true);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            return games;
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
               String sql = "INSERT IGNORE INTO users (username, password, inGame)" +
                       "VALUES (" + u.getUsername() + ", " + u.getPassword() + ", 0);";
               stmt = db.connection.prepareStatement(sql);
               stmt.executeUpdate();

           }
           catch(SQLException e)
           {
               System.out.println(e.getMessage());
               return false;
           }

           return true;

       }

}
