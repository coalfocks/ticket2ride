package common;

/**
 * Created by colefox on 1/20/17.
 */
public class DataTransferObject
{
    private int playerID;
    private String command;
    private String data;
    private String errorMsg;

    public DataTransferObject(String c, String d, String e)
    {
        command = c;
        data = d;
        errorMsg = e;
    }

    public DataTransferObject()
    {
        command = "";
        data = "";
        errorMsg = "";
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }
    
    public int getPlayerID()
    {
        return playerID;
    }
    
    public void setPlayerID(int id)
    {
        this.playerID = id;
    }
}
