package com.njrobot.huangyouqiang.redevicemanager.data.net;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

/**
 * @author huangyouqiang
 * @date 2016/8/12
 */
public class ReqSendMission extends RequestBody {
    private MissionEntity mission_obj;

    public ReqSendMission(String destinationSite) {
        super();
        this.mission_obj = new MissionEntity(destinationSite,"time","1.0");
    }

}
