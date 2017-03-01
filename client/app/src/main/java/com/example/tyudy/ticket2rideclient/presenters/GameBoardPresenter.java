package com.example.tyudy.ticket2rideclient.presenters;

import com.example.tyudy.ticket2rideclient.activities.GameBoardActivity;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;

/**
 * Created by tyudy on 2/24/17.
 */

public class GameBoardPresenter {
    private GameBoardFragment mGameBoardFragment;

    public GameBoardPresenter(){

    }

    // Called in the onCreate function in the GameBoardFragment Class in the fragments folder so that it can be updated.
    public void setGameBoardFragment(GameBoardFragment gameBoardFragment) {
        mGameBoardFragment = gameBoardFragment;
    }
}