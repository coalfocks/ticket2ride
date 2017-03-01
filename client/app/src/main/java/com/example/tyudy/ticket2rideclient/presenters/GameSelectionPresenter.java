package com.example.tyudy.ticket2rideclient.presenters;

import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.fragments.GameSelectionFragment;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 2/24/17.
 */

public class GameSelectionPresenter {
    private GameSelectionFragment mGameSelectionFragment;

    public GameSelectionPresenter(){

    }

    // Called in the onCreate function in the GameSelectionFragment Class in the activities folder so that it can be updated.
    public void setGameSelectionFragment(GameSelectionFragment gameSelectionFragment) {
        mGameSelectionFragment = gameSelectionFragment;
    }

    public void createGameClicked(){
        if(ClientModel.SINGLETON.getCurrentTTRGame() == null) {
            MethodsFacade.SINGLETON.createGame();
        } else {
            Toast.makeText(mGameSelectionFragment.getContext(), "Leave your current game before creating a new one!", Toast.LENGTH_LONG).show();
        }
    }

}