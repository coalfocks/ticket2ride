package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;

/**
 * Created by Trevor on 2/11/2017.
 */
public class Poller
{
    private Poller(){}

    public static Poller getInstance()
    {
        if (poller == null)
        {
            poller = new Poller();
        }

        return poller;
    }

    private static Poller poller = null;
    private DataTransferObject gamesData = null;

    /**
     * Poll method, called every [amount of time] by ClientCommunicator
     * @return null if no information was received
     * @return DataTransferObject if there was pending information
     */
    public DataTransferObject poll()
    {
        TTRServerFacade facade = new TTRServerFacade();
        DataTransferObject previousData = new DataTransferObject(gamesData);

        gamesData = facade.listGames(previousData);

        if (gamesData.equals(previousData))
        {
            return null;
        }

        return gamesData;
    }

}
