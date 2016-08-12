package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

/**
 * Created by huangyouqiang on 2016/8/12.
 */
public class ReqSendMission extends RequestBody {
    private MissionEntity mission_obj;

    public ReqSendMission(String destinationSite) {
        super();
        this.mission_obj = new MissionEntity(destinationSite);
    }

}
