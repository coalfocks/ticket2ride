package com.example.tyudy.ticket2rideclient.common.decks;

import com.example.tyudy.ticket2rideclient.common.Color;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cards.iCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zacheaton on 3/7/17.
 */
public class TrainCardDeck implements iDeck, Serializable
{

    List<iCard> cards = new ArrayList<iCard>();

    //initialize the deck
    public TrainCardDeck(){
        for (Color c : Color.values()){
            for(int i = 0; i < 12; i++){
                TrainCard newCard = new TrainCard(c);
                this.addCard(newCard);
            }
        }
        TrainCard newWild = new TrainCard(Color.COLORLESS);
        this.addCard(newWild);
        this.addCard(newWild);
        this.shuffle();
    }
    public void shuffle(){
        Collections.shuffle(this.cards);
    };

    public  void addCard(iCard card){
        this.cards.add(card);
    }
    public  iCard getCard(){
        if(cards.size()>0) {
            iCard myCard = cards.get(cards.size() - 1);
            cards.remove(cards.size() - 1);
            return myCard;
        }
        else{
            return null;
        }
    }
}
