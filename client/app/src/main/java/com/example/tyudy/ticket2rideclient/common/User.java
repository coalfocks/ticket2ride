package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by tyudy on 2/7/17.
 */

public class User implements Serializable {
    private String username;
    private String password;
    private int playerID;
    private int inGame;
    private Color color;
    private int points = 0;


    public User()
    {
        username = "";
        password = "";
        playerID = 0;
        inGame = 0;
        points = 0;
        color = Color.BLACK;
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

    public Color getColorEnum()
    {
        return color;
    }

    public int getColor() {
        switch(color)
        {
            case BLACK:
                return android.graphics.Color.BLACK;

            case WHITE:
                return android.graphics.Color.WHITE;

            case RED:
                return android.graphics.Color.RED;

            case BLUE:
                return android.graphics.Color.BLUE;

            case GREEN:
                return android.graphics.Color.GREEN;

            case PURPLE:
                return android.graphics.Color.MAGENTA;

            case YELLOW:
                return android.graphics.Color.YELLOW;

            case ORANGE:
                return android.graphics.Color.rgb(239, 163, 33); // Trust me this is orange XD

            default:
                return android.graphics.Color.TRANSPARENT;
        }
    }

    public void setColor(Color color)
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
}