package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 */
public class JoinGameCommand extends Command implements iCommand, Serializable
{
  public JoinGameCommand(){}
private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        TTRServerFacade facade = new TTRServerFacade();
        data = facade.joinGame(data);
        return data;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
