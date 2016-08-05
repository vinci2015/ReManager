package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.HeaderEntity;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class ReqCancelMission extends RequestBody{
    private int id;

    public ReqCancelMission(int id) {
        super();
        this.id = id;
    }
}
