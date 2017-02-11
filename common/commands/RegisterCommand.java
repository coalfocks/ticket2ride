package common.commands;

import com.google.gson.JsonObject;
import common.Command;
import common.DataTransferObject;
import common.iCommand;
import server.Database.DAO;
import server.User;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 */
public class RegisterCommand extends Command implements iCommand, Serializable
{
    @Override
    public DataTransferObject execute(DataTransferObject dto)
    {
        DAO dao = DAO.getInstance();
        DataTransferObject returnObject = new DataTransferObject();

        JsonObject obj = dto.getJsonObj();
        User user;

        // Check JsonObject for new user data
        if (obj.has("userdata"))
        {
            user = new User();
            JsonObject userObj = obj.getAsJsonObject("userdata");

            user.setUsername(userObj.get("username").getAsString());
            user.setPassword(userObj.get("password").getAsString());
            user.setPlayerID(userObj.get("playerId").getAsInt());

            if (dao.addUser(user))
            {
                returnObject.setData("Success!");
            }
            else
            {
                returnObject.setErrorMsg("Couldn't register user.");
            }
        }
        else
        {
            returnObject.setErrorMsg("No user found in DTO JsonObject");
        }

        return returnObject;
    }
}
