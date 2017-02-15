package com.example.tyudy.ticket2rideclient;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.model.ClientModelFacade;
import com.example.tyudy.ticket2rideclient.model.User;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private Gson gson = new Gson();

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


    public DataTransferObject sendCommand(String command)
    {
        DataTransferObject dto = null;
        try
        {
            String ipAddress = ClientModelFacade.SINGLETON.getIpAddress();
            String urlString = "http://" + ipAddress + ":8080/command";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            OutputStream requestBody = connection.getOutputStream();
            requestBody.write(command.getBytes());
            requestBody.flush();
            requestBody.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream responseBody = connection.getInputStream();
                ByteArrayOutputStream bouts = new ByteArrayOutputStream();
                byte [] buffer = new byte [1024];
                int length = 0;
                while ((length = responseBody.read(buffer)) != -1)
                {
                    bouts.write(buffer, 0, length);
                }

                String responseData = bouts.toString();
                dto = gson.fromJson(responseData, DataTransferObject.class);
            }
            else
            {
                dto = new DataTransferObject("Error", "", "Could not connect!",null);
                System.out.print(dto.getErrorMsg());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dto;

    }
}
