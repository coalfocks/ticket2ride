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

    public Path(Color c, int dist, City city1, City city2) {
        pathColor = c;
        distance = dist;
        connectedCities = new Pair<>(city1, city2);
        owner = null;
    }

    public void setOwner(Player p) { owner = p; }
    public Player getOwner() { return owner; }

    /**
     * A method to find if a path contains the given city
     * @param city The desired city
     * @return True if path is connected to city, false otherwise
     */
    public boolean containsCity(City city) {
        if (connectedCities.first.equals(city) ||
            connectedCities.second.equals(city))
            return true;

        return false;
    }
}
