package client;

import common.DataTransferObject;

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

    /**
     * Poll method, called every [amount of time] by ClientCommunicator
     * @return null if no information was received
     * @return DataTransferObject if there was pending information
     */
    public DataTransferObject poll()
    {
        return null;
    }

}
