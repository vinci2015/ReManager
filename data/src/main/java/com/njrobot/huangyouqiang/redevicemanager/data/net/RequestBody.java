package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.HeaderEntity;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class RequestBody {
    HeaderEntity header;

    public RequestBody() {
        this.header = new HeaderEntity();
        this.header.setUserId(2);
    }
}
