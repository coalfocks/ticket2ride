package com.example.tyudy.ticket2rideclient.common.cards;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;

import java.io.Serializable;

/**
 * Created by zacheaton on 3/2/17.
 */

/**
 * this class is more of a train card stack of specific colors rather than a single card
 * the num corresponds to how many of the specific mColorENUM you have
 *
 */
public class TrainCard implements iCard, Serializable {

    public ColorENUM mColorENUM;
    public int num;
    public TrainCard(ColorENUM colorENUM){

        this.num = 0;
        this.mColorENUM = colorENUM;
    }

    public TrainCard(){
        this.num = 0;
        this.mColorENUM = mColorENUM;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }


    public void setColor(ColorENUM color) {
        this.color = color;
    }

    public ColorENUM getColor() {
        return color;
    }

    public void incNum() {
        this.num = this.num++;
    }
}
