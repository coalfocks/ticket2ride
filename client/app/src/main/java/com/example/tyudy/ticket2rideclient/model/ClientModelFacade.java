package com.example.tyudy.ticket2rideclient.model;

import com.example.tyudy.ticket2rideclient.IObservable;
import com.example.tyudy.ticket2rideclient.IObserver;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;

import java.util.ArrayList;

/**
 * Created by tyudy on 2/13/17.
 */

public class ClientModelFacade implements IObservable {

    public static final ClientModelFacade SINGLETON = new ClientModelFacade();
    private ArrayList<TTRGame> mTTRGameList;
    private ArrayList<IObserver> obsList;
    private String ipAddress;
    private User currentUser;
    private TTRGame mCurrentTTRGame;


    private ClientModelFacade(){
        // IMPLEMENT ME!
        mTTRGameList = new ArrayList<>();
        obsList = new ArrayList<>();
        ipAddress = null;
        currentUser = null;
        mCurrentTTRGame = null;
    }

    /**
     * Add an observer to the list of observers stored inside this observable
     */
    public void addObserver(IObserver observer){
        obsList.add(observer);
    }

    /**
     * Tell all the Observers that changes were made.
     * i.e. call each ones .observe() method
     */
    @Override
    public void notifyObservers(){
        for (IObserver obs : obsList){
          obs.observe();
        }
        return;
    }

    /**
     * Add a game to the ClientModelFacade. (Stored in something like a GameList class or just a List<TTRGame>)
     * @param TTRGameList - game to be added
     */
    public void addGames(ArrayList<TTRGame> TTRGameList) {
        this.mTTRGameList = TTRGameList;
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
     * @return - a list of all the games that the ClientModelFacade is currently aware of.
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

    public void setCurrentTTRGame(TTRGame g){
        mCurrentTTRGame = g;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public TTRGame getCurrentTTRGame(){
        return mCurrentTTRGame;
    }

}
