package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
 * Created by tyudy on 2/6/17.
 */

public class LoginFragment extends Fragment {

    private EditText mUserField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mRegisterButton;

    private String password;
    private String userName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        mUserField = (EditText) v.findViewById(R.id.user_name_field);
        mUserField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing...
            }
        });

        mPasswordField = (EditText) v.findViewById(R.id.user_name_field);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLoginButton = (Button) v.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = MethodsFacade.SINGLETON.loginUser(userName, password);
                // TODO: base the success variable off of what login returns;
                if(user != null){
                    // Go to waiting room
                } else {
                    // Display toast
                    Toast.makeText(getContext() , "Invalid user name or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to register activity
                Toast.makeText(getContext() , "Register a new user name and password!", Toast.LENGTH_SHORT).show();
                Fragment registerFragment = new RegisterFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, registerFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
            }
        });



        return v;
    }
}
