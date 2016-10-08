package com.njrobot.huangyouqiang.redevicemanager.presentation.exception;


import com.njrobot.huangyouqiang.redevicemanager.data.exception.MissionListNullException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.NetworkConnectionException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.NodeListNullException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.ResponseNotCorrectException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.SendMessageNotSuccessfulException;

/**
 * @author huangyouqiang
 * @date 2016/8/4
 */
public class ErrorMessageFactory {

    public ErrorMessageFactory() {
    }

    public static String create(Exception exception){
        String msg = "there is a error";
        if(exception instanceof NetworkConnectionException){
            msg = "there is no network connected,check it!!";
        }else if(exception instanceof MissionListNullException){
            msg = "the mission list get from the server is null";
        }else if(exception instanceof ResponseNotCorrectException){
            msg = "the response from server is incorrect";
        }else if(exception instanceof NodeListNullException){
            msg = "get nodes that is empty";
        }else if(exception instanceof SendMessageNotSuccessfulException){
            msg = "check the connection between phone and watch, the message is sent not successful";
        }
        return  msg;
    }
}
