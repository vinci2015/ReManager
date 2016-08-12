package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

/**
 * Created by huangyouqiang on 2016/8/12.
 */
public class ResSendMission extends ResponseBody {
    @SerializedName("mission_info")
    private MissionEntity missionEntity;

    public MissionEntity getMissionEntity() {
        return missionEntity;
    }
}
