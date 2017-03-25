package com.example.tyudy.ticket2rideclient;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.commands.CreateGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.ListGamesCommand;
import com.example.tyudy.ticket2rideclient.common.commands.SendChatCommand;
import com.example.tyudy.ticket2rideclient.common.commands.StartGameCommand;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.common.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodsFacade {

    public static final MethodsFacade SINGLETON = new MethodsFacade();
    private FragmentActivity mContext;
    private Gson gson = new Gson();

    /**
     * Default private constructor for MethodsFacade
     * @pre None
     * @post The MethodsFacade will be instantiated
     */
    private MethodsFacade(){
        mContext = null;
    }

    /**
     * Function called by the LoginFragment when the Login button is clicked.
     * @param enteredName The name entered to perform login in the login fragment
     * @param enteredPassword The password entered to perform login in the login fragment
     * @pre EnteredName cannot be null, empty string or single character. EnteredPassword similar.
     *      Both parameters must meet the username/password requirements.
     * @post The user will be logged into the server
     */
    public void loginUser(String enteredName, String enteredPassword){
        User user = new User();
        user.setUsername(enteredName);
        user.setPassword(enteredPassword);
        String jsonUserString = gson.toJson(user);

        DataTransferObject dto = new DataTransferObject(); // Create DTO to pass to the proxy server
        dto.setData(jsonUserString);
        dto.setCommand("login");
        ServerProxy.SINGLETON.login(dto);
    }

    /**
     * Function called by the RegisterFragment when the register button is clicked.
     * Registers the user on the server if they have entered a valid user name and password
     * @param enteredName The name entered to perform login in the login fragment
     * @param enteredPassword The password entered to perform login in the login fragment
     * @return A User object reflecting the registered user, or null if the userName or password was invalid
     * @pre EnteredName cannot be null, empty string or single character. EnteredPassword similar.
     *      Both parameters must meet the username/password requirements.
     * @post The user will be registered in the server database
     */
    public User registerUser(String enteredName, String enteredPassword){
        // IMPLEMENT ME!
        if(checkPassword(enteredName) && checkPassword(enteredPassword)){
            User user = new User();
            user.setUsername(enteredName);
            user.setPassword(enteredPassword);
            String s = gson.toJson(user);

            DataTransferObject dto = new DataTransferObject();
            dto.setData(s);
            dto.setCommand("register");
            ServerProxy.SINGLETON.register(dto);

        }
        return null;
    }

    /**
     * Replaces the current list of games in the model with the new one,
     * called in the ListGamesCommand execute
     * @param gList A new list of games for the model
     * @pre gList cannot be null. It can however be empty.
     * @post The ClientModel will be updated accordingly to the information passed in.
     */
    public void replaceModelsGames(ArrayList<TTRGame> gList){
        ClientModel.SINGLETON.replaceGames(gList);
    }

    /**
     * Called when the create game button is clicked in the GameSelectionFragment.
     * Creates a game based off of the current user and will add it to the games stored on the server.
     * @pre The user must be logged in and not already a part of a game currently in progress, or
     *      waiting to start
     * @post The user will create a new game which people can join
     */
    public void createGame(){
        // User this curUser for any data that you may need (i.e. userName)
        DataTransferObject dto = new DataTransferObject();
        User user = ClientModel.SINGLETON.getCurrentUser();
        String s = gson.toJson(user);
        dto.setData(s);
        dto.setPlayerID(user.getPlayerID());
        dto.setCommand("create");
        ServerProxy.SINGLETON.createGame(dto);
    }

    /**
     * Called when the user is ready to start the game
     * @pre The command must come from the owner of the game, and the game must have been previously created
     * @post The game will begin
     */
    public void startGame(){
        // User this curUser for any data that you may need (i.e. userName)
        TTRGame game = ClientModel.SINGLETON.getCurrentTTRGame();

        if(game != null){
            DataTransferObject dto = new DataTransferObject();
            String s = gson.toJson(game);
            dto.setData(s);
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentTTRGame().getOwnerID());
            dto.setCommand("start");
            ServerProxy.SINGLETON.startGame(dto);
        }
    }


    /**
     * A getter method to display all current games the server is hosting
     * @pre The user must be in the game lobby view to see the updates
     * @post The game list will be updated every time the poller polls
     */
     public void getGameList() {

         DataTransferObject dto = new DataTransferObject();
         dto.setCommand("gameList");
         ServerProxy.SINGLETON.listGames(dto);
     }

    /**
     * A getter method to receive a specific command from the server's command queue
     * @param index The index of the command in the queue
     * @pre Index must be >= 0, and not out of range of the server's command queue
     * @post All commands after the given index will be received
     */
    public void getCommands(int index) {
        DataTransferObject dto = new DataTransferObject();
        dto.setCommand(("getCommands"));
        dto.setData(Integer.toString(index));
        ServerProxy.SINGLETON.getCommands(dto);
    }

    /**
     * Joins the currentUser into the game that was clicked on in the GameSelectionFragment.
     * @param gameToJoin Game that the currentUser is going to join
     * @pre The user must have been logged in, and not a part of any other active games. The
     *      given parameter cannot be null.
     * @post The user will be put into the chosen game
     */
    public void joinGame(TTRGame gameToJoin){

        DataTransferObject dto = new DataTransferObject();

        String s = String.valueOf(gameToJoin.getGameID());
        dto.setData(s);
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setCommand("join");
        ServerProxy.SINGLETON.joinGame(dto);
    }

    /**
     * A validation method for if a password is valid when registering a new user
     * @param pass The entered password
     * @return True if valid, false otherwise
     * @pre The given parameter cannot be null. It must contain between 2 and 10 characters.
     * @post The password will be deemed valid
     */
    public boolean checkPassword(String pass){
        if(pass == null || pass.equals("")){
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
     * A method to update the current context of the application
     * @param jeffery Bless his soul. He's the given context.
     * @pre Jeffery cannot be null.
     * @post The current context will be set to Jeffery. Good boy.
     */
    public void setContext(FragmentActivity jeffery){
        mContext = jeffery;
    }

    /**
     * A getter to get the current context of the application
     * @return The current context (A.K.A. Jeffery)
     * @pre None
     * @post The current context will be returned. May be null.
     */
    public FragmentActivity getContext(){
        return mContext;
    }

    /**
     * The method used to update the chat-log in the ClientModel
     * @param chat The chat message received from the server
     * @pre The given chat cannot be null. It may be an empty string.
     * @post The chat message will be added to the current chat-log
     */
    public void addChat(String chat){
        ClientModel.SINGLETON.receiveNewChat(chat);
    }

    /**
     * The method called when the 'Send' button is pressed in the chat fragment
     * @param chatMessage The entered message to send to all users
     * @pre The message cannot be null, or empty.
     * @post The message will be delivered to the server
     */
    public void sendChatMessage(String chatMessage){
        // We don't want to send an empty string to the server
        if (!chatMessage.equals("")) {
            // Before sending to the server, patch on player's name before message
            String userName = ClientModel.SINGLETON.getCurrentUser().getUsername();
            StringBuilder message =
                    new StringBuilder(userName + ": " + chatMessage);

            DataTransferObject dto = new DataTransferObject();
            dto.setData(message.toString());
            dto.setCommand("sendChat");
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
            ServerProxy.SINGLETON.sendChatMessage(dto);
        }
    }

    /**
     * Called when a path is claimed.
     * @param path The path to be claimed
     * @pre Path cannot be null, and must be a registered/valid path
     * @post The server will be notified of the update
     */
    public void claimPath(Path path){
        DataTransferObject dto = new DataTransferObject();
        String pathData = gson.toJson(path);
        dto.setData(pathData);
        dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
        dto.setCommand("claimPath");
        ServerProxy.SINGLETON.claimPath(dto);
    }

    /**
     * A getter for all of the paths contained in the ClientModel (which is all paths
     * in the game)
     * @return All of the paths in the game
     * @pre There must be a current game being played by the current user.
     * @post The list of paths will be returned
     */
    public ArrayList<Path> getModelPaths(){
        return ClientModel.SINGLETON.getPaths();
    }

    /**
     * A reset method used to reset the data in the ClientModel
     * @pre None
     * @post The ClientModel will be wiped and replaced with a clean slate
     */
    public void reset() {
        ClientModel.SINGLETON.setCurrentTTRGame(new TTRGame());
        ClientModel.SINGLETON.setCurrentUser(new User());
        ClientModel.SINGLETON.setObsList(new ArrayList<iObserver>());
    }
}
