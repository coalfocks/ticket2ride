package com.example.tyudy.ticket2rideclient.common.cities;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Trevor on 3/8/2017.
 */

public class Path implements Serializable {
    private ColorENUM mPathColorENUM;
    private int distance;
    private ArrayList<City> connectedCities;
    private User owner;

    public Path(ColorENUM c, int dist, City city1, City city2) {
        mPathColorENUM = c;
        distance = dist;
        connectedCities = new ArrayList<>();
        connectedCities.add(city1);
        connectedCities.add(city2);
        owner = null;
    }

    public void setOwner(User p) { owner = p; }
    public User getOwner() { return owner; }
    public ArrayList<City> getCities() { return connectedCities; }
    public int getPoints() {
        return this.distance;
    }

    /**
     * A method to find if a path contains the given city
     * @param city The desired city
     * @return True if path is connected to city, false otherwise
     */
    public boolean containsCity(City city) {

        TreeSet<City> cities = new TreeSet<>(connectedCities);
        return cities.contains(city);
    }

    /**
     * Check if the path has an owner
     * @return - true if the path is owned false if not
     */
    public boolean hasOwner(){
        if(owner != null){
            return true;
        } else {
            return false;
        }
    }
}
