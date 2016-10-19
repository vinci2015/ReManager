package com.njrobot.huangyouqiang.redevicemanager.domain.repository;


import com.njrobot.huangyouqiang.redevicemanager.domain.model.Mission;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.Robot;

import java.util.List;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/28.
 */
public interface MissionRepository {

    Observable<Mission> mission(int missionId);

    Observable<List<Mission>> missions();

    Observable<Mission> cancelMission(int missionId);

    Observable<Robot> robot(int robotId);

    Observable<Mission> sendMission(String desSite);
}
