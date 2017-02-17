package common;

import server.TTRServerFacade;

import java.io.Serializable;

/**
 * The main Command class which all the other command classes extend.
 */
public class Command implements iCommand, Serializable
{
    private DataTransferObject data;

    public void setData(DataTransferObject d)
    {
        this.data = d;
    }

    @Override
    public DataTransferObject execute()
    {
        TTRServerFacade facade = new TTRServerFacade();
        data = facade.endGame(data);
        return data;
    }
}
