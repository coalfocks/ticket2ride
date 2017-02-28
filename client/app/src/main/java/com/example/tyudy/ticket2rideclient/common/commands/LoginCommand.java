package com.example.tyudy.ticket2rideclient.common.commands;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.activities.PreGameActivity;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 */
public class LoginCommand extends Command implements iCommand, Serializable
{
    public LoginCommand(){}
    private DataTransferObject data;

    @Override
    public DataTransferObject execute() {

        FragmentActivity jeffery = MethodsFacade.SINGLETON.getContext();

        if (data.getErrorMsg().length() != 0) {
            Toast.makeText(jeffery, data.getErrorMsg(), Toast.LENGTH_SHORT).show();
            return null;
        } else {
            try {
                User loggedInUser = (User) Serializer.deserialize(data.getData());
                ClientModel.SINGLETON.setCurrentUser(loggedInUser);
                // I am such a boss programmer for this line ..... it sets the current game
                ClientModel.SINGLETON.setCurrentTTRGame(ClientModel.SINGLETON.getTTRGameWithID(ClientModel.SINGLETON.getCurrentUser().getInGame()));
                Toast.makeText(jeffery, "Successful Login!", Toast.LENGTH_SHORT).show();
                ((PreGameActivity) jeffery).onLogin(loggedInUser);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(jeffery, "Invalid Login", Toast.LENGTH_SHORT).show();
                Log.d("LoginCommand", e.getMessage());
            }
        }
        return null;
    }


    public void setData(DataTransferObject d)
    {
        this.data = d;
    }
}
