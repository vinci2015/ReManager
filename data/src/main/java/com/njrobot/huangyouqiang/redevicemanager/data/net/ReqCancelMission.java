package com.njrobot.huangyouqiang.redevicemanager.data.net;


/**
 * @author huangyouqiang
 * @date 2016/8/4
 */
public class ReqCancelMission extends RequestBody{
    int id;

    public ReqCancelMission(int id) {
        super();
        this.id = id;
    }
}
