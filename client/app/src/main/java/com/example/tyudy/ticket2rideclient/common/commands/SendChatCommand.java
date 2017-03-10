package com.example.tyudy.ticket2rideclient.common.commands;

import android.util.Log;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;

/**
 * Created by Trevor on 3/1/2017.
 */

public class SendChatCommand extends Command implements iCommand, Serializable {

    // The chat will be in the data section of the DataTransferObject
    private DataTransferObject data;

    @Override
    public void setData(DataTransferObject d) {
        data = d;
    }

    @Override
    public DataTransferObject execute() {
        try {
            String newChat = data.getData();
            MethodsFacade.SINGLETON.addChat(newChat);
        } catch (Exception e)
        {
            Log.d("SendChatCommand", e.getMessage());
        }

        return null;
    }
}
