package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;

import java.util.ArrayList;
import java.util.Map;

import static com.example.tyudy.ticket2rideclient.common.Color.WHITE;

/**
 * Created by Trevor on 3/1/2017.
 */

public class Player {
    private String name;
    private int points;
    private Color color;
    private User associatedUser;
    private Map<Color, TrainCard> colorCards;
    private ArrayList<DestinationCard> destCards;

    public Player(User user, Color color) {
        this.associatedUser = user;
        this.color = color;
        name = user.getUsername();
        points = 0;
    }

    public Player(User user, String playerName, Color color) {
        this.associatedUser = user;
        this.color = color;
        this.name = playerName;
        points = 0;
    }

    public boolean addDestinationCard(DestinationCard card){
       return destCards.add(card);
    }

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
    public ArrayList<TrainCard> returnTrainCards(){
        ArrayList<TrainCard> arrayOfCards = new ArrayList<TrainCard>(colorCards.values());
        return arrayOfCards;
    }


    public void setName(String newName){
        this.name = newName;
    }

    public String getName() {
        return name;
    }

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

    public int getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }
}
