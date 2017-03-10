package com.example.tyudy.ticket2rideclient;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.example.tyudy.ticket2rideclient.common.DataTransferObject;
import com.example.tyudy.ticket2rideclient.common.commands.ClaimPathCommand;
import com.example.tyudy.ticket2rideclient.common.commands.CreateGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.GetCommandsCommand;
import com.example.tyudy.ticket2rideclient.common.commands.InitializeGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.JoinGameCommand;
import com.example.tyudy.ticket2rideclient.common.commands.ListGamesCommand;
import com.example.tyudy.ticket2rideclient.common.commands.LoginCommand;
import com.example.tyudy.ticket2rideclient.common.commands.RegisterCommand;
import com.example.tyudy.ticket2rideclient.common.commands.ResetIndexCommand;
import com.example.tyudy.ticket2rideclient.common.commands.SendChatCommand;
import com.example.tyudy.ticket2rideclient.common.commands.StartGameCommand;
import com.example.tyudy.ticket2rideclient.model.ClientModel;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private ClientCommunicator(){
        new Thread(Poller.getInstance()).start();
    }

    public static ClientCommunicator getInstance() {
        if (communicator == null) {
            communicator = new ClientCommunicator();
        }

        return communicator;
    }

    public void sendCommand(String command)
    {

        try{
            SendCommandTask task = new SendCommandTask();
            task.execute(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class SendCommandTask extends AsyncTask<String, Void, Void> {

        private DataTransferObject responseDTO;

        public SendCommandTask(){
            responseDTO = new DataTransferObject();
        }

       @Override
       protected Void doInBackground(String... params) {
           DataTransferObject dto = null;
           try
           {
               String ipAddress = ClientModel.SINGLETON.getIpAddress();
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
                   responseDTO = gson.fromJson(responseData, DataTransferObject.class);
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
           return null;
       }

       @Override
       protected void onPostExecute(Void aVoid){
           super.onPostExecute(aVoid);
           String comType = responseDTO.getCommand();
           try {
               switch (comType) {
                   case "register":
                       //MethodsFacade.SINGLETON.processRegister(responseDTO);
                       RegisterCommand registerCommand = new RegisterCommand();
                       registerCommand.setData(responseDTO);
                       registerCommand.execute();
                       break;
                   case "login":
                      // MethodsFacade.SINGLETON.passBackDTOLogin(responseDTO);
                       LoginCommand loginCommand = new LoginCommand();
                       loginCommand.setData(responseDTO);
                       loginCommand.execute();
                       break;
                   case "join":
                       //MethodsFacade.SINGLETON.passBackDTOJoinGame(responseDTO);
                       JoinGameCommand joinGameCommand = new JoinGameCommand();
                       joinGameCommand.setData(responseDTO);
                       joinGameCommand.execute();
                       break;
                   case "create":
                      // MethodsFacade.SINGLETON.passBackDTOCreate(responseDTO);
                       CreateGameCommand createGameCommand = new CreateGameCommand();
                       createGameCommand.setData(responseDTO);
                       createGameCommand.execute();
                       break;
                   case "start":
                       //MethodsFacade.SINGLETON.passBackDTOStart(responseDTO);
                       StartGameCommand startGameCommand = new StartGameCommand();
                       startGameCommand.setData(responseDTO);
                    //   startGameCommand.execute();
                       break;
                   case "gameList":
                      // MethodsFacade.SINGLETON.updateGameList(responseDTO);
                       ListGamesCommand listGamesCommand = new ListGamesCommand();
                       listGamesCommand.setData(responseDTO);
                       listGamesCommand.execute();
                       break;
                    case "getCommands" :
                       GetCommandsCommand getCommandsCommand = new GetCommandsCommand();
                       getCommandsCommand.setData(responseDTO);
                       getCommandsCommand.execute();
                       break;
                   case "reset" :
                       ResetIndexCommand resetIndexCommand = new ResetIndexCommand();
                       resetIndexCommand.setData(responseDTO);
                       resetIndexCommand.execute();
                   default:
                       break;
               }

           } catch (Exception e) {
                e.printStackTrace();
                System.out.print("Error in the switch statement in ClientCommunicator");
           }
       }
   }
}
