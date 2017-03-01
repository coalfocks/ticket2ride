package com.example.tyudy.ticket2rideclient.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.MethodsFacade;
import com.example.tyudy.ticket2rideclient.R;
import com.example.tyudy.ticket2rideclient.fragments.LoginFragment;
import com.example.tyudy.ticket2rideclient.fragments.RegisterFragment;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

/**
 * Created by tyudy on 2/24/17.
 * Any changes you see on screen in the LoginFragment should happen by going through this presenter.
 */

public class LoginPresenter {
    private LoginFragment mLoginFragment;
    private String mPassword;
    private String mUserName;
    private String mIpAddress;

    public LoginPresenter(){

    }

    // Called in the onCreate function in the LoginFragment Class in the activities folder so that it can be updated.
    public void setLoginFragment(LoginFragment loginFragment) {
        mLoginFragment = loginFragment;
    }

    public void loginClicked(){
        setModelIpAddress();
        if(!ipAddressIsSet()){ // Do nothing if IP Address is not set
            Toast.makeText(mLoginFragment.getContext() , "Please Enter an IP Address", Toast.LENGTH_SHORT).show();
            return;
        }
        MethodsFacade.SINGLETON.loginUser(mUserName, mPassword);
    }

    public void registerClicked(){
        setModelIpAddress();
        if(!ipAddressIsSet()){ // Do nothing if IP Address is not set
            return;
        }

        // Go to register activity
        Toast.makeText(mLoginFragment.getContext() , "Register a new user name and password!", Toast.LENGTH_SHORT).show();
        Fragment registerFragment = new RegisterFragment();
        FragmentTransaction ft = mLoginFragment.getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, registerFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void passwordEntered(String password){
        mPassword = password;
    }

    public void userNameEntered(String userName){
        mUserName = userName;
    }

    public void ipAddressEntered(String adr) {
        mIpAddress = adr;
    }

    //-------------------------------------- Helper Function ----------------------------------

    /**
     * Set the ip Address on the model so it can be used in different places
     */
    public void setModelIpAddress(){
        ClientModel.SINGLETON.setIpAddress(mIpAddress);
    }

    /**
     * Checks to make sure the ipAddress has been set and notifies the user if it hasn't
     * @return - true if set false if not
     */
    public boolean ipAddressIsSet(){
        if (mIpAddress == null){
            Toast.makeText(mLoginFragment.getContext() , "Enter an IP Address!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}