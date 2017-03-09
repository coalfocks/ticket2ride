package com.example.tyudy.ticket2rideclient.common.cards;

import com.example.tyudy.ticket2rideclient.common.Destination;

/**
 * Created by zacheaton on 3/2/17.
 */

public class DestinationCard {
    private Destination destination;
    private int pointValue;

    public Destination getDestination() {
        return destination;
    }

    public int getPointValue() {
        return pointValue;
    }

    public DestinationCard(Destination d, int points) {
        destination = d;
        pointValue = points;
    }

    @Override
    public String toString() {
        return destination.toString() + " Point Value: " + pointValue;
    }
}
