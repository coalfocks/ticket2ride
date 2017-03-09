package com.example.tyudy.ticket2rideclient.presenters;

import android.util.Log;
import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.commands.SendChatCommand;
import com.example.tyudy.ticket2rideclient.fragments.ChatFragment;

import com.example.tyudy.ticket2rideclient.fragments.RegisterFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.IOException;

/**
 * Created by tyudy on 3/6/17.
 */

public class ChatPresenter {
    private ChatFragment mChatFragment;
    private String mChatMessage;

    public ChatPresenter(){

    }

    // Called in the onCreate function in the ChatFragment Class in the Fragments folder so that it can be updated.
    public void setChatFragment(ChatFragment chatFragment) {
        mChatFragment = chatFragment;
    }

    public void chatEntered(String chat){
        mChatMessage = chat;
    }

    public void sendClicked(){
        MethodsFacade.SINGLETON.sendChatMessage(mChatMessage);
    }
}
