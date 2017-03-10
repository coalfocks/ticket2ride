package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

/**
 * Created by colefox on 3/10/17.
 */

public class AddTrainCardCommand extends Command implements iCommand, Serializable {
    public AddTrainCardCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length()!=0){
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else {
            try {
                TrainCard card = (TrainCard) Serializer.deserialize(data.getData());
                ClientModel.SINGLETON.addTrainCard(card, data.getPlayerID());
                Toast.makeText(jeffery, "Card Added", Toast.LENGTH_SHORT).show();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
