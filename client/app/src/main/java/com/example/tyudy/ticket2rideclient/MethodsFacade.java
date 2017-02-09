package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.model.User;

/**
 * Created by tyudy on 2/6/17.
 */

public class MethodsFacade {

    public static final MethodsFacade SINGLETON = new MethodsFacade();

    private MethodsFacade(){

    }

    /**
     * Function called by the LoginFragment when the Login button is clicked.
     * @param enteredName - string that was typed into the User Name field.
     * @param enteredPassword - string that was typed into the Password field.
     */
    public void readUserInfo(String enteredName, String enteredPassword){

    }

    /**
     * Function called by the LoginFragment when the Login button is clicked.
     * Logs the user in and returns a user object if successful, and a null object if not successful.
     * NOTE: we could also do the checking in another function called like canDoLogin or somethings which
     *    is what Dr.Woodfield suggested in class, then this function wouldnt run into errors when loggin in.
     */
    public User login(){
        return null;
    }


}
