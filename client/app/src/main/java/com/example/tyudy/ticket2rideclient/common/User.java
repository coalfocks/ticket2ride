package com.example.tyudy.ticket2rideclient.common;


import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.common.cities.Path;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

import static com.example.tyudy.ticket2rideclient.common.ColorENUM.BLACK;
import static com.example.tyudy.ticket2rideclient.common.ColorENUM.WHITE;

/**
 * Created by tyudy on 2/7/17.
 */

public class User implements Serializable, Comparable<User> {
    private String username;
    private String password;
    private int playerID;
    private int inGame;
    private int points = 0;

    private ColorENUM color;

    private ArrayList<Path> claimedPaths;
    private Map<ColorENUM, TrainCard> colorCards;
    private ArrayList<DestinationCard> destCards;


    public User()
    {
        username = "";
        password = "";
        playerID = 0;
        inGame = 0;
        points = 0;
        destCards = new ArrayList<>();
        colorCards = new HashMap<ColorENUM, TrainCard>();
        claimedPaths = new ArrayList<>();
        this.color = BLACK;
    }

    public User(String username, String password, int playerID, int inGame)
    {
        this.username = username;
        this.password = password;
        this.playerID = playerID;
        this.inGame = inGame;


        destCards = new ArrayList<>();
        //claimedPaths = new ArrayList<>();
        colorCards = new HashMap<ColorENUM, TrainCard>();
        TrainCard myCard = new TrainCard();
        myCard.setColor(WHITE);
        this.addTrainCard(myCard);
        destCards = new ArrayList<>();
        colorCards = new HashMap<ColorENUM, TrainCard>();
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

    public TrainCard getNumCardsOfColor(ColorENUM c) { return colorCards.get(c); }

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


    public ColorENUM getColor() {
        return color;
    }

    public void setColor(ColorENUM color) {
        this.color = color;
    }

     public void claimPath(Path p) {
         claimedPaths.add(p);
         addPoints(p.getPoints());
     }

    public boolean haveCompletedRoute(DestinationCard card) {
        // Make sure the given card is a card the player has
        if (destCards.contains(card))
        {
            HashSet<City> citiesInRoute = new HashSet<>();
            City source = new City();
            City dest = new City();
            boolean connectsToDest = false;
            boolean connectsToSource = false;

            // Iterate through each path the user has claimed
            for (Path path: claimedPaths)
            {
                if (path.containsCity(source))
                {
                    connectsToSource = true;
                }
                else if (path.containsCity(dest))
                {
                    connectsToDest = true;
                }

                if (connectsToSource && connectsToDest)
                    break;
            }

            // Player does not own a path connected to one of the necessary cities
            if (!(connectsToSource && connectsToDest))
                return false;

            // DFS to find the SCC of the source city
            // tldr; if the source city is in the same set as
            //  the destination city after the loop exist,
            //  there exists a path from src to dest
            Stack<City> citySCC = new Stack<>();
            citySCC.push(source);
            while(!citySCC.empty())
            {
                City nextCity = citySCC.pop();
                if (!citiesInRoute.contains(nextCity))
                {
                    citiesInRoute.add(nextCity);

                    for (Path path : nextCity.getPaths())
                    {
                        City c1 = path.getCities().get(0);
                        City c2 = path.getCities().get(1);

                        if (!c1.equals(nextCity))
                            citySCC.push(c1);
                        else
                            citySCC.push(c2);
                    }
                }
            }

            if (citiesInRoute.contains(source) && citiesInRoute.contains(dest))
                return true;
        }

        return false;
    }

    public void removeDestinationCard(DestinationCard card) {
        this.destCards.remove(card);
    }

    public void removeAllTrainCards(){
        colorCards = new HashMap<>();
    }

    public void removeAllDestinationCards(){
        destCards = new ArrayList<>();
    }
}
