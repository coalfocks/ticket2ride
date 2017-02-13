package server;

import common.DataTransferObject;

/**
 * Created by colefox on 2/5/17.
 */
public interface iTTRServer
{
    public DataTransferObject createGame(DataTransferObject data);

    public DataTransferObject startGame(DataTransferObject data);

    public DataTransferObject endGame(DataTransferObject data);

    public DataTransferObject joinGame(DataTransferObject data);

    public DataTransferObject login(DataTransferObject data);
}
