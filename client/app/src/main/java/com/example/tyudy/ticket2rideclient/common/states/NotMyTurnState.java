package com.example.tyudy.ticket2rideclient.common.states;

import com.example.tyudy.ticket2rideclient.common.cards.iCard;
import com.example.tyudy.ticket2rideclient.common.states.IState;

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
    public boolean drawCard() {
        return false;
    }

    @Override
    public boolean drawDest() {
        return false;
    }

    @Override
    public boolean pickCard() {
        return false;
    }

    @Override
    public boolean claimPath() {
        return false;
    }

    @Override
    public boolean returnCard(iCard cardToBeReturned) {
        return false;
    }

    @Override
    public boolean scorePoints() {
        return false;
    }
}
