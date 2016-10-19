package com.njrobot.huangyouqiang.redevicemanager.data.net;


import com.njrobot.huangyouqiang.redevicemanager.data.entity.MissionEntity;

import java.util.List;

/**
 * @author huangyouqiang
 * @date 2016/6/29
 */
class ResMissionList extends ResponseBody{
    List<MissionEntity> mission_list;

    List<MissionEntity> getMission_list() {
        return mission_list;
    }
}
