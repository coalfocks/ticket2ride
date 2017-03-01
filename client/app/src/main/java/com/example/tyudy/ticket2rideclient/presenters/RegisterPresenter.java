package com.example.tyudy.ticket2rideclient.presenters;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.fragments.RegisterFragment;

/**
 * Created by tyudy on 2/24/17.
 * Any changes you see on screen in the RegisterFragment should happen by going through this presenter.
 */

public class RegisterPresenter {
    private RegisterFragment mRegisterFragment;

    private String mUserName;
    private String mPassword;

    public RegisterPresenter(){

    }

    // Called in the onCreate function in the RegisterFragment Class in the activities folder so that it can be updated.
    public void setRegisterFragment(RegisterFragment registerFragment) {
        mRegisterFragment = registerFragment;
    }

    public void userNameEntered(String userName) {
        mUserName = userName;
    }

    public void passwordEntered(String password){
        mPassword = password;
    }

    public void registerClicked(){
        // Try to register the user
        MethodsFacade.SINGLETON.registerUser(mUserName, mPassword);
    }
}