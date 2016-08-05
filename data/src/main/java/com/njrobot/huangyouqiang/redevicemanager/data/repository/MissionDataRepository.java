package com.njrobot.huangyouqiang.redevicemanager.data.repository;

import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqCancelMission;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqRobot;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStore;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStoreFactory;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.RobotEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/29.
 */
public class MissionDataRepository implements MissionRepository {
    private MissionDataStoreFactory missionDataStoreFactory;
    private MissionDataStore missionDataStore;

    @Inject
    public MissionDataRepository(MissionDataStoreFactory missionDataStoreFactory) {
        this.missionDataStoreFactory = missionDataStoreFactory;
        missionDataStore = missionDataStoreFactory.createCloudDataStore();
    }

    @Override
    public Observable<MissionEntity> mission(int missionId) {
        return missionDataStore.getMissionEntity(new ReqMissionList(),missionId);
    }

    @Override
    public Observable<List<MissionEntity>> missions() {
        return missionDataStore.getMissionEntityList(new ReqMissionList());
    }

    @Override
    public Observable<MissionEntity> cancelMission(int missionId) {
        return missionDataStore.cancelMission(new ReqCancelMission(missionId));
    }

    @Override
    public Observable<RobotEntity> robot(int robotId) {
        return missionDataStore.getRobotEntity(new ReqRobot(robotId));
    }
}
