package com.example.tyudy.ticket2rideclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.commands.SendChatCommand;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trevor on 2/23/2017.
 */

public class ChatFragment extends Fragment implements iObserver {
    private LinearLayoutManager llm;
    private Button send;
    private RecyclerView chat_recycler;
    private EditText chat_box;
    private RecyclerAdapter adapter;
    private String chat_message;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ClientCommunicator.getInstance().setContext(this.getActivity());
        MethodsFacade.SINGLETON.setContext(this.getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_fragment, container, false);
        chat_message = "";

        llm = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, true);
        adapter = new RecyclerAdapter(ClientModel.SINGLETON.getChatMsgs(), llm.getLayoutDirection());

        send = (Button) v.findViewById(R.id.send_button);
        chat_box = (EditText) v.findViewById(R.id.chat_box);

        chat_recycler = (RecyclerView) v.findViewById(R.id.chat_recycler);
        chat_recycler.setLayoutManager(llm);
        chat_recycler.setAdapter(adapter);

        chat_box.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                chat_box.setText(""); // Automatically remove current text
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                chat_message = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Nothing
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // We don't want to send an empty string to the server
                if (!chat_message.equals("")) {
                    // Before sending to the server, patch on player's name before message
                    String playerName = ClientModel.SINGLETON.getCurrentPlayer().getName();
                    StringBuilder message =
                            new StringBuilder(playerName + ": " + chat_message);

                    SendChatCommand command = new SendChatCommand();
                    DataTransferObject dto = new DataTransferObject();
                    dto.setData(message.toString());
                    command.setData(dto);

                    try {
                        ClientCommunicator.getInstance().sendCommand(Serializer.serialize(command));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                        Log.d("ChatFragment", e.getMessage());
                    }

                    chat_message = ""; // Reset chat message
                }
            }
        });

        return v;
    }

    /**
     * Call this function to add a chat to the RecyclerView
     * @param chat
     */
    public void addChat(String chat)
    {
        adapter.addMessage(chat);
    }

    /**
     * This is the method called when there's information to update.
     * Implemented from iObserver
     */
    @Override
    public void observe() {
        List<String> chats = ClientModel.SINGLETON.getChatMsgs();
        List<String> currList = adapter.getCurrentMessages();

        for (String chat : chats)
        {
            // Don't duplicate chat messages
            if (!currList.contains(chat))
                addChat(chat);
        }
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private List<String> messages;
        private int itemLayout;
        private int nextMsgPosition;
        private final int MAX_MESSAGES = 15;

        public RecyclerAdapter(List<String> items, int itemLayout) {
            this.messages = items;
            this.itemLayout = itemLayout;

            if (items != null) {
                nextMsgPosition = items.size();
                if (nextMsgPosition > MAX_MESSAGES)
                    nextMsgPosition = MAX_MESSAGES;
            }
            else
                nextMsgPosition = 0;
        }

        public List<String> getCurrentMessages() {
            return messages;
        }

        public void addMessage(String item) {
            // Only increase position if the max message count hasn't been reached
            if (nextMsgPosition == MAX_MESSAGES)
            {
                messages.remove(0);
                notifyItemRemoved(0);

                messages.add(nextMsgPosition, item);
                notifyItemInserted(nextMsgPosition);
            }
            else
            {
                messages.add(nextMsgPosition, item);
                notifyItemInserted(nextMsgPosition);
                nextMsgPosition++;
            }
        }

        @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            return new ViewHolder(v);
        }

        @Override public void onBindViewHolder(ViewHolder holder, int position) {
            String item = messages.get(position);
            holder.text.setText(item);
        }

        @Override public int getItemCount() {
            return messages.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView text;

            public ViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }
    }
}
