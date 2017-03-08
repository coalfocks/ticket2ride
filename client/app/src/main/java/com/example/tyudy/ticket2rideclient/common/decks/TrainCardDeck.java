package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.Color;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;

import java.util.Collections;

/**
 * Created by zacheaton on 3/7/17.
 */
public class TrainCardDeck implements iDeck {

    //initialize the deck
    public TrainCardDeck(){
        for (Color c : Color.values()){
            for(int i = 0; i < 12; i++){
                TrainCard newCard = new TrainCard(c);
                this.addCard(newCard);
            }
        }
        TrainCard newWild = new TrainCard(Color.WILD);
        this.addCard(newWild);
        this.addCard(newWild);
        this.shuffle();
    }

    @Override
    public void shuffle()
    {
        Collections.shuffle(this.cards);
    }

    @Override
    public void addCard(iCard card)
    {
        this.cards.add(card);
    }

    @Override
    public iCard getCard()
    {
        if(cards.size()>0) {
            iCard myCard = cards.get(cards.size());
            cards.remove(cards.size());
            return myCard;
        }
        else{
            return null;
        }
    }
}
