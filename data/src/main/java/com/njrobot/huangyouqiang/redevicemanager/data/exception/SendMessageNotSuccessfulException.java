package com.njrobot.huangyouqiang.redevicemanager.data.exception;

/**
 * Created by huangyouqiang on 2016/8/12.
 */
public class SendMessageNotSuccessfulException extends Exception {
    public SendMessageNotSuccessfulException() {
    }

    public SendMessageNotSuccessfulException(String detailMessage) {
        super(detailMessage);
    }

    public SendMessageNotSuccessfulException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public SendMessageNotSuccessfulException(Throwable throwable) {
        super(throwable);
    }
}
