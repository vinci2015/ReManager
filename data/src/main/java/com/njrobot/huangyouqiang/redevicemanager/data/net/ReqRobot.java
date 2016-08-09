package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.ParamsBeanEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class ReqRobot extends RequestBody {
    @SerializedName("code_lst")
    private List<ParamsBeanEntity> robotIds;

    public ReqRobot(int robotId) {
        super();
        this.robotIds = new ArrayList<>();
        ParamsBeanEntity beanEntity = new ParamsBeanEntity();
        beanEntity.setCode(robotId);
        this.robotIds.add(beanEntity);

    }
}
