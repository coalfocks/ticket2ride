package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.example.tyudy.ticket2rideclient.common.Color.BLACK;
import static com.example.tyudy.ticket2rideclient.common.Color.WHITE;

/**
 * Created by tyudy on 2/7/17.
 */

public class User implements Serializable, Comparable<User> {
    private String username;
    private String password;
    private int playerID;
    private int inGame;
    private int points = 0;

    private Color color;
    private User associatedUser;
    private Map<Color, TrainCard> colorCards = new HashMap<Color, TrainCard>();;
    private ArrayList<DestinationCard> destCards;


    public User()
    {
        username = "";
        password = "";
        playerID = 0;
        inGame = 0;
        points = 0;
        destCards = new ArrayList<>();
        colorCards = new HashMap<Color, TrainCard>();
        this.color = BLACK;
    }

    public User(String username, String password, int playerID, int inGame)
    {
        this.username = username;
        this.password = password;
        this.playerID = playerID;
        this.inGame = inGame;
        TrainCard myCard = new TrainCard();
        myCard.setColor(WHITE);
        this.addTrainCard(myCard);
        destCards = new ArrayList<>();
        colorCards = new HashMap<Color, TrainCard>();
        this.color = BLACK;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public void setPlayerID(int playerID)
    {
        this.playerID = playerID;
    }

    public int getInGame()
    {
        return inGame;
    }

    public void setInGame(int inGame)
    {
        this.inGame = inGame;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public int getPoints()
    {
        return points;
    }

    public void addPoints(int pointValue) {
        this.points += pointValue;
    }

    @Override
    public int compareTo(User o)
    {
        if(this.playerID > o.getPlayerID()) {
            return 1;
        }
        else if (this.playerID < o.getPlayerID()) {
            return -1;
        }
        return 0;
    }

    public boolean addDestinationCard(DestinationCard card){
        return destCards.add(card);
    }

    public ArrayList<DestinationCard> getDestCards() { return destCards; }

    //Cards stuff
    public void addTrainCard(TrainCard card){
        TrainCard c = colorCards.get(card.getColor());
        if(c != null) {
            c.incNum();
            colorCards.put(card.getColor(), c);
        }
        else{
            colorCards.put(card.getColor(), card);
        }
    }
    public ArrayList<TrainCard> getTrainCards(){
        ArrayList<TrainCard> arrayOfCards = new ArrayList<TrainCard>(colorCards.values());
        return arrayOfCards;
    }

    public TrainCard getNumCardsOfColor(Color c) { return colorCards.get(c); }
    

    public void increasePoints(int addPoints) {
        points += Math.abs(addPoints);
    }

    public void decreasePoints(int subtractPoints) {
        points -= Math.abs(subtractPoints);

        // Can't go less than 0 points
        if (points < 0)
            points = 0;
    }

    public void setPoints(int newValue) {
        if (newValue > 0)
            points = newValue;
        else
            points = 0;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}