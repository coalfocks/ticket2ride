package com.example.tyudy.ticket2rideclient.common.cities;

import com.example.tyudy.ticket2rideclient.common.Color;
import com.example.tyudy.ticket2rideclient.common.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Trevor on 3/8/2017.
 */

public class Path implements Serializable{
    public Color pathColor;
    public int distance;
    public ArrayList<City> connectedCities;
    private User owner;

    public Path(Color c, int dist, City city1, City city2) {
        pathColor = c;
        distance = dist;
        connectedCities = new ArrayList<>();
        connectedCities.add(city1);
        connectedCities.add(city2);
        owner = null;
    }

    public void setOwner(User p) { owner = p; }
    public User getOwner() { return owner; }
    public ArrayList<City> getCities() { return connectedCities; }

    /**
     * A method to find if a path contains the given city
     * @param city The desired city
     * @return True if path is connected to city, false otherwise
     */
    public boolean containsCity(City city) {

        TreeSet<City> cities = new TreeSet<>(connectedCities);
        return cities.contains(city);
    }

//    @Override
//    public String toString() {
//        String color = "Color: " + pathColor.toString();
//        String length = ", Length: " + distance;
//        String cities = ", Cities: " + connectedCities.first.getCityName()
//                + " <-> " + connectedCities.second.getCityName();
//        String ownr = "\nOwner: ";
//
//        if (owner != null)
//            ownr += owner.getUsername();
//        else
//            ownr += "none";
//
//        return color + length + cities + ownr;
//    }
}
