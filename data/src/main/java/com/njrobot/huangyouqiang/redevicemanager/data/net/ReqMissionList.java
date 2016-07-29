package com.njrobot.huangyouqiang.redevicemanager.data.net;


import com.njrobot.huangyouqiang.redevicemanager.domain.entity.HeaderEntity;

/**
 * Created by huangyouqiang on 2016/6/29.
 */
public class ReqMissionList {
    private HeaderEntity header;

    public ReqMissionList() {
        this.header = new HeaderEntity();
        this.header.setUserId(2);
    }
}
