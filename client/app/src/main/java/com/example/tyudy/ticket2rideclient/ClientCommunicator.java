package com.example.tyudy.ticket2rideclient;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.model.ClientModelFacade;
import com.example.tyudy.ticket2rideclient.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class ClientCommunicator {

    private Gson gson = new Gson();
    private static ClientCommunicator communicator = null;
    private DataTransferObject responseDTO;
    private FragmentActivity mContext;

    private ClientCommunicator(){
        responseDTO = null;
        mContext = null;
        // TODO: Uncomment this
        // new Thread(Poller.getInstance()).start();
    }

    public static ClientCommunicator getInstance() {
        if (communicator == null) {
            communicator = new ClientCommunicator();
        }

        return communicator;
    }

    public void sendCommand(String command)
    {
        /*DataTransferObject dto = null;
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
        */
        try{
            SendCommandTask task = new SendCommandTask();
            task.execute(command);
        } catch (Exception e){

        }

    }

    public void setContext(FragmentActivity context) {
        mContext = context;
    }

    private class SendCommandTask extends AsyncTask<String, Void, Void> {

       @Override
       protected Void doInBackground(String... params) {
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
               requestBody.write(params[0].getBytes());
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
           responseDTO = dto;
           return null;
       }

       @Override
       protected void onPostExecute(Void aVoid) {
           super.onPostExecute(aVoid);
           MethodsFacade.SINGLETON.processRegister(responseDTO, mContext);
       }
   }
}
