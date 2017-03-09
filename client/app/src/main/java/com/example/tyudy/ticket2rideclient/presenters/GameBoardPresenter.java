package com.example.tyudy.ticket2rideclient.presenters;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;

/**
 * Created by tyudy on 2/24/17.
 */

public class GameBoardPresenter {
    private GameBoardFragment mGameBoardFragment;
    private DisplayDestCardsDialogFragment mDialogFragment;

    public GameBoardPresenter(){
        mDialogFragment = new DisplayDestCardsDialogFragment();
    }

    // Called in the onCreate function in the GameBoardFragment Class in the fragments folder so that it can be updated.
    public void setGameBoardFragment(GameBoardFragment gameBoardFragment) {
        mGameBoardFragment = gameBoardFragment;
    }

    /**
     * Method called when the button is pressed to show the current user
     * his/her destination cards
     */
    public void showDestCards(){
        User player = mGameBoardFragment.getCurrentUser();
        mDialogFragment = new DisplayDestCardsDialogFragment();
        mDialogFragment.setCardList(player.getDestCards());
        mDialogFragment.setGameBoardActivity(mGameBoardFragment.getActivity());

        mDialogFragment.show(mGameBoardFragment.getActivity().getFragmentManager(), "Cards");
    }
}