package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.model.ClientModel;

import java.util.concurrent.TimeUnit;

/**
 * Created by Trevor on 2/11/2017.
 */
public class Poller  implements Runnable
{


    private static Poller poller;
    private DataTransferObject gamesData;
    private boolean stop;
    private int wait;


    private Poller(){
        poller = null;
        gamesData = new DataTransferObject();
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
        DataTransferObject dto;
        stop = false;

        while(!stop)
        {
            try
            {
                TimeUnit.SECONDS.sleep(wait);
                dto = poller.poll();
                if ( dto != null)
                {
                    // TODO this is where the gamelist has changed and needs to be updated

                    MethodsFacade.SINGLETON.updateGameList(dto);
                }

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
     * @return null if no change to information
     * @return DataTransferObject if there was pending information
     */
    public DataTransferObject poll()
    {
        if(ClientModel.SINGLETON.getIpAddress() == null){
            return null;
        }

        DataTransferObject previousData = new DataTransferObject();
        previousData = gamesData;

        gamesData = MethodsFacade.SINGLETON.getGameList();


    if(gamesData != null){
      if(gamesData.getData() != null){
        if (gamesData.getData().equals(previousData.getData()) )
        {
            return null;
        }
        return gamesData;
      }
      else{
        return null;
      }
    }
      else{
        return null;
      }
    }

}
