package com.njrobot.huangyouqiang.redevicemanager.domain.repository;


import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.RobotEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/28.
 */
public interface MissionRepository {

    Observable<MissionEntity> mission(int missionId);

    Observable<List<MissionEntity>> missions();

    Observable<MissionEntity> cancelMission(int missionId);

    Observable<RobotEntity> robot(int robotId);

    Observable<MissionEntity> sendMission(String desSite);
}
