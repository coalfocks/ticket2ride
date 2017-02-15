package common.commands;

import common.Command;
import common.DataTransferObject;
import common.iCommand;
import server.TTRServerFacade;

import java.io.Serializable;

/**
 * Created by Trevor on 2/11/2017.
 */
public class EndGameCommand extends Command implements iCommand, Serializable
{
    @Override
    public DataTransferObject execute(DataTransferObject dto)
    {
        TTRServerFacade serverFacade = new TTRServerFacade();
        return serverFacade.endGame(dto);
    }
}
