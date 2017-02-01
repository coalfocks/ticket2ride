
/**
 * Created by colefox on 1/20/17.
 */
public class DataTransferObject
{
    private String command;
    private Object data;
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

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
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
}
