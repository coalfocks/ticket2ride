package com.example.tyudy.ticket2rideclient.common;

import java.io.Serializable;

/**
 * Created by tyudy on 2/7/17.
 */

public class User implements Serializable {

  private String username;
  private String password;
  private int playerID;
  private int inGame;

  public User()
  {
    username = "";
    password = "";
    playerID = 0;
    inGame = 0;
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

}
