package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.DataTransferObject;
import com.google.gson.Gson;
import server.Database.Database;

import javax.xml.crypto.Data;
import java.util.Objects;

/**
 * Created by colefox on 2/5/17.
 */
public class TTRServerFacade implements iTTRServer
{
    private GameUserManager gameUserManager = GameUserManager.getInstance();
    private TTRGameServer gameServer = TTRGameServer.getInstance();
    private Gson gson = new Gson();

    @Override
    public DataTransferObject createGame(DataTransferObject data)
    {

        int gameID = gameUserManager.createGame(data.getPlayerID());
        data.setData(String.valueOf(gameID));
        return joinGame(data);

    }

    @Override
    public DataTransferObject startGame(DataTransferObject data)
    {
        //Game g = db.getGame
        //g.start()
        return null;
    }

    @Override
    public DataTransferObject endGame(DataTransferObject data)
    {
        //Game g = db.getGame
        //g.end()
        return null;
    }

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

}
