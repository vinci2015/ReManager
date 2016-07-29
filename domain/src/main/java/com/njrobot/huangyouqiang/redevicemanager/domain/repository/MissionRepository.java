package com.njrobot.huangyouqiang.redevicemanager.domain.repository;


import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/28.
 */
public interface MissionRepository {

    Observable<MissionEntity> mission();
}
