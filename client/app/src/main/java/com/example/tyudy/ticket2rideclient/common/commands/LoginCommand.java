package com.example.tyudy.ticket2rideclient.common.commands;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;

import com.example.tyudy.ticket2rideclient.common.Command;
import com.example.tyudy.ticket2rideclient.common.iCommand;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 */
public class LoginCommand extends Command implements iCommand, Serializable
{
    @Override
    public DataTransferObject execute(DataTransferObject dto)
    {
        TTRServerFacade serverFacade = new TTRServerFacade();
        return serverFacade.login(dto);
    }
}
