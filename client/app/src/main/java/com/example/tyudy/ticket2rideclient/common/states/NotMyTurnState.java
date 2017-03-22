package com.example.tyudy.ticket2rideclient.common.states;

/**
 * Created by Trevor on 3/15/2017.
 */

/**
 * All of the methods in this state will return false,
 * since there are no valid actions you can do on another
 * players' turns.
 */
public class NotMyTurnState implements IState {

    @Override
    public IState drawCard() {
        return this;
    }

    @Override
    public IState drawDest() {
        return this;
    }

    @Override
    public IState pickCard() {
        return this;
    }

    @Override
    public IState claimPath() {
        return this;
    }

    @Override
    public IState returnCard() {
        return this;
    }

    @Override
    public IState scorePoints() {
        return this;
    }

    @Override
    public IState endTurn() {
        return this;
    }
}
