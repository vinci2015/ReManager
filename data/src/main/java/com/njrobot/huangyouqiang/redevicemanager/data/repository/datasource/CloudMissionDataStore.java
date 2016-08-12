package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqCancelMission;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqRobot;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqSendMission;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;
import com.njrobot.huangyouqiang.redevicemanager.data.net.RobotClient;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.RobotEntity;

import java.util.List;

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
    public Observable<MissionEntity> getMissionEntity(ReqMissionList reqMissionList,int missionId) {
        return this.robotClient.getMission(reqMissionList,missionId);
    }

    @Override
    public Observable<List<MissionEntity>> getMissionEntityList(ReqMissionList reqMissionList) {
        return this.robotClient.getMissionList(reqMissionList);
    }

    @Override
    public Observable<MissionEntity> cancelMission(ReqCancelMission reqCancelMission) {
        return this.robotClient.cancelMission(reqCancelMission);
    }

    @Override
    public Observable<RobotEntity> getRobotEntity(ReqRobot reqRobot) {
        return this.robotClient.getRobot(reqRobot);
    }

    @Override
    public Observable<MissionEntity> sendMission(ReqSendMission reqSendMission) {
        return this.robotClient.sendMission(reqSendMission);
    }
}
