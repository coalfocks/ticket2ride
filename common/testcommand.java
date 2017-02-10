package common;

import server.TTRServerFacade;

import java.io.Serializable;

/**
 * Created by colefox on 2/9/17.
 */
public class testcommand implements iCommand,Serializable
{
    private DataTransferObject data;


    public testcommand(){}

    public void setData(DataTransferObject d)
    {
        this.data = d;
    }

    @Override
    public DataTransferObject execute()
    {
        TTRServerFacade facade = new TTRServerFacade();
        data = facade.register(data);
        return data;
    }
}
