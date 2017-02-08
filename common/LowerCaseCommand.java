package common;

import server.StringProcessorSingleton;

import java.io.Serializable;

/**
 * Created by Trevor on 1/21/2017.
 */
public class LowerCaseCommand extends Command implements iCommand, Serializable
{
    @Override
    public DataTransferObject execute(String s)
    {
        return new Response(StringProcessorSingleton.SINGLETON.toLowerCase(s));
    }

    @Override
    public String toString()
    {
        return "LowerCase Command";
    }
}
