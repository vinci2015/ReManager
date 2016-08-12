package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import android.util.Log;

import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.wearable.DataApi;
import com.mobvoi.android.wearable.MessageApi;
import com.mobvoi.android.wearable.Node;
import com.mobvoi.android.wearable.PutDataMapRequest;
import com.mobvoi.android.wearable.PutDataRequest;
import com.mobvoi.android.wearable.Wearable;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.NodeListNullException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.SendMessageNotSuccessfulException;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.Constant;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
public class LocalWatchDataStore implements WatchDataStore {
    private MobvoiApiClient client;

    public LocalWatchDataStore(MobvoiApiClient client) {
        this.client = client;
    }

    @Override
    public Observable<Node> getNode() {
      return   Observable.create(new Observable.OnSubscribe<Node>() {
            @Override
            public void call(Subscriber<? super Node> subscriber) {
                List<Node> nodes = Wearable.NodeApi.getConnectedNodes(client).await().getNodes();
                if(nodes.isEmpty()){
                    subscriber.onError(new NodeListNullException("get node list is empty"));
                }else{
                    subscriber.onNext(nodes.get(0));
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public Observable<Boolean> changeSite(final String nodeId, final String site) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                PutDataMapRequest putDataMapRequest = PutDataMapRequest.create(Constant.SITE_CHANGED);
                putDataMapRequest.getDataMap().putString(Constant.WATCH_ID,nodeId);
                putDataMapRequest.getDataMap().putString(Constant.WATCH_SITE,site);
                PutDataRequest putDataRequest = putDataMapRequest.asPutDataRequest();
                if(!client.isConnected()){
                    subscriber.onError(new UnknownError("client no connected"));
                }
                Wearable.DataApi.putDataItem(client,putDataRequest).setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                    @Override
                    public void onResult(DataApi.DataItemResult dataItemResult) {
                        subscriber.onNext(dataItemResult.getStatus().isSuccess());
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    @Override
    public Observable<Boolean> findRobot(final String nodeId, final String distance) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                Wearable.MessageApi.sendMessage(client,nodeId, Constant.COMMAND_FIND_ROBOT,distance.getBytes())
                        .setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
                            @Override
                            public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                                if(sendMessageResult.getStatus().isSuccess()) {
                                    subscriber.onNext(true);
                                    subscriber.onCompleted();
                                }else {
                                    subscriber.onError(new SendMessageNotSuccessfulException());
                                }
                            }
                        });
            }
        });
    }

    @Override
    public Observable<Boolean> resetView(final String nodeId) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                Wearable.MessageApi.sendMessage(client,nodeId, Constant.MESSAGE_SEND_MISSION_FAILED,new byte[0])
                        .setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
                            @Override
                            public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                               if(sendMessageResult.getStatus().isSuccess()){
                                   subscriber.onNext(true);
                                   subscriber.onCompleted();
                               }else {
                                   subscriber.onError(new SendMessageNotSuccessfulException());
                               }
                            }
                        });
            }
        });
    }
}
