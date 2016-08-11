package com.njrobot.huangyouqiang.redevicemanager.data.exception;

/**
 * Created by huangyouqiang on 2016/8/11.
 */
public class NodeListNullException extends Exception {
    public NodeListNullException(String detailMessage) {
        super(detailMessage);
    }

    public NodeListNullException() {
    }

    public NodeListNullException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NodeListNullException(Throwable throwable) {
        super(throwable);
    }
}
