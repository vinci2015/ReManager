package com.njrobot.huangyouqiang.redevicemanager.data.net;


import com.njrobot.huangyouqiang.redevicemanager.domain.entity.HeaderEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/29.
 */
public class ResMissionList {
    private HeaderEntity header;
    private List<MissionEntity> mission_list;

    public HeaderEntity getHeader() {
        return header;
    }

    public List<MissionEntity> getMission_list() {
        return mission_list;
    }
}
