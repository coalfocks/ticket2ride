package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by tyudy on 2/7/17.
 */

public class User implements Serializable, Comparable<User> {
    private String username;
    private String password;
    private int playerID;
    private int inGame;
    private int color;
    private int points = 0;


    public User()
    {
        username = "";
        password = "";
        playerID = 0;
        inGame = 0;
        points = 0;
        color = 0;
    }

    public User(String username, String password, int playerID, int inGame)
    {
        this.username = username;
        this.password = password;
        this.playerID = playerID;
        this.inGame = inGame;
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

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
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
}