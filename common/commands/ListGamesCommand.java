package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * The command that gets the list of games.
 */
public class ListGamesCommand extends Command implements iCommand, Serializable
{
  public ListGamesCommand(){}
private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
        TTRServerFacade facade = new TTRServerFacade();
        data = facade.listGames(data);
        return data;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
