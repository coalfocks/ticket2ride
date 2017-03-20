package com.example.tyudy.ticket2rideclient.common.states.myturnstates;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;
import com.example.tyudy.ticket2rideclient.common.states.MyTurnState;

/**
 * Created by Trevor on 3/15/2017.
 */

public class NoAction extends MyTurnState {

    public NoAction(User currentUser) { super(currentUser);}

    @Override
    public boolean drawCard() {
        return super.drawCard();
    }

    @Override
    public boolean drawDest() {
        return super.drawDest();
    }

    @Override
    public boolean pickCard() {
        return super.pickCard();
    }

    @Override
    public boolean claimPath() {
        return super.claimPath();
    }

    @Override
    public boolean returnCard(iCard cardToBeReturned) {
        return super.returnCard(cardToBeReturned);
    }

    // You can't score points at the beginning of the turn
    @Override
    public boolean scorePoints() {
        return false;
    }
}
