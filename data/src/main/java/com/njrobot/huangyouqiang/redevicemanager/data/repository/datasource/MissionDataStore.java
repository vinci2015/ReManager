package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqCancelMission;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqRobot;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.RobotEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/29.
 */
public interface MissionDataStore {
    Observable<MissionEntity> getMissionEntity(ReqMissionList reqMissionList,int missionId);
    Observable<List<MissionEntity>> getMissionEntityList(ReqMissionList reqMissionList);
    Observable<MissionEntity> cancelMission(ReqCancelMission reqCancelMission);
    Observable<RobotEntity> getRobotEntity(ReqRobot reqRobot);
}
