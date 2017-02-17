package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.DataTransferObject;
import com.google.gson.Gson;
import server.Database.Database;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by colefox on 2/5/17.
 */
public class TTRServerFacade implements iTTRServer
{
    private GameUserManager gameUserManager = GameUserManager.getInstance();
    private TTRGameServer gameServer = TTRGameServer.getInstance();
    private Gson gson = new Gson();

    /**
     * The method called by the CreateGameCommand
     * @param data Data needed to create a game
     * @return Data about the new created game
     */
    @Override
    public DataTransferObject createGame(DataTransferObject data)
    {

        int gameID = gameUserManager.createGame(data.getPlayerID());
        data.setData(String.valueOf(gameID));
        return joinGame(data);

    }

    /**
     * The method called by the StartGameCommand
     * @param data Data needed to start the game
     * @return Data about the attempted start
     */
    @Override
    public DataTransferObject startGame(DataTransferObject data)
    {
        if (gameUserManager.startGame(data.getPlayerID()))
        {
            try
            {
                TTRGame game = gameUserManager.getGame(data.getPlayerID());
                String gstring = Serializer.serialize(game);
                data.setData(gstring);
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            data.setErrorMsg("An error occurred, couldn't start game");
        }
        return data;
    }

    /**
     * The method called by the EndGameCommand
     * @param data Data needed to end the game
     * @return Data about the state of the game
     */
    @Override
    public DataTransferObject endGame(DataTransferObject data)
    {
        if (gameUserManager.endGame(data.getPlayerID()))
        {
            try
            {
                data.setData("Ended game");
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            data.setErrorMsg("An error occurred, couldn't start game");
        }
        return data;
    }

    /**
     * The method called by the JoinGameCommand
     * @param data Data needed to join the particular game
     * @return Data containing updated game information
     */
    @Override
    public DataTransferObject joinGame(DataTransferObject data)
    {
        int gameID = Integer.parseInt(data.getData());
        boolean added = gameUserManager.joinGame(gameID, data.getPlayerID());
        if(added)
        {
            data.setData("Player " + data.getPlayerID() + " joined game " + gameID);
        }
        else
        {
            data.setData("");
            data.setErrorMsg("An error occurred, game not joined");
        }
        return data;
    }

    /**
     * The method called by the LoginCommand
     * @param data Data needed to login
     * @return Data containing the things the server wanted to give
     */
    @Override
    public DataTransferObject login(DataTransferObject data)
    {
        DataTransferObject userInfo = new DataTransferObject();
        try
        {
            User loginUser = gson.fromJson(data.getData(), User.class);
            User u = gameUserManager.getUser(loginUser.getUsername());
            if (u != null && Objects.equals(loginUser.getPassword(), u.getPassword()))
            {
                u.setPassword("GOOD");
                userInfo.setData(gson.toJson(u));
                userInfo.setPlayerID(u.getPlayerID());
            }
            else
            {
                userInfo.setErrorMsg("Invalid username or password");
            }
        } catch (Exception e)
        {
            userInfo.setErrorMsg(e.getMessage());
        }

        return userInfo;
    }

    /**
     * The method called by the RegisterCommand
     * @param data Data needed to register
     * @return Data containing the newly registered user
     */
    @Override
    public DataTransferObject register(DataTransferObject data)
    {
        DataTransferObject userInfo = new DataTransferObject();
        try
        {
            JsonParser jsonParser = new JsonParser();
            JsonObject o = jsonParser.parse(data.getData()).getAsJsonObject();
            String password = o.get("password").getAsString();
            String username = o.get("username").getAsString();
            User exists = gameUserManager.getUser(username);
            if (exists != null)
            {
                userInfo.setErrorMsg("User already exists!");
            }
            else
            {
                gameUserManager.addUser(username, password);
                userInfo.setData("User created!");
            }
        } catch (Exception e)
        {
            userInfo.setErrorMsg(e.getMessage());
        }

        return userInfo;
    }

    public DataTransferObject listGames(DataTransferObject data)
    {
        try
        {
            ArrayList<TTRGame> games = gameUserManager.getGames();
            data.setData(Serializer.serialize(games));
            return data;
        } catch(Exception e)
        {
            e.printStackTrace();
            data.setErrorMsg("An error occurred - could not get games");
            return data;
        }
    }

}
