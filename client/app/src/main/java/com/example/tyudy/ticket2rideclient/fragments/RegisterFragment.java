package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.User;

/**
 * Created by tyudy on 2/7/17.
 */

public class RegisterFragment extends Fragment {

    private EditText mUserName;
    private EditText mPassword;
    private Button mRegisterButton;

    private String enteredName;
    private String enteredPassword;

    //TODO: There should be a back arrow up top to allow the user to go back to the login screen

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.register_fragment, container, false);

        mUserName = (EditText) v.findViewById(R.id.register_user_name_field);
        mUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing...
            }
        });

        mPassword = (EditText) v.findViewById(R.id.register_password_field);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enteredPassword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing...
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_screen_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Try to register the user
                User user = MethodsFacade.SINGLETON.registerUser(enteredName, enteredPassword);

                if(user != null){
                    Toast.makeText(getContext() , "Login Successful!" , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext() , "Login Failed!" , Toast.LENGTH_SHORT).show();
                }

            }
        });
        return v;
    }
}
