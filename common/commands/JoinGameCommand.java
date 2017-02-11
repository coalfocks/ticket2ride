package common.commands;

import common.Command;
import common.DataTransferObject;
import common.iCommand;

import java.io.Serializable;

/**
 * Created by Trevor on 2/10/2017.
 */
public class JoinGameCommand extends Command implements iCommand, Serializable
{
    @Override
    public DataTransferObject execute(DataTransferObject dto)
    {
        // TODO
        return super.execute(dto);
    }
}
