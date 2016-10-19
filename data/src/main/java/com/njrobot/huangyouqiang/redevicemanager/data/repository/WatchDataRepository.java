package com.njrobot.huangyouqiang.redevicemanager.data.repository;

import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.wearable.Node;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.LocalWatchDataStore;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.WatchDataStore;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author huangyouqiang
 * @date 2016/8/10
 */
public class WatchDataRepository implements WatchRepository {
    private WatchDataStore watchDataStore;

    @Inject
    WatchDataRepository(MobvoiApiClient mobvoiApiClient) {
        this.watchDataStore = new LocalWatchDataStore(mobvoiApiClient);
    }

    @Override
    public Observable<WatchModel> getWatch() {
        return  this.watchDataStore.getNode()
                .map(new Func1<Node, WatchModel>() {
                    @Override
                    public WatchModel call(Node node) {
                        WatchModel watchModel = new WatchModel(node.getId());
                        watchModel.setName(node.getDisplayName());
                        return watchModel;
                    }
                });
    }

    @Override
    public Observable<Boolean> changeSite(String nodeId,String site) {
        return this.watchDataStore.changeSite(nodeId,site);
    }

    @Override
    public Observable<Boolean> findRobot(String nodeId, String distance) {
        return watchDataStore.findRobot(nodeId,distance);
    }

    @Override
    public Observable resetView(String nodeId) {
        return watchDataStore.resetView(nodeId);
    }
}
