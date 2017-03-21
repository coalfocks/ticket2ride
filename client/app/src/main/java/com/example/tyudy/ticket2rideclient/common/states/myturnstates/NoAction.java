package com.example.tyudy.ticket2rideclient.common.states.myturnstates;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.states.IState;
import com.example.tyudy.ticket2rideclient.common.states.MyTurnState;

/**
 * Created by Trevor on 3/15/2017.
 */

public class NoAction extends MyTurnState {

    @Override
    public IState drawCard() {
        return new DrawFirst();
    }

    @Override
    public IState drawDest() {
        return new DrawDest();
    }

    @Override
    public IState pickCard() {
        return new PickFirst();
    }

    @Override
    public IState claimPath(Path pathToClaim) {
        return new ClaimRoute();
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
