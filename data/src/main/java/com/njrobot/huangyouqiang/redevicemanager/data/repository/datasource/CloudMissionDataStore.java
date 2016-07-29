package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;
import com.njrobot.huangyouqiang.redevicemanager.data.net.RobotClient;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/29.
 */
public class CloudMissionDataStore implements MissionDataStore {
    private RobotClient robotClient;

    public CloudMissionDataStore(RobotClient robotClient){
        this.robotClient = robotClient;
    }

    @Override
    public Observable<MissionEntity> getMissionEntity(ReqMissionList reqMissionList) {
        return this.robotClient.getMissionList(reqMissionList);
    }
}
