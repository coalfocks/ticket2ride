package server;


import server.Database.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by colefox on 2/6/17.
 */
public class GameUserManager
{
    private static GameUserManager instance;
    private DAO dao = DAO.getInstance();

    private GameUserManager(){}

    public static GameUserManager getInstance()
    {
        if (instance == null)
        {
            instance = new GameUserManager();
        }
        return instance;
    }

    public User getUser(String s)
    {
        User user = null;
        try
        {
             user = dao.getUser(s);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     * The method to create a new user
     * @param username The username of the new user
     * @param password The password of the new user
     * @return True if successful, false otherwise
     */
    public boolean addUser(String username, String password)
    {
        try
        {
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);

            dao.addUser(u);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * The method to create a new game
     * @param ownerID The playerID of the user calling the method
     * @return The unique gameID, or 0 if failure
     */
    public int createGame(int ownerID)
    {
        try
        {
            int gameID = dao.createGame(ownerID);
            return gameID;

        } catch (Exception e)
        {
            return 0;
        }
    }

    public ArrayList<TTRGame> getGames()
    {
        return dao.getGames();
    }

    /**
     * The method to join a listed game
     * @param gameID The ID of the game to join
     * @param playerID The ID of the player joinging
     * @return True if successful, false otherwise
     */
    public boolean joinGame(int gameID, int playerID)
    {
        try
        {
            if (dao.getGameStatus(gameID) == 1)
            {
                return false;
            }
            TTRGame game = dao.getGame(gameID);
            game.addPlayer(playerID);
            dao.addPlayerToGame(gameID, Serializer.serialize(game));
            User player = dao.getUser(playerID);
            if (dao.updatePlayerGame(gameID, playerID))
            {
                player.setInGame(gameID);
            }
            if (game.getNumPlayers() >= 5)
            {
                startGame(game.getOwnerID());
            }
        } catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public TTRGame getGame(int gameID)
    {
        return dao.getGame(gameID);
    }

    public boolean startGame(int ownerID)
    {
        return dao.startGame(ownerID);
    }

    public boolean endGame(int ownerID)
    {
        return dao.endGame(ownerID);
    }
}
