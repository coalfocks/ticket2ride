package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.activities.GameLobbyActivity;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by Trevor on 2/11/2017.
 */
public class StartGameCommand extends Command implements iCommand, Serializable
{
  public StartGameCommand(){}
private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
//      TTRServerFacade facade = new TTRServerFacade();
//      data = facade.startGame(data);
//      return data;
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length()!=0){
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(jeffery, "Game Started!", Toast.LENGTH_SHORT).show();
            ((GameLobbyActivity) jeffery).onStartGame();
        }
        return null;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
