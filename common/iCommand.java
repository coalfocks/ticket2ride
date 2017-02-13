package common;

import common.Response;

import java.io.Serializable;

/**
 * Created by Trevor on 1/19/2017.
 */
public interface iCommand extends Serializable
{
    DataTransferObject execute();
}
