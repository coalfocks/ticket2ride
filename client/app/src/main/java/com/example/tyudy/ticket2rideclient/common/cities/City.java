package com.example.tyudy.ticket2rideclient.common.cities;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Trevor on 3/8/2017.
 */

public class City {
    /**
     * A map of the cities this city is connected to, with the
     * values being the class Path
     */
    private String mCityName;
    private Map<City, Path> mConnectedCities;
    private PointF mCoordinates;

    public City(String cityName, Map<City, Path> connectedCities) {
        mConnectedCities = connectedCities;
        mCityName = cityName;
    }

    public City(String cityName) {
        mConnectedCities = null;
        mCityName = cityName;

    }

    public void setCoordinate(PointF coordinate) { mCoordinates = coordinate; }
    public PointF getCoordinates() { return mCoordinates; }

    public void setConnectedCities(Map<City, Path> cc) { mConnectedCities = cc; }

    /**
     * A function to find if a city is connected to another
     * @param city The city the question is about
     * @return True if the two are connected, false otherwise
     */
    public boolean isConnectedTo(City city) {
        if (mConnectedCities.containsKey(city))
            return true;

        return false;
    }

    /**
     * A function to find the path length between 2 cities
     * @param city The connected city to find length to
     * @return Distance to city, if connected. -1 if no connection exists
     */
    public int getDistTo(City city) {
        if (isConnectedTo(city))
            return mConnectedCities.get(city).distance;

        return -1;
    }

    /**
     * Getter function to return the path between the two cities
     * @param city The connected city to get the path to
     * @return Path to the given city, if connected. Null if no connection exists
     */
    public Path getPathTo(City city) {
        if (isConnectedTo(city))
            return mConnectedCities.get(city);

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof City)) return false;

        City city = (City) obj;
        if (!city.mConnectedCities.equals(this.mConnectedCities)) return false;

        return super.equals(obj);
    }
}
