package com.example.tyudy.ticket2rideclient.common;

import java.io.Serializable;

import static common.Response.TYPE.INT;
import static common.Response.TYPE.STRING;

/**
 * Created by Trevor on 1/21/2017.
 */
public class Response implements Serializable
{
    public enum TYPE implements Serializable {STRING, INT};
    public TYPE type;

    public int intValue = 0;
    public String stringValue = "";

    public Response(String s)
    {
        type = TYPE.STRING;
        stringValue = s;
    }

    public Response(int i)
    {
        type = TYPE.INT;
        intValue = i;
    }

    public Response(String s, int i)
    {
        stringValue = s;
        intValue = i;

        if (!stringValue.equals(""))
            type = TYPE.STRING;
        else
            type = TYPE.INT;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        if (type == TYPE.INT)
        {
            sb.append("INT: " + intValue);
        }
        else
        {
            sb.append("STRING: " + stringValue);
        }

        return sb.toString();
    }

    public static String ERROR = "ERROR IN RETURN TYPE";
    public static int ERROR_INT = -1;
}
