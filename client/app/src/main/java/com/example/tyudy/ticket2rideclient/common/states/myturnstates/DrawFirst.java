package com.example.tyudy.ticket2rideclient.common.states.myturnstates;

import com.example.tyudy.ticket2rideclient.common.cards.iCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.states.IState;
import com.example.tyudy.ticket2rideclient.common.states.MyTurnState;

/**
 * Created by Trevor on 3/21/2017.
 */

public class DrawFirst extends MyTurnState {
    @Override
    public IState drawCard() {
        return new DrawSecond();
    }

    @Override
    public IState drawDest() {
        return this;
    }

    @Override
    public IState pickCard() {
        return new PickSecond();
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
