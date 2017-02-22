package com.example.tyudy.ticket2rideclient;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.commands.CreateGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.JoinGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.ListGamesCommand;
import com.example.tyudy.ticket2rideclient.common.commands.LoginCommand;
import com.example.tyudy.ticket2rideclient.common.commands.RegisterCommand;
import com.example.tyudy.ticket2rideclient.common.commands.StartGameCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.common.User;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by tyudy on 2/6/17.
 */

public class MethodsFacade {

    public static final MethodsFacade SINGLETON = new MethodsFacade();
    public Serializer serializer = new Serializer();

    private Gson gson = new Gson();

    private MethodsFacade(){

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
            try {
                String commandString = serializer.serialize(newCommand);
                ClientCommunicator.getInstance().sendCommand(commandString);
            } catch (Exception e){
                e.printStackTrace();
                Log.d("MethodsFacade", e.getMessage());
                return null;
            }
         }

        return null;

    }
    public void passBackDTOLogin(DataTransferObject response, FragmentActivity contxt){

        if(response.getErrorMsg().length()!=0){
            Toast.makeText(contxt, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            try {
                User loggedInUser = (User) serializer.deserialize(response.getData());
                ClientModel.SINGLETON.setCurrentUser(loggedInUser);
                // I am such a boss programmer for this line ..... it sets the current game
                ClientModel.SINGLETON.setCurrentTTRGame(ClientModel.SINGLETON.getTTRGameWithID(ClientModel.SINGLETON.getCurrentUser().getInGame()));
                Toast.makeText(contxt, "Successful Login!", Toast.LENGTH_SHORT).show();
                ((PreGameActivity) contxt).onLogin(loggedInUser);

            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(contxt, "Invalid Login", Toast.LENGTH_SHORT).show();
                Log.d("MethodsFacade", e.getMessage());
            }
        }
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
            Command newCommand = new RegisterCommand();
            dto.setData(s);
            dto.setCommand("register");
            newCommand.setData(dto);
            try {
                String commandString = serializer.serialize(newCommand);
                ClientCommunicator.getInstance().sendCommand(commandString);

            } catch (Exception e){
                e.printStackTrace();
                Log.d("MethodsFacade", e.getMessage());
                return null;
            }
      }
        return null;
    }

    public void processRegister(DataTransferObject response, FragmentActivity contxt){
        if(response.getErrorMsg().length()!=0){
            Toast.makeText(contxt, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            try {
                Toast.makeText(contxt, response.getData(), Toast.LENGTH_SHORT).show();
                contxt.getSupportFragmentManager().popBackStack();

            } catch (Exception e){
                e.printStackTrace();
                Log.d("MethodsFacade", e.getMessage());
            }
        }
    }

    public boolean check(String pass){
      if(pass == null || pass == ""){
        return false;
      }
      if(pass.length() > 10 || pass.length() < 2){
        return false;
      }

      else{
        return true;
      }

    }

    /**
     * Called by the Poller. Updates the game list stored in the model and notifies the observers that changes were made.
     * @param gameList - new game list from the server. (passed in by the Poller ).
     */
    public void updateGameList(DataTransferObject gameList){


        try {
            ArrayList<TTRGame> gList = (ArrayList<TTRGame>) serializer.deserialize(gameList.getData());
            ClientModel.SINGLETON.replaceGames(gList);
        } catch (Exception e){
            Log.d("MethodsFacade", e.getMessage());
        }


    }

    /**
     * Called when the create game button is clicked in the GameSelectionFragment.
     * Creates a game based off of the current user and will add it to the games stored on the server.
     probably needs a return type? IDK cause if it had one then we could see more effectively if the game was created correctly or not...
     */
    public void createGame(){
        // User this curUser for any data that you may need (i.e. userName)
        User user = ClientModel.SINGLETON.getCurrentUser();

        if(check(user.getUsername()) && check(user.getPassword())){
            DataTransferObject dto = new DataTransferObject();
            String s = gson.toJson(user);
            Command newCommand = new CreateGameCommand();
            dto.setData(s);
            dto.setPlayerID(user.getPlayerID());
            dto.setCommand("create");
            newCommand.setData(dto);
            try {
                String commandString = serializer.serialize(newCommand);
                ClientCommunicator.getInstance().sendCommand(commandString);
            } catch (Exception e){
                e.printStackTrace();
                Log.d("createGame", e.getMessage());
            }
        }
    }
    public void passBackDTOCreate(DataTransferObject response, FragmentActivity contxt){
      if(response.getErrorMsg().length()!=0){
          Toast.makeText(contxt, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
      }
      else{
          try {
              ClientModel.SINGLETON.setCurrentTTRGame((TTRGame) serializer.deserialize(response.getData()));
              Toast.makeText(contxt, "Created and Joined Game Successfully!", Toast.LENGTH_SHORT).show();
          } catch(Exception e){
              e.printStackTrace();
          }
      }
    }

    public void startGame(){
        // User this curUser for any data that you may need (i.e. userName)
        TTRGame game = ClientModel.SINGLETON.getCurrentTTRGame();

        if(game != null){
            DataTransferObject dto = new DataTransferObject();
            String s = gson.toJson(game);
            Command newCommand = new StartGameCommand();
            dto.setData(s);
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentTTRGame().getOwnerID());
            dto.setCommand("start");
            newCommand.setData(dto);
            try {
                String commandString = serializer.serialize(newCommand);
                ClientCommunicator.getInstance().sendCommand(commandString);
            } catch (Exception e){
                e.printStackTrace();
                Log.d("MethodsFacade", e.getMessage());
            }
        }
    }
    public void passBackDTOStart(DataTransferObject response, FragmentActivity contxt){
      if(response.getErrorMsg().length()!=0){
          Toast.makeText(contxt, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
      }
      else{
          Toast.makeText(contxt, "Game Started!", Toast.LENGTH_SHORT).show();
      }
    }

     public DataTransferObject getGameList(){
       //TODO how does he want the server to return the list of games?
       DataTransferObject dto = new DataTransferObject();
       Command newCommand = new ListGamesCommand();
       dto.setCommand("gameList");
       newCommand.setData(dto);
       try {
           String commandString = serializer.serialize(newCommand);
           ClientCommunicator.getInstance().sendCommand(commandString);

       } catch (Exception e){
           e.printStackTrace();
           Log.d("MethodsFacade", e.getMessage());
           return null;
       }
         return null;
     }


     public void passBackDTOGameList(DataTransferObject response, FragmentActivity contxt){

     }

    /**
     * Joins the currentUser into the game that was clicked on in the GameSelectionFragment.
     * At this point currentUser has already been set (Done at login) as well as the currentGame (done when clicked on)
     * @param gameToJoin - game that the currentUser is going to join
     */
    public void joinGame(TTRGame gameToJoin){

        DataTransferObject dto = new DataTransferObject();
        try {
            // This is commented temporarily but it should probably pass a game
            //String s = serializer.serialize(gameToJoin);
            String s = String.valueOf(gameToJoin.getGameID());
            Command newCommand = new JoinGameCommand();
            dto.setData(s);
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
            dto.setCommand("join");
            newCommand.setData(dto);
            String commandString = serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);
        } catch (Exception e){
            e.printStackTrace();
            Log.d("MethodsFacade", e.getMessage());
        }

    }

    // TODO: (Zac) Check against current game
    public void passBackDTOJoinGame(DataTransferObject response, FragmentActivity contxt){
        if(response.getErrorMsg().length()!=0){
            Toast.makeText(contxt, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                ClientModel.SINGLETON.setCurrentTTRGame((TTRGame) serializer.deserialize(response.getData()));
                Toast.makeText(contxt, "Joined Game Successfully!", Toast.LENGTH_SHORT).show();
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }


}
