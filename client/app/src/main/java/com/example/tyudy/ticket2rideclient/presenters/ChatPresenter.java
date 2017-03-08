package com.example.tyudy.ticket2rideclient.presenters;

import android.util.Log;
import android.widget.EditText;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.commands.SendChatCommand;
import com.example.tyudy.ticket2rideclient.fragments.ChatFragment;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trevor on 3/3/2017.
 */

public class ChatPresenter implements iObserver {
    private ChatFragment mChatFragment;
    private String mChatMessage;

    public ChatPresenter()
    {
        mChatFragment = null;
        mChatMessage = "";
        ClientModel.SINGLETON.addObserver(this);
    }

    /**
     * This method is called when the ChatFragment is created
     * @param cf An instance of the ChatFragment
     */
    public void setChatFragment(ChatFragment cf)
    {
        mChatFragment = cf;
    }

    /**
     * This method is called when the Send button is pressed in the ChatFragment.
     * If the string in the chat box is not empty, the message is sent to the
     * server.
     * @param chat_box This is a parameter purely to be able to reset the contents
     *                 of the chat_box after the message has been sent.
     */
    public void send(EditText chat_box)
    {
        if (!mChatMessage.equals(""))
        {
            // Before sending to the server, patch on player's name before message
            String playerName = ClientModel.SINGLETON.getCurrentUser().getUsername();
            StringBuilder message =
                    new StringBuilder(playerName + ": " + mChatMessage);

            SendChatCommand command = new SendChatCommand();
            DataTransferObject dto = new DataTransferObject();
            dto.setData(message.toString());
            dto.setCommand("sendChat");
            dto.setPlayerID(ClientModel.SINGLETON.getCurrentUser().getPlayerID());
            command.setData(dto);

            try {
                ClientCommunicator.getInstance().sendCommand(Serializer.serialize(command));
            } catch (IOException e)
            {
                e.printStackTrace();
                Log.d("ChatFragment", e.getMessage());
            }

            mChatMessage = ""; // Reset chat message
            chat_box.setText(mChatMessage);
        }
    }

    /**
     * Setter for message
     * @param message Sets mChatMessage equal to message
     */
    public void setMessage(String message)
    {
        mChatMessage = message;
    }

    /**
     * This is the method called when there's information to update.
     * Implemented from iObserver
     */
    @Override
    public void observe() {

        /* [THIS COMMENTED SECTION IS FOR IF WE WANT TO GET ALL CHAT
            MESSAGES IN THE HISTORY OF THE CURRENT GAME]

        List<String> chats = ClientModel.SINGLETON.getChatMsgs();
        List<String> currList = mChatFragment.getChatList();

        for (String chat : chats)
        {
            // Don't duplicate chat messages
            if (!currList.contains(chat))
                mChatFragment.addChat(chat);
        }
        */

        // Updates the ChatFragment with the most recent message from server
        mChatFragment.addChat(ClientModel.SINGLETON.getMostRecentMessage());
    }
}
