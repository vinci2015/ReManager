package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.data.entity.HeaderEntity;

/**
 * @author huangyouqiang
 * @date 2016/8/4
 */
class RequestBody {
    private HeaderEntity header;

    RequestBody() {
        this.header = new HeaderEntity();
        this.header.setUserId(2);
    }
}
