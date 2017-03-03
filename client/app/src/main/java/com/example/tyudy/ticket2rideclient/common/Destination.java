package com.example.tyudy.ticket2rideclient.common;

/**
 * Created by zacheaton on 3/2/17.
 */

public class Destination {
    int source;
    int dest;

    public Destination(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
