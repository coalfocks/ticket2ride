package com.example.tyudy.ticket2rideclient.common.states;

import com.example.tyudy.ticket2rideclient.common.cards.iCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;

/**
 * Created by Trevor on 3/15/2017.
 */

public class EndGameState implements IState {

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
    public IState claimPath(Path pathToClaim) {
        return this;
    }

    @Override
    public IState returnCard(iCard cardToBeReturned) {
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
