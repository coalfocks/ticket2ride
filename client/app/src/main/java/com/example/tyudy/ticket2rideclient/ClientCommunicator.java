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


//    DataTransferObject data = new DataTransferObject();
//    User me = new User("cole","fox",1,0);
//    String s = gson.toJson(me);
//data.setData("15");
//        data.setCommand("JoinGame");
//        data.setPlayerID(1);
//
//        testcommand test = new testcommand();
//        test.setData(data);
//
//        DataTransferObject dto = null;
//        try
//        {
//        String urlString = "http://127.0.0.1:8080/command";
//        URL url = new URL(urlString);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        connection.connect();
//
//        String commandString = toString(test);
//        OutputStream requestBody = connection.getOutputStream();
//        requestBody.write(commandString.getBytes());
//        requestBody.flush();
//        requestBody.close();
//
//        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
//        {
//        InputStream responseBody = connection.getInputStream();
//        ByteArrayOutputStream bouts = new ByteArrayOutputStream();
//        byte [] buffer = new byte [1024];
//        int length = 0;
//        while ((length = responseBody.read(buffer)) != -1)
//        {
//        bouts.write(buffer, 0, length);
//        }
//
//        String responseData = bouts.toString();
//        dto = gson.fromJson(responseData, DataTransferObject.class);
//        }
//        else
//        {
//        dto = new DataTransferObject(data.getCommand(), "", "Could not connect!",null);
//        System.out.print(dto.getErrorMsg());
//        }
//
//        } catch (Exception e)
//        {
//        e.printStackTrace();
//        }
