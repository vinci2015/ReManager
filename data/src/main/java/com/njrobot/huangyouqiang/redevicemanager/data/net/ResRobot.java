package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.RobotEntity;

import java.util.List;


/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class ResRobot extends ResponseBody {
    @SerializedName("robot_info_lst")
    private List<RobotEntity> robotEntities;

    public List<RobotEntity> getRobotEntities() {
        return robotEntities;
    }
}
