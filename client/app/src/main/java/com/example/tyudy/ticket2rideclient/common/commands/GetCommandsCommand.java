package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.Poller;
import com.example.tyudy.ticket2rideclient.Serializer;
import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.DataTransferObject;

import com.example.tyudy.ticket2rideclient.common.iCommand;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by colefox on 3/3/17.
 */
public class GetCommandsCommand extends Command implements iCommand, Serializable {
    private DataTransferObject data;

    @Override
    public void setData(DataTransferObject d) {
        data = d;
    }

    @Override
    public DataTransferObject execute() {
        try {
            ArrayList<Command> commands = (ArrayList<Command>) Serializer.deserialize(data.getData());
            for (Command c : commands) {
                c.execute();
            }
            Poller.getInstance().incIndex(commands.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
