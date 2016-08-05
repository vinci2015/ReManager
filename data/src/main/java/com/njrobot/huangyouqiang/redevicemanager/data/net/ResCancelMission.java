package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class ResCancelMission extends ResponseBody {
    private MissionEntity mission_info;

    public MissionEntity getMission_info() {
        return mission_info;
    }
}
