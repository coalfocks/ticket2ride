package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

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
//        TTRServerFacade facade = new TTRServerFacade();
//        data = facade.createGame(data);
//        return data;
        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();
        if(data.getErrorMsg().length()!=0){
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        else{
            try {
                ClientModel.SINGLETON.setCurrentTTRGame((TTRGame) Serializer.deserialize(data.getData()));
                Toast.makeText(jeffery, "Created and Joined Game Successfully!", Toast.LENGTH_SHORT).show();
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
