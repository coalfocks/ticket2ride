package com.example.tyudy.ticket2rideclient;

import android.util.Log;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.commands.CreateGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.JoinGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.ListGamesCommand;
import com.example.tyudy.ticket2rideclient.common.commands.LoginCommand;
import com.example.tyudy.ticket2rideclient.common.commands.RegisterCommand;
import com.example.tyudy.ticket2rideclient.common.commands.StartGameCommand;
import com.example.tyudy.ticket2rideclient.interfaces.iTTRServer;
import com.google.gson.Gson;

/**
 * Created by tyudy on 2/27/17.
 */

public class ServerProxy implements iTTRServer {

    public static final ServerProxy SINGLETON = new ServerProxy();
    private Gson gson = new Gson();

    private ServerProxy(){}

    @Override
    public DataTransferObject createGame(DataTransferObject data) {
        Command newCommand = new CreateGameCommand();
        newCommand.setData(data);
        try {
            String commandString = Serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ServerPRoxy", e.getMessage());
        }
        return null;
    }

    @Override
    public DataTransferObject startGame(DataTransferObject data) {
        Command newCommand = new StartGameCommand();
        newCommand.setData(data);
        try {
            String commandString = Serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);
        } catch (Exception e){
            e.printStackTrace();
            Log.d("ServerProxy", e.getMessage());
        }
        return null;
    }

    @Override
    public DataTransferObject endGame(DataTransferObject data) {
        return null;
    }

    @Override
    public DataTransferObject listGames(DataTransferObject data) {
        Command newCommand = new ListGamesCommand();
        newCommand.setData(data);
        try {
            String commandString = Serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);

        } catch (Exception e){
            e.printStackTrace();
            Log.d("MethodsFacade", e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public DataTransferObject joinGame(DataTransferObject data) {
        try {
            // This is commented temporarily but it should probably pass a game
            //String s = serializer.serialize(gameToJoin);
            Command newCommand = new JoinGameCommand();
            newCommand.setData(data);
            String commandString = Serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);
        } catch (Exception e){
            e.printStackTrace();
            Log.d("ServerProxy", e.getMessage());
        }
        return null;
    }

    @Override
    /**
     * @param dto - contains an empty dto except for the data field which contains a space serperated string of
     *            the format 'username' 'password' so just parse that to get data.
     */
    public DataTransferObject login(DataTransferObject dto) {
        LoginCommand newCommand = new LoginCommand();
        newCommand.setData(dto);
        try {
            String commandString = Serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);
        } catch (Exception e){
            e.printStackTrace();
            Log.d("ServerProxy", e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public DataTransferObject register(DataTransferObject data) {
        Command newCommand = new RegisterCommand();
        newCommand.setData(data);
        try {
            String commandString = Serializer.serialize(newCommand);
            ClientCommunicator.getInstance().sendCommand(commandString);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ServerProxy", e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public DataTransferObject initializeTrainCards(DataTransferObject data) {
        // NEEDS TO BE IMPLEMENTED STILL
        return null;
    }

    @Override
    public DataTransferObject initializeDestinationCards(DataTransferObject data) {
        // NEEDS TO BE IMPLEMENTED STILL
        return null;
    }

    @Override
    public DataTransferObject initializeChatRoom(DataTransferObject data) {
        // NEEDS TO BE IMPLEMENTED STILL
        return null;
    }

    @Override
    public DataTransferObject sendChatMessage(DataTransferObject data) {
        // NEEDS TO BE IMPLEMENTED STILL
        return null;
    }

    @Override
    public DataTransferObject updateGameplay(DataTransferObject data) {
        // NEEDS TO BE IMPLEMENTED STILL
        return null;
    }
}
