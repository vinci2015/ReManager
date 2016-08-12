package com.njrobot.huangyouqiang.redevicemanager.domain.repository;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
public interface WatchRepository {
    Observable getNode();
    Observable changeSite(String nodeId,String site);
    Observable findRobot(String nodeId,String distance);
    Observable resetView(String nodeId);
}
