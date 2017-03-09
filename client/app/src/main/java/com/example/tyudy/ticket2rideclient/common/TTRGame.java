package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.decks.DestinationCardDeck;
import com.example.tyudy.ticket2rideclient.common.decks.TrainCardDeck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by colefox on 2/6/17.
 */
public class TTRGame implements Serializable
{
    private int inProgress;
    private int gameID;
    private int ownerID;
    private String ownerUsername;
    private Set<User> players = new TreeSet<User>();

    private TrainCardDeck myTrainDeck;
    private DestinationCardDeck myDestDeck;

    public TTRGame()
    {
    }

    public void setMyTrainDeck(TrainCardDeck myTrainDeck) {
        this.myTrainDeck = myTrainDeck;
    }

    public void setMyDestDeck(DestinationCardDeck myDestDeck) {
        this.myDestDeck = myDestDeck;
    }

    public TrainCardDeck getMyTrainDeck() {
        return myTrainDeck;
    }

    public DestinationCardDeck getMyDestDeck() {
        return myDestDeck;
    }

    public int getInProgress()
    {
        return inProgress;
    }

    public void setInProgress(int inProgress)
    {
        this.inProgress = inProgress;
    }

    public int getOwnerID()
    {
        return ownerID;
    }

    public void setOwnerID(int ownerID)
    {
        this.ownerID = ownerID;
    }

    public void addPlayer(User player)
    {
        players.add(player);
    }

    public int getNumPlayers()
    {
        return players.size();
    }

    public String getOwnerUsername()
    {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername)
    {
        this.ownerUsername = ownerUsername;
    }

    public Set<User> getUsers()
    {
        return players;
    }

    public void setPlayers(Set<User> players)
    {
        this.players = players;
    }

    public int getGameID()
    {
        return gameID;
    }

    public void setGameID(int gameID)
    {
        this.gameID = gameID;
    }


}
