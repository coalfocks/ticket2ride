package com.example.tyudy.ticket2rideclient.model;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by colefox on 2/6/17.
 */
public class Game implements Serializable
{
    private String ownerUsername;
    private Set<Integer> players = new TreeSet<Integer>();

    public Game()
    {
    }

    public void addPlayer(int playerID)
    {
        players.add(playerID);
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

    public Set<Integer> getPlayers()
    {
        return players;
    }

    public void setPlayers(Set<Integer> players)
    {
        this.players = players;
    }
}


}
