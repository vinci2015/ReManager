package com.njrobot.huangyouqiang.redevicemanager.data.repository;

import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.wearable.Node;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.LocalWatchDataStore;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.WatchDataStore;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
public class WatchDataRepository implements WatchRepository {
    private WatchDataStore watchDataStore;

    @Inject
    public WatchDataRepository(MobvoiApiClient mobvoiApiClient) {
        this.watchDataStore = new LocalWatchDataStore(mobvoiApiClient);
    }

    @Override
    public Observable<Node> getNode(int index) {
        return  this.watchDataStore.getNode(index);
    }

    @Override
    public Observable changeSite(String nodeId,String site) {
        return null;
    }
}
