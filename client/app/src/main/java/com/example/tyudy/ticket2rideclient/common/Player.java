package com.example.tyudy.ticket2rideclient.common;

/**
 * Created by Trevor on 3/1/2017.
 */

public class Player {
    private String name;
    private int points;
    private Color color;
    private User associatedUser;

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
        points = newValue;
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
