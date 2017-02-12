package server;


import server.Database.DAO;

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

    public boolean createPlayer()
    {
        return true;
    }

    public boolean createGame()
    {
        return true;
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

    public boolean getGames()
    {
        return true;
    }

    public boolean joinGame(int gameID, int playerID)
    {
        try
        {
            TTRGame game = dao.getGame(gameID);
            game.addPlayer(playerID);
            User player = dao.getUser(playerID);
            if (dao.updatePlayerGame(gameID, playerID))
            {
                player.setInGame(gameID);
            }
        } catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
