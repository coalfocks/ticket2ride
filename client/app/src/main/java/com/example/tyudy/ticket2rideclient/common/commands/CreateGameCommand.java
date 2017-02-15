package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 private DataTransferObject data;


 public testcommand(){}

 public void setData(DataTransferObject d)
 {
     this.data = d;
 }

 @Override
 public DataTransferObject execute()
 {
     TTRServerFacade facade = new TTRServerFacade();
     data = facade.endGame(data);
     return data;
 }
 */
public class CreateGameCommand extends Command implements iCommand, Serializable
{


  public CreateGameCommand(){}
private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        TTRServerFacade facade = new TTRServerFacade();
        data = facade.createGame(data);
        return data;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
