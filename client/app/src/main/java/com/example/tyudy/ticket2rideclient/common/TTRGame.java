package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
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
    private int mTurnIndex = 0;
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

    public int getmTurnIndex()
    {
        return mTurnIndex;
    }

    public void setmTurnIndex(int mTurnIndex)
    {
        this.mTurnIndex = mTurnIndex;
    }

    public void claimPath(Path path) {
        for (User u : players) {
            if (u.getPlayerID() == path.getOwner().getPlayerID()) {
                u.claimPath(path);
            }
        }
    }

    public TrainCard dealTrainCard(TrainCard card, int playerID){
        TrainCard myCard = null;
        for (User u : players)
        {
            if (u.getPlayerID() == playerID)
            {
                myCard = (TrainCard) getMyTrainDeck().getCard();
                u.addTrainCard(myCard);
            }
        }
        return myCard;
    }

    public void dealDestCard(User u){
        DestinationCard myCard = (DestinationCard) getMyDestDeck().getCard();
        u.addDestinationCard(myCard);

    }

    public void changeTurn() {
        this.mTurnIndex++;
        mTurnIndex %= this.players.size();
    }

    public int getWhoTurn() {
        ArrayList<User> arr = new ArrayList<>(players);
        return arr.get(mTurnIndex).getPlayerID();
    }

    public void setUsers(Set<User> users) {
        this.players = users;
    }
}
