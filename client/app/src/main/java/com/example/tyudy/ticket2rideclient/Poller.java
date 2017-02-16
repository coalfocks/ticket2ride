package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.TTRServerFacade;

/**
 * Created by Trevor on 2/11/2017.
 */
public class Poller  implements Runnable
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
                dto = poller.poll();
                if (!= null)
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

        DataTransferObject previousData = new DataTransferObject();
        previousData = gamesData;

//TODO implement getGameData in the MethodsFacade
//getgamedata gets the current data from the server for the gameslist
        gamesData = MethodsFacade.SINGLETON.getGameList();

//I don't like this at all I think we should see if they have changed some other way. Like compare thej
//should check if the data is null too
//this sucks but I have no time
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
