package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by colefox on 3/10/17.
 */

public class ResetIndexCommand extends Command implements iCommand, Serializable {
    public ResetIndexCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        Poller.getInstance().setQueueIndex(Integer.parseInt(data.getData()));
        return data;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
