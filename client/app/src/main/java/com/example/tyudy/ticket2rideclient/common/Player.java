package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.DestinationCard;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Trevor on 3/1/2017.
 */

public class Player {
    private String name;
    private int points;
    private Color color;
    private User associatedUser;
    private Map<Color, Integer> colorCards;
    private ArrayList<DestinationCard> destCards;

    public Player(User user, Color color) {
        this.associatedUser = user;
        this.color = color;
        name = user.getUsername();
        points = 0;
        destCards = new ArrayList<>();
    }

    public Player(User user, String playerName, Color color) {
        this.associatedUser = user;
        this.color = color;
        this.name = playerName;
        points = 0;
        destCards = new ArrayList<>();
    }

    public boolean addDestinationCard(DestinationCard card){
       return destCards.add(card);
    }

    public ArrayList<DestinationCard> getDestCards() { return destCards; }

    //Cards stuff
    public void addTrainCard(TrainCard card){
        int i = colorCards.get(card.getColor());
        i++;
        colorCards.put(card.getColor(), i);
    }

    public Integer getNumCardsOfColor(Color c) { return colorCards.get(c); }

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

    public Color getColorEnum() {
        return color;
    }

    public int getColor() { return associatedUser.getColor(); }

    public User getAssociatedUser() {
        return associatedUser;
    }
}
