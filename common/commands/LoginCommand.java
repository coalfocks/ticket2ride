package common.commands;

import common.Command;
import common.DataTransferObject;
import common.iCommand;
import server.TTRGameServer;
import server.TTRServerFacade;

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
