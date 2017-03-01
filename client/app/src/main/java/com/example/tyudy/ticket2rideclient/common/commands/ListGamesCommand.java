package com.example.tyudy.ticket2rideclient.common.commands;

import android.util.Log;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Trevor on 2/11/2017.
 */
public class ListGamesCommand extends Command implements iCommand, Serializable
{
    public ListGamesCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute()
    {
//        TTRServerFacade facade = new TTRServerFacade();
//        data = facade.listGames(data);
//        return data;
        try {
            ArrayList<TTRGame> gList = (ArrayList<TTRGame>) Serializer.deserialize(data.getData());
            MethodsFacade.SINGLETON.replaceModelsGames(gList);
        } catch (Exception e){
            Log.d("ListGamesCommand", e.getMessage());
        }
        return null;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
