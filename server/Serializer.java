package server;

import java.io.*;
import java.util.Base64;

/**
 * Created by colefox on 2/11/17.
 */
public class Serializer
{
    /**
     * The method to serialize an object
     * @param o Serializable object
     * @return The serialized object
     * @throws IOException
     */
    public static String serialize(Serializable o) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * The method to deserialize an object
     * @param s The string to deserialize
     * @return The deserialized object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(String s) throws IOException, ClassNotFoundException
    {
        byte [] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
