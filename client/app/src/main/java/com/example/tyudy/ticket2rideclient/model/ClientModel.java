package com.example.tyudy.ticket2rideclient.model;

import com.example.tyudy.ticket2rideclient.common.Player;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.interfaces.iObservable;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyudy on 2/13/17.
 */

public class ClientModel implements iObservable {

    public static final ClientModel SINGLETON = new ClientModel();
    private ArrayList<TTRGame> mTTRGameList;
    private ArrayList<iObserver> obsList;
    private ArrayList<String> chatMsgs;
    private String ipAddress;
    private User currentUser;
    private Player currentPlayer;
    private TTRGame mCurrentTTRGame;


    private ClientModel(){
        mTTRGameList = new ArrayList<>();
        obsList = new ArrayList<>();
        chatMsgs = new ArrayList<>();
        ipAddress = null;
        currentUser = null;
        mCurrentTTRGame = null;
        currentPlayer = null;
    }

    /**
     * Add an observer to the list of observers stored inside this observable
     */
    public void addObserver(iObserver observer){
        obsList.add(observer);
    }

    /**
     * Tell all the Observers that changes were made.
     * i.e. call each ones .observe() method
     */
    @Override
    public void notifyObservers(){
        for (iObserver obs : obsList){
          obs.observe();
        }
        return;
    }

    /**
     * replace the gameList with the new one from the server.
     * Also update the currentGame becuase it could have changed.
     * @param TTRGameList - new games from the server
     */
    public void replaceGames(ArrayList<TTRGame> TTRGameList) {
        this.mTTRGameList = TTRGameList;
        if(getCurrentTTRGame() != null){
            this.mCurrentTTRGame = getTTRGameWithID(getCurrentTTRGame().getGameID());
        }
        this.notifyObservers();
    }

    /**
     * return a game at the given index
     * @param index - location that the game is stored in the chosen data structure
     */
    public TTRGame getGameAtIndex(int index){
        return mTTRGameList.get(index);
    }

    /**
     * @return - a list of all the games that the ClientModel is currently aware of.
     */
    public ArrayList<TTRGame> getTTRGameList(){
        return mTTRGameList;
    }


    public void setIpAddress(String addressParam){
        ipAddress = addressParam;
    }

    public void setCurrentUser(User u){
        currentUser = u;
    }

    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public void setCurrentTTRGame(TTRGame g){
        mCurrentTTRGame = g;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public TTRGame getCurrentTTRGame(){
        return mCurrentTTRGame;
    }

    public void setObsList(ArrayList<iObserver> list) {
        this.obsList = list;
    }

    public ArrayList<String> getChatMsgs() { return chatMsgs; }

    public String getMostRecentMessage() { return chatMsgs.get(chatMsgs.size() - 1); }

    public void receiveNewChat(String chat){
        chatMsgs.add(chat);
        this.notifyObservers();
    }

    /**
     * @param ID - the unique game ID that we are a part of
     * @return - the game that has the given ID, else mCurrentTTRGame
     */
    public TTRGame getTTRGameWithID(int ID){

        if(ID == 0){
            return null;
        }

        for(TTRGame game: mTTRGameList){
            if(game.getGameID() == ID){
                return game;
            }
        }
        return mCurrentTTRGame;
    }

    /**
     * This method doesn't necessarily need to be in the ClientModel,
     * but I didn't have anywhere else to put it. So for now, it sits here
     */
    public void initCities(){
        /*
         * List of cities:
         * Atlanta
         * Boston
         * Calgary
         * Charleston
         * Chicago
         * Dallas
         * Denver
         * Duluth
         * El Paso
         * Helena
         * Houston
         * Kansas City
         * Las Vegas
         * Little Rock
         * LA
         * Miami
         * Monreal
         * Nashville
         * New Orleans
         * New York
         * Oklahoma City
         * Omaha
         * Phoenix
         * Pittsburgh
         * Portland
         * Raleigh
         * St Louis
         * Salt Lake
         * San Fran
         * Santa Fe
         * Sault St. Marie
         * Seattle
         * Toronto
         * Vancouver
         * Washington DC
         * Winnipeg
         */

        // Create All Cities
        City Atlanta = new City("Atlanta");
        City Boston = new City("Boston");
        City Calgary = new City("Calgary");
        City Charleston = new City("Charleston");
        City Chicago = new City("Chicago");
        City Dallas = new City("Dallas");
        City Denver = new City("Denver");
        City Duluth = new City("Duluth");
        City El_Paso = new City("El Paso");
        City Helena = new City("Helena");
        City Houston = new City("Houston");
        City Kansas_City = new City("Kansas City");
        City Las_Vegas = new City("Las Vegas");
        City Little_Rock = new City("Little Rock");
        City Los_Angeles = new City("Los Angeles");
        City Miami = new City("Miami");
        City Montreal = new City("Montreal");
        City Nashville = new City("Nashville");
        City New_Orleans = new City("New Orleans");



    }

}
