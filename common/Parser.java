package common;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;

/**
 * Created by Trevor on 1/21/2017.
 */
public class Parser
{
    public static final Parser SINGLETON = new Parser();

    private Parser(){}

    private static Gson gson = new Gson();
    private static String responseInt = "ResponseInt";
    private static String responseStr = "ResponseStr";

    // The method to deserialize a string using JSON
    public static String readString(InputStream is) throws IOException
    {
        JsonReader reader = new JsonReader(new InputStreamReader(is));
        String string = gson.fromJson(reader, String.class);


        return string;
    }

    // The method to deserialize a response using JSON
    public static Response readResponse(InputStream fileIn) throws IOException
    {
        Response response = null;
        JsonReader reader = new JsonReader(new InputStreamReader(fileIn));
        reader.beginObject();

        try
        {
            int intVal = 0;
            String str = null;

            while (reader.hasNext())
            {
                String name = reader.nextName();

                if (name.equals(responseInt))
                    intVal = reader.nextInt();

                else if (name.equals(responseStr))
                    str = reader.nextString();
            }

            response = new Response(str, intVal);
        }
        catch (Exception e)
        {
            System.out.println("Response couldn't be deserialized.");
            e.printStackTrace();
        }
        finally
        {
            reader.endObject();
            reader.close();
        }

        return response;
    }

    // The method to deserialize a command
    public static iCommand readCommand(InputStream fileIn) throws IOException
    {
        iCommand command = null;

        try
        {
            ObjectInputStream is = new ObjectInputStream(fileIn);
            command = (iCommand) is.readObject();

            is.close();
            fileIn.close();
        }
        catch (Exception e)
        {
            System.out.println("Command couldn't be deserialized.");
            e.printStackTrace();
        }

        return command;
    }

    // The method to serialize a command
    public static void writeCommand(iCommand command, OutputStream fileOut) throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(fileOut);
        os.writeObject(command);
        os.close();

        System.out.println("Command serialized\n");
    }

    // The method to serialize a response using JSON
    public static void writeResponse(Response response, OutputStream fileOut) throws IOException
    {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(fileOut));

        writer.beginObject();
        writer.name(responseInt).value(response.intValue);
        writer.name(responseStr).value(response.stringValue);
        writer.endObject();
        writer.close();

        System.out.println("Response serialized\n");
    }

    // The method to serialize a string using JSON
    public static void writeString(String str, OutputStream os) throws IOException
    {
        String s = gson.toJson(str);

        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(s);
        sw.close();
    }
}
