package com.example.tyudy.ticket2rideclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.ClientCommunicator;
import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.example.tyudy.ticket2rideclient.presenters.LoginPresenter;
import com.example.tyudy.ticket2rideclient.presenters.PresenterHolder;

/**
 * Created by tyudy on 2/6/17.
 */

public class LoginFragment extends Fragment {

    private EditText mUserField;
    private EditText mPasswordField;
    private EditText mIpAddressField;
    private Button mLoginButton;
    private Button mRegisterButton;

    private String ipAddress;
    private LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        mLoginPresenter = PresenterHolder.SINGLETON.getLoginPresenter();
        mLoginPresenter.setLoginFragment(this); // Presenter needs to know this instance

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        mIpAddressField = (EditText) v.findViewById(R.id.ip_address_field);
        mIpAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLoginPresenter.ipAddressEntered(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing...
            }
        });

        mUserField = (EditText) v.findViewById(R.id.user_name_field);
        mUserField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLoginPresenter.userNameEntered(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing...
            }
        });

        mPasswordField = (EditText) v.findViewById(R.id.password_field);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing...
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLoginPresenter.passwordEntered(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mLoginButton = (Button) v.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.loginClicked();
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.registerClicked();
            }
        });

        return v;
    }
}