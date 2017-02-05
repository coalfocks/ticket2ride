package server.commands;

import common.Response;
import interfaces.ICommand;

import java.io.Serializable;

/**
 * Created by Trevor on 1/21/2017.
 */
public class Command implements ICommand, Serializable
{
    @Override
    public Response execute(String s)
    {
        return null;
    }
}
