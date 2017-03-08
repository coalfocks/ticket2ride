package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.cards.iCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zacheaton on 3/7/17.
 */
public interface iDeck {
    List<iCard> cards = new ArrayList<iCard>();

    public void shuffle();

    public void addCard(iCard card);

    public iCard getCard();
}
