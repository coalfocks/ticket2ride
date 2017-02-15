package com.example.tyudy.ticket2rideclient;

import java.io.*;
import android.util.Base64;

/**
 * Created by colefox on 2/11/17.
 */
public class Serializer
{
    public static String serialize(Serializable o) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }

    public static Object deserialize(String s) throws IOException, ClassNotFoundException
    {
        byte [] data = Base64.decode(s, Base64.DEFAULT);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
