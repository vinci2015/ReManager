package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.ParamsBeanEntity;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class ReqRobot extends RequestBody {
    @SerializedName("code_lst")
    private ParamsBeanEntity robotIds;

    public ReqRobot(int robotId) {
        super();
        this.robotIds = new ParamsBeanEntity();
        this.robotIds.setCode(robotId);
    }
}
