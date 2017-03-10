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
import com.example.tyudy.ticket2rideclient.presenters.ChatPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trevor on 2/23/2017.
 */

public class ChatFragment extends Fragment implements iObserver {
    private LinearLayoutManager llm;
    private Button mSendButton;
    private RecyclerView mChatRecyclerView;
    private EditText mChatBox;
    private ChatsAdapter mChatsAdapter;

    ChatPresenter mChatPresenter = PresenterHolder.SINGLETON.getChatPresenter();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MethodsFacade.SINGLETON.setContext(this.getActivity());
        ClientModel.SINGLETON.addObserver(this);
        PresenterHolder.SINGLETON.getChatPresenter().setChatFragment(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_fragment, container, false);

        mSendButton = (Button) v.findViewById(R.id.send_button);
        mChatBox = (EditText) v.findViewById(R.id.chat_box);

        mChatRecyclerView = (RecyclerView) v.findViewById(R.id.chat_recycler);
        llm = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, true);
        llm.setReverseLayout(true);
        mChatRecyclerView.setLayoutManager(llm);
        observe();


        mChatBox.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                //mChatBox.setText(""); // Automatically remove current text
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mChatPresenter.chatEntered(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Nothing
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChatPresenter.sendClicked();
            }
        });

        return v;
    }

//    /**
//     * Call this function to add a chat to the RecyclerView
//     * @param chat
//     */
//    public void addChat(String chat)
//    {
//        adapter.addMessage(chat);
//    }

    /**
     * This is the method called when there's information to update.
     * Implemented from iObserver
     */
    @Override
    public void observe() {
        // Update the adapter to use the updated model
        mChatsAdapter = null;
        mChatsAdapter = new ChatsAdapter(ClientModel.SINGLETON.getChatMsgs());
        mChatRecyclerView.setAdapter(mChatsAdapter);

    }

    // -------------------------------------Adapter and ViewHolder Classes---------------------------------------

    public class ChatsAdapter extends RecyclerView.Adapter<ChatsViewHolder> {

        private List<String> messages;
//        private int nextMsgPosition;
//        private final int MAX_MESSAGES = 15;

        public ChatsAdapter(List<String> items) {
            this.messages = items;

//            if (items != null) {
//                nextMsgPosition = items.size();
//                if (nextMsgPosition > MAX_MESSAGES){
//                    nextMsgPosition = MAX_MESSAGES;
//                }
//            } else {
//                nextMsgPosition = 0;
//            }
        }

        @Override
        public ChatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.message_holder, parent, false);
            return new ChatsViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ChatsViewHolder holder, int position) {
            String message = messages.get(position);
            holder.bind(message);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

    }


    public class ChatsViewHolder extends RecyclerView.ViewHolder {
        public TextView mChatText;

        public ChatsViewHolder(View itemView) {
            super(itemView);
            mChatText = (TextView) itemView.findViewById(R.id.message_holder_text);
        }

        public void bind(String s){
            mChatText.setText(s);
        }
    }
}