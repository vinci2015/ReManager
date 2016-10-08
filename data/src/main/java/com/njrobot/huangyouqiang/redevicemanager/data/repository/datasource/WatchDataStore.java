package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import com.mobvoi.android.wearable.Node;

import rx.Observable;

/**
 * @author huangyouqiang
 * @date 2016/8/10
 */
public interface WatchDataStore {
    Observable<Node> getNode();
    Observable<Boolean> changeSite(String nodeId,String site);
    Observable<Boolean> findRobot(String nodeId,String distance);
    Observable<Boolean> resetView(String nodeId);
}
