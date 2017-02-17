package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;

import java.io.Serializable;

/**
 * The command to register.
 */
public class RegisterCommand extends Command implements iCommand, Serializable
{
  public RegisterCommand(){}
private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        TTRServerFacade facade = new TTRServerFacade();
        data = facade.register(data);
        return data;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
