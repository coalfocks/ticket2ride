package common;

import server.TTRServerFacade;

import java.io.Serializable;

/**
 * Created by Trevor on 1/21/2017.
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
