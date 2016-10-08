package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

/**
 * @author huangyouqiang
 * @date 2016/8/12
 */
class ResSendMission extends ResponseBody {
    @SerializedName("mission_info")
    MissionEntity missionEntity;

    public MissionEntity getMissionEntity() {
        return missionEntity;
    }
}
