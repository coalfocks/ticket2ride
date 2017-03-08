package com.example.tyudy.ticket2rideclient.common.cards;

import com.example.tyudy.ticket2rideclient.common.Destination;

/**
 * Created by zacheaton on 3/2/17.
 */

public class DestinationCard implements iCard{
    Destination destination;
    int pointValue;

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

}

