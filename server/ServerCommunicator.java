package server;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.google.gson.Gson;

import common.DataTransferObject;
import common.iCommand;



    /**
     * Created by colefox on 1/19/17.
     */
    public class ServerCommunicator
    {

        private static final int MAX_WAITING_CONNECTION = 10;
        private HttpServer server;
        private static int SERVER_PORT_NUMBER = 8080;
        private Gson gson = new Gson();

        public void run()
        {
            try
            {
                server = HttpServer.create(new InetSocketAddress(InetAddress.getLocalHost()/*"127.0.0.1"*/, SERVER_PORT_NUMBER), MAX_WAITING_CONNECTION);
                System.out.print("Server created at " + InetAddress.getLocalHost());
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.print(e.getMessage());
                return;
            }
            server.setExecutor(null);
            server.createContext("/command", commandHandler);
            server.start();
        }


        private HttpHandler commandHandler = new HttpHandler()
        {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException
            {
                InputStream input = httpExchange.getRequestBody();
                try
                {
                    String body = streamToString(input);
                    iCommand command = gson.fromJson(body, iCommand.class);
                    DataTransferObject response = command.execute();
                    sendOutData(response, httpExchange);
                } catch (IOException e)
                {
                    e.printStackTrace();
                    sendOutData(new DataTransferObject("","","Failed. Connection error"), httpExchange);
                } catch (ClassCastException e)
                {
                    e.printStackTrace();
                    sendOutData(new DataTransferObject("","","Failed. No request body was found"), httpExchange);
                }

            }
        };

        private void sendOutData(Object data, HttpExchange exchange)
        {
            try
            {
                if (data != null)
                {

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    OutputStreamWriter sendBack = new OutputStreamWriter(exchange.getResponseBody(), Charset.forName("UTF-8"));
                    String json = gson.toJson(data);
                    sendBack.write(json);
                    sendBack.close();
                }
                else
                {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, -1);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
                System.out.println("\nError sending data " + e.getMessage());
            }
        }

        private String streamToString(InputStream in) throws IOException
        {
            StringBuilder out = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            for(String line = br.readLine(); line != null; line = br.readLine())
                out.append(line);
            br.close();
            return out.toString();
        }

        public void stop()
        {
            Runtime.getRuntime().exit(0);
        }

    }


