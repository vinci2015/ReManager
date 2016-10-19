package com.njrobot.huangyouqiang.redevicemanager.data.repository;

import com.njrobot.huangyouqiang.redevicemanager.data.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.data.entity.RobotEntity;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqCancelMission;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqRobot;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqSendMission;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStore;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStoreFactory;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.Mission;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.Robot;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author huangyouqiang
 * @date 2016/7/29
 */
public class MissionDataRepository implements MissionRepository {
    private MissionDataStore missionDataStore;

    @Inject
    MissionDataRepository(MissionDataStoreFactory missionDataStoreFactory) {
        missionDataStore = missionDataStoreFactory.createCloudDataStore();
    }

    @Override
    public Observable<Mission> mission(int missionId) {
        return missionDataStore.getMissionEntity(new ReqMissionList(),missionId)
                .map(new Func1<MissionEntity, Mission>() {
                    @Override
                    public Mission call(MissionEntity missionEntity) {
                        return missionEntity.transform();
                    }
                });
    }

    @Override
    public Observable<List<Mission>> missions() {
        return missionDataStore.getMissionEntityList(new ReqMissionList())
                .map(new Func1<List<MissionEntity>, List<Mission>>() {
                    @Override
                    public List<Mission> call(List<MissionEntity> missionEntities) {
                        List<Mission> list = new ArrayList();
                        for(MissionEntity entity : missionEntities){
                            list.add(entity.transform());
                        }
                        return list;
                    }
                });
    }

    @Override
    public Observable<Mission> cancelMission(int missionId) {
        return missionDataStore.cancelMission(new ReqCancelMission(missionId))
                .map(new Func1<MissionEntity, Mission>() {
                    @Override
                    public Mission call(MissionEntity missionEntity) {
                        return missionEntity.transform();
                    }
                });
    }

    @Override
    public Observable<Robot> robot(int robotId) {
        return missionDataStore.getRobotEntity(new ReqRobot(robotId))
                .map(new Func1<RobotEntity, Robot>() {
                    @Override
                    public Robot call(RobotEntity robotEntity) {
                        return robotEntity.transform();
                    }
                });
    }

    @Override
    public Observable<Mission> sendMission(String desSite) {
        return missionDataStore.sendMission(new ReqSendMission(desSite))
                .map(new Func1<MissionEntity, Mission>() {
                    @Override
                    public Mission call(MissionEntity missionEntity) {
                        return missionEntity.transform();
                    }
                });
    }
}
