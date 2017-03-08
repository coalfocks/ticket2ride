package com.example.tyudy.ticket2rideclient.common.cards;


import com.example.tyudy.ticket2rideclient.common.Color;

/**
 * Created by zacheaton on 3/2/17.
 */

public class TrainCard {
    public Color color;
    public int num;
   public TrainCard(Color color){
        this.num = 0;
        this.color = color;
    }

   public TrainCard(){
        this.num = 0;
        this.color = color;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void incNum() {
        this.num = this.num++;
    }
}
