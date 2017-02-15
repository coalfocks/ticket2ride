package com.example.tyudy.ticket2rideclient.model;

import java.util.ArrayList;

/**
 * Created by tyudy on 2/13/17.
 */

public class Game {
  ArrayList<User> players;
  private Game(){

  }
  public void addUser(User player){
    players.add(player);
  }

}
