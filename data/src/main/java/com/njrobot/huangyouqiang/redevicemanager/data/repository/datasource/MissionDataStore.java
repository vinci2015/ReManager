package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.data.net.ReqMissionList;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/29.
 */
public interface MissionDataStore {
    Observable<MissionEntity> getMissionEntity(ReqMissionList reqMissionList);
}
