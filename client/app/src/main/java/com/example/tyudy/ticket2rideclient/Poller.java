package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.sql.ClientInfoStatus;
import java.util.concurrent.TimeUnit;

/**
 * Created by Trevor on 2/11/2017.
 */
public class Poller  implements Runnable
{


    private static Poller poller;
    private boolean stop;
    private int wait;


    private Poller(){
        poller = null;
        stop = false;
        wait = 2;

    }

    public static Poller getInstance()
    {
        if (poller == null)
        {
            poller = new Poller();
        }

        return poller;
    }

    // default wait of 2 seconds

    public void run()
    {
        stop = false;

        while(!stop)
        {
            try
            {
                TimeUnit.SECONDS.sleep(wait);
                poller.poll();

            }
            catch (InterruptedException e){
              e.printStackTrace();
            }
        }
    }


    public void stop() { stop = true; }

    public void setWait(int seconds)
    {
        wait = seconds;
    }

    /**
     * Poll method, called every [amount of time] by ClientCommunicator
     */
    public void poll() {
        if (ClientModel.SINGLETON.getIpAddress() == null) {
            return;
        }

        // Only pull new games if the client is not playing a game, if they are playing then stop polling for the game list
        if ((ClientModel.SINGLETON.getCurrentTTRGame() != null &&
                ClientModel.SINGLETON.getCurrentTTRGame().getInProgress() != 1) ||
                (ClientModel.SINGLETON.getCurrentUser() != null)) {

            MethodsFacade.SINGLETON.getGameList();
        }
    }

}
