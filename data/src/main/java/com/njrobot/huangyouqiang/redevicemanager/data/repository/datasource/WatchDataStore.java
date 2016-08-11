package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import com.mobvoi.android.wearable.Node;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
public interface WatchDataStore {
    Observable<Node> getNode(int index);
    Observable<Boolean> changeSite(String nodeId,String site);
}
