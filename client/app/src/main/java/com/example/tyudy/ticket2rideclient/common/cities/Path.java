package com.example.tyudy.ticket2rideclient.common.cities;

import android.util.Pair;

import com.example.tyudy.ticket2rideclient.common.Color;
import com.example.tyudy.ticket2rideclient.common.Player;

/**
 * Created by Trevor on 3/8/2017.
 */

public class Path {
    public Color pathColor;
    public int distance;
    public Pair<City, City> connectedCities;
    private Player owner;

    Path(Color c, int dist, City city1, City city2) {
        pathColor = c;
        distance = dist;
        connectedCities = new Pair<>(city1, city2);
        owner = null;
    }

    public void setOwner(Player p) { owner = p; }
    public Player getOwner() { return owner; }
}
