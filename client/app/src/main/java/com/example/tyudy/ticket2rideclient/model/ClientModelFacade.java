package com.example.tyudy.ticket2rideclient.model;

import com.example.tyudy.ticket2rideclient.IObservable;
import com.example.tyudy.ticket2rideclient.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyudy on 2/13/17.
 */

public class ClientModelFacade implements IObservable {

    public static final ClientModelFacade SINGLETON = new ClientModelFacade();
    private ArrayList<Game> gameList;
    private ArrayList<Observer> obsList;


    private ClientModelFacade(){
        // IMPLEMENT ME!
    }

    /**
     * Add an observer to the list of observers stored inside this observable
     */
    public void addObserver(IObserver observer){
        obsList.push(observer);
    }

    /**
     * Tell all the Observers that changes were made.
     * i.e. call each ones .observe() method
     */
    @Override
    public void notifyObservers(){
        for (Observer obs : obsList){
          obs.observe();
        }
        return;
    }

    /**
     * Add a game to the ClientModelFacade. (Stored in something like a GameList class or just a List<Game>)
     * @param g - game to be added
     */
    public void addGame(Game g){
        gameList.push(g);
        return;
    }

    /**
     * return a game at the given index
     * @param index - location that the game is stored in the chosen data structure
     */
    public Game getGameAtIndex(int index){
        return gameList.get(index);
    }

    /**
     * @return - a list of all the games that the ClientModelFacade is currently aware of.
     */
    public ArrayList<Game> getGameList(){
        return gameList;
    }
}
