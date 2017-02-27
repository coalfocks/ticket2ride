package com.example.tyudy.ticket2rideclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.R;

/**
 * Created by Trevor on 2/23/2017.
 */

public class ChatFragment extends Fragment {
    private LinearLayoutManager llm;
    private Button send;
    private RecyclerView chat_recycler;
    private EditText chat_box;
    private String chat_message;
    private RecyclerView.Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClientCommunicator.getInstance().setContext(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_fragment, container, false);

        llm = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, true);
        chat_message = "";
        send = (Button) v.findViewById(R.id.send_button);
        chat_box = (EditText) v.findViewById(R.id.chat_box);
        chat_recycler = (RecyclerView) v.findViewById(R.id.chat_recycler);

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
                if (!chat_message.equals("")) {
                    // TODO Send chat message to server


                    chat_message = ""; // Reset chat message
                }
            }
        });

        // TODO Receive messages from the server and update in recycler view


        return v;
    }
}
