package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;

import java.util.concurrent.TimeUnit;

/**
 * The ClientCommunicator class implements Runnable so as
 * to be its own thread in order to call on the poller
 * every set amount of time (setWait()). When the poller
 * receives something from the server, the ClientCommunicator
 * will receive it and send it to the ClientModel.
 */
public class ClientCommunicator implements Runnable
{
    private ClientCommunicator(){ poller = Poller.getInstance(); }

    public static ClientCommunicator getInstance()
    {
        if (communicator == null)
        {
            communicator = new ClientCommunicator();
        }

        return communicator;
    }

    private static ClientCommunicator communicator = null;
    private Poller poller = null;
    private boolean stop = false;
    private int wait = 2;           // default wait of 2 seconds

    public void run()
    {
        DataTransferObject dto;
        stop = false;

        while(!stop)
        {
            try
            {
                TimeUnit.SECONDS.sleep(wait);

                if ((dto = poller.poll()) != null)
                {
                    // TODO Send info to ClientModel
                }

            }
            catch (InterruptedException e){}
        }
    }


    public void stop() { stop = true; }

    public void setWait(int seconds)
    {
        wait = seconds;
    }
}
