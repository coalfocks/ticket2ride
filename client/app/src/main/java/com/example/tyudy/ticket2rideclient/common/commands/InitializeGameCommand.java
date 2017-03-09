package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.io.Serializable;

/**
 * Created by tyudy on 3/3/17.
 */
public class InitializeGameCommand extends Command implements iCommand, Serializable {
    private DataTransferObject data;

    @Override
    public void setData(DataTransferObject d) {
        data = d;
    }

    @Override
    public DataTransferObject execute() {
        try {
            TTRGame game = (TTRGame) Serializer.deserialize(data.getData());
            game.setInProgress(1);
            ClientModel.SINGLETON.setCurrentTTRGame(game);
            for (User u : game.getUsers()) {
                if (u.getPlayerID() == ClientModel.SINGLETON.getCurrentUser().getPlayerID()) {
                    ClientModel.SINGLETON.setCurrentUser(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
