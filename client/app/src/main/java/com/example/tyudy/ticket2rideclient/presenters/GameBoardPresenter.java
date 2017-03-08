package com.example.tyudy.ticket2rideclient.presenters;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.fragments.DisplayDestCardsDialogFragment;
import com.example.tyudy.ticket2rideclient.fragments.GameBoardFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

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

    public void showDestCards(){
//        Player player = mGameBoardFragment.getCurrentPlayer();
        User user = ClientModel.SINGLETON.getCurrentUser();
        mDialogFragment = new DisplayDestCardsDialogFragment();
        mDialogFragment.setCardList(user.getDestCards());
        mDialogFragment.setGameBoardActivity(mGameBoardFragment.getActivity());

        mDialogFragment.show(mGameBoardFragment.getActivity().getFragmentManager(), "Cards");
    }
}