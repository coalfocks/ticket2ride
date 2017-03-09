package com.example.tyudy.ticket2rideclient.common.cards;

import com.example.tyudy.ticket2rideclient.common.Destination;

import java.io.Serializable;

/**
 * Created by zacheaton on 3/2/17.
 */

public class DestinationCard implements iCard, Serializable {
    Destination destination;
    int pointValue;

    public Destination getDestination() {
        return destination;
    }

    public int getPointValue() {
        return pointValue;
    }

    public DestinationCard() {

    }

}

