package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.data.entity.RobotEntity;

import java.util.List;


/**
 * @author huangyouqiang
 * @date 2016/8/4
 */
class ResRobot extends ResponseBody {
    @SerializedName("robot_info_lst")
    List<RobotEntity> robotEntities;

    List<RobotEntity> getRobotEntities() {
        return robotEntities;
    }
}
