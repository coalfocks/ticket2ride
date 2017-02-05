package server.commands;

import common.Response;
import interfaces.ICommand;
import server.StringProcessorSingleton;

import java.io.Serializable;

/**
 * Created by Trevor on 1/21/2017.
 */
public class ParseIntCommand extends Command implements ICommand, Serializable
{
    @Override
    public Response execute(String s)
    {
        return new Response(StringProcessorSingleton.SINGLETON.parseInteger(s));
    }

    @Override
    public String toString()
    {
        return "ParseInt Command";
    }
}
