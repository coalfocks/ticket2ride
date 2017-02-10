import com.google.gson.Gson;
import common.Command;
import common.DataTransferObject;
import common.iCommand;
import common.testcommand;
import server.ServerCommunicator;
import server.TTRServerFacade;
import server.User;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.URL;
import java.util.Base64;

/**
 * Created by colefox on 2/9/17.
 */
public class main
{

    public static void main(String[] args)
    {
        Gson gson = new Gson();

        ServerCommunicator server = new ServerCommunicator();
        server.run();

        DataTransferObject data = new DataTransferObject();
        User me = new User("cole","fox",0,0);
        String s = gson.toJson(me);
        data.setData(s);
        data.setCommand("Login");

        testcommand test = new testcommand();
        test.setData(data);

        DataTransferObject dto = null;
        try
        {
            String urlString = "http://127.0.0.1:8080/command";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            String commandString = toString(test);
            OutputStream requestBody = connection.getOutputStream();
            requestBody.write(commandString.getBytes());
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
                dto = new DataTransferObject(data.getCommand(), "", "Could not connect!");
                System.out.print(dto.getErrorMsg());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(dto.getData());
        System.out.println(dto.getErrorMsg());
        server.stop();
    }

    private static String toString( Serializable o ) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

}
