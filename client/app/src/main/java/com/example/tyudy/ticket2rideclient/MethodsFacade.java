package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.model.ClientModelFacade;
import com.example.tyudy.ticket2rideclient.model.Game;
import com.example.tyudy.ticket2rideclient.model.ClientModelFacade;
import com.example.tyudy.ticket2rideclient.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyudy on 2/6/17.
 */

public class MethodsFacade {

    public static final MethodsFacade SINGLETON = new MethodsFacade();
    public Serializer serializer = new Serializer();

    private MethodsFacade(){
        // IMPLEMENT ME!
    }


    /**
     * Function called by the LoginFragment when the Login button is clicked.
     * Logs the user in and returns a user object if successful, and a null object if not successful.
     * NOTE: we could also do the checking in another function called like canDoLogin or somethings which
     *    is what Dr.Woodfield suggested in class, then this function wouldnt run into errors when loggin in.
     * @param enteredName - name entered to perform login in the login fragment
     * @param enteredPassword - password entered to perform login in the login fragment
     * @return - a User object reflecting the logged in user
     */
    public User loginUser(String enteredName, String enteredPassword){
        // IMPLEMENT ME!
        if(check(enteredName) && check(enteredPassword)){
            DataTransferObject dto = new DataTransferObject();
            User user = new User();
            user.setUsername(enteredName);
            user.setPassword(enteredPassword);
            String s = gson.toJson(user);
            LoginCommand newCommand = new LoginCommand();
            dto.setData(s);
            dto.setCommand("login");
            newCommand.setData(dto);
            String commandString = serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);
      }

        return null;
    }

    /**
     * Function called by the RegisterFragment when thte register button is clicked.
     * Registers the user on the server if they have entered a valid user name and password
     * @param enteredName - name entered to perform login in the login fragment
     * @param enteredPassword - password entered to perform login in the login fragment
     * @return - a User object reflecting the registered user or null if the userName or password was invalid
     */
    public User registerUser(String enteredName, String enteredPassword){
        // IMPLEMENT ME!
        if(check(enteredName) && check(enteredPassword)){
            DataTransferObject dto = new DataTransferObject();
            User user = new User();
            user.setUsername(enteredName);
            user.setPassword(enteredPassword);
            String s = gson.toJson(user);
            LoginCommand newCommand = new RegisterCommand();
            dto.setData(s);
            dto.setCommand("login");
            newCommand.setData(dto);
            String commandString = serializer.serialize(newCommand);
            DataTransferObject response = ClientCommunicator.getInstance().sendCommand(commandString);
      }
        // if(server no error)
        // return User
        //else return null;
        return null;
    }
    public boolean check(String pass){
      if(pass == null || pass == ""){
        return false;
      }
      if(pass.length() > 10 || pass.length() < 2){
        return false;
      }
      if(StringUtils.isAlphanumeric(pass)){
        return true;
      }
      else{
        return false;
      }

    }

    /**
     * Called by the Poller. Updates the game list stored in the model and notifies the observers that changes were made.
     * @param gameList - new game list from the server. (passed in by the Poller ).
     */
    public void updateGameList(ArrayList<Game> gameList){
        // Update current game list with the new one
        // notify the observers by calling notifyObservers on the ClientModelFacade
        ClientModelFacade.SINGLETON.addGames(gameList);
    }

}
