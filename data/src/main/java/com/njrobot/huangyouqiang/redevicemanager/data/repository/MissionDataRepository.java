package com.njrobot.huangyouqiang.redevicemanager.data.repository;

import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStore;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStoreFactory;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/29.
 */
public class MissionDataRepository implements MissionRepository {
    private MissionDataStoreFactory missionDataStoreFactory;

    public MissionDataRepository(MissionDataStoreFactory missionDataStoreFactory) {
        this.missionDataStoreFactory = missionDataStoreFactory;
    }

    @Override
    public Observable<MissionEntity> mission() {
        MissionDataStore store = missionDataStoreFactory.createCloudDataStore();
        return store.getMissionEntity(new ReqMissionList());
    }
}
