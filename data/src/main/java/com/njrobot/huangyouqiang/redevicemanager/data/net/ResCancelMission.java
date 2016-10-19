package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.data.entity.MissionEntity;

/**
 * @author huangyouqiang
 * @date 2016/8/4
 */
class ResCancelMission extends ResponseBody {
    MissionEntity mission_info;

    MissionEntity getMission_info() {
        return mission_info;
    }
}
