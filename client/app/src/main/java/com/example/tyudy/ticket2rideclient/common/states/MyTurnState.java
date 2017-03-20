package com.example.tyudy.ticket2rideclient.common.states;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;

/**
 * Created by Trevor on 3/15/2017.
 */

public class MyTurnState implements IState {
    private User mUser;

    public MyTurnState(User currentUser) { mUser = currentUser; }

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
