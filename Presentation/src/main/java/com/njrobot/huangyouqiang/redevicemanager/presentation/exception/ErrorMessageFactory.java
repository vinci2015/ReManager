package com.njrobot.huangyouqiang.redevicemanager.presentation.exception;


import com.njrobot.huangyouqiang.redevicemanager.data.exception.MissionListNullException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.NetworkConnectionException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.ResponseNotCorrectException;

/**
 * Created by huangyouqiang on 2016/8/4.
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
        }
        return  msg;
    }
}
