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
    private ArrayList<Path> mPaths;
    private PointF mCoordinates;

    public City(String cityName) {
        //mConnectedCities = null;
        mCityName = cityName;

    }

    public void setCoordinate(PointF coordinate) { mCoordinates = coordinate; }
    public PointF getCoordinates() { return mCoordinates; }
    public String getCityName() { return mCityName; }

    public void setPaths(ArrayList<Path> paths) { this.mPaths = paths; }
    public ArrayList<Path> getPaths() { return mPaths; }

    /**
     * A function to findif a city is connected to another
     * @param city The city the question is about
     * @return True if the two are connected, false otherwise
     */
    public boolean isConnectedTo(City city) {
        if (city.equals(this))
            return true;

        for (Path path : mPaths)
        {
            if (path.containsCity(city))
                return true;
        }

        return false;
    }

    /**
     * Getter function to return the path between the two cities
     * @param city The connected city to get the path to
     * @return Path to the given city, if connected. Null if no connection exists,
     *  or if the given city is the current city
     */
    public Path getPathTo(City city) {
        if (city.equals(this))
            return null;

        if (isConnectedTo(city))
        {
            for (Path path : mPaths)
            {
                if (path.containsCity(city))
                    return path;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof City)) return false;

        City city = (City) obj;
        if (!city.mPaths.equals(this.mPaths)) return false;

        return super.equals(obj);
    }
}
