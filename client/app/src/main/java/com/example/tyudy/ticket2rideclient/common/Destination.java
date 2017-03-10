package com.example.tyudy.ticket2rideclient.common;

import com.example.tyudy.ticket2rideclient.common.cities.City;

/**
 * Created by zacheaton on 3/2/17.
 */

public class Destination {
    City source;
    City dest;

    public Destination(City source, City dest) {
        this.source = source;
        this.dest = dest;
    }

    public City getSource() {
        return source;
    }

    public City getDest() {
        return dest;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public void setDest(City dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "Source: " + source.getCityName() +
                    ", Dest: " + dest.getCityName();
    }
}
