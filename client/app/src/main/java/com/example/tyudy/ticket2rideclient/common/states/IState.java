package com.example.tyudy.ticket2rideclient.common.states;

import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;

/**
 * Created by Trevor on 3/15/2017.
 */

public interface IState {

    /**
     * This is the function to be called when a player
     * wants to draw a train card from the top of the
     * deck.
     * @return True if it's a valid action, false otherwise.
     * @pre Must be the current player's turn, and can't have
     *      chosen a Locomotive (wild) card face up previously,
     *      or already have drawn two cards.
     *      Must also not have drawn any destination cards, or
     *      claimed a path on this turn.
     * @post The action will be valid.
     */
    boolean drawCard();

    /**
     * This is the function to be called when a player
     * wants to draw new destination cards.
     * @return True if it's a valid action, false otherwise.
     * @pre Must be the current player's turn, and can't have
     *      taken any other actions, meaning drawing any train
     *      cards or claimed any paths.
     * @post The action will be valid.
     */
    boolean drawDest();

    /**
     * This is the function to be called when a player
     * wants to draw one of the face-up train cards.
     * @return True if it's a valid action, false otherwise.
     * @pre Must be the current player's turn, and can't have
     *      already drawn two train cards, or, if this is the
     *      second card picked or drawn by the player, it cannot be
     *      a Locomotive (wild) card. Can't have taken any
     *      other actions on this turn.
     * @post The action will be valid.
     *
     */
    boolean pickCard();

    /**
     * This is the function to be called when a player
     * wants to claim a path with the necessary amount
     * of train cards they currently have.
     * @return True if it's a valid action, false otherwise.
     * @pre Must be the current player's turn, and can't have
     *      taken any other actions already, meaning drawing
     *      train or destination cards. The current player
     *      must also have a sufficient number of color
     *      train cards of the necessary color.
     * @post The action will be valid.
     */
    boolean claimPath();

    /**
     * This is the function to be called when a player
     * wants to return one of the destination cards
     * they have just drawn earlier this turn.
     * @param cardToBeReturned The card to be returned.
     * @return True if the action is valid, false otherwise.
     * @pre Must be the current player's turn, and must
     *      have previously (on this turn) drawn destination
     *      cards.
     * @post The action will be valid.
     */
    boolean returnCard(iCard cardToBeReturned);

    /**
     * This is the function that is called at the end
     * of each player's turn. This function only adds
     * the new points gained this turn through claiming
     * paths, it doesn't calculate any completed destination
     * cards. (Those are calculated at the end of the game)
     * @return True if it's a valid function call, false otherwise.
     * @pre Must be the end of the current player's turn,
     *      or in other words, the player must have already
     *      drawn train cards, destination cards, or have
     *      claimed a path.
     * @post The action will be valid.
     */
    boolean scorePoints();
}
