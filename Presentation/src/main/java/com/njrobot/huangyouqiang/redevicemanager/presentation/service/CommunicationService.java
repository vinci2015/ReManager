package com.njrobot.huangyouqiang.redevicemanager.presentation.service;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.wearable.DataEventBuffer;
import com.mobvoi.android.wearable.MessageEvent;
import com.mobvoi.android.wearable.Node;
import com.mobvoi.android.wearable.Wearable;
import com.mobvoi.android.wearable.WearableListenerService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.model.WatchModel;

public class CommunicationService extends WearableListenerService implements MobvoiApiClient.ConnectionCallbacks,MobvoiApiClient.OnConnectionFailedListener{
    private static final String TAG = CommunicationService.class.getSimpleName();
    private MobvoiApiClient client;
    private OnServiceMessageCallback callback;
    public CommunicationService() {
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i(TAG,"onMessageReceived()");
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.i(TAG,"onDataChanged()");
    }

    @Override
    public void onPeerConnected(Node node) {
        Log.i(TAG,"onPeerConnected()");
    }

    @Override
    public void onPeerDisconnected(Node node) {
        Log.i(TAG,"onPeerDisconnected()");
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG,"onConnected()");
        Wearable.MessageApi.addListener(client,this);
        Wearable.DataApi.addListener(client,this);
        Wearable.NodeApi.addListener(client,this);
        this.callback.sendMessage("connected watch");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG,"onConnectionSuspended()");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG,"onConnectionFailed()");
    }

    public interface OnServiceMessageCallback{
        /**
         * 检测到服务器端响应任务并开始running的回调
         * @param robotId 执行任务的机器人ID
         */
        void onStartMission(int robotId);

        /**
         * 发现并连接上一个手表的回调
         * @param watchModel
         */
        void onFindWatch(WatchModel watchModel);
        void sendMessage(String s);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }
    public class ServiceBinder extends Binder{
        public CommunicationService getService(){
            return CommunicationService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate()");
        this.client = new MobvoiApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        this.client.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroyed()");
        Wearable.MessageApi.removeListener(client,this);
        Wearable.DataApi.removeListener(client,this);
        Wearable.NodeApi.removeListener(client,this);
        client.disconnect();

    }

    /**
     * 设置service的事件回调
     * @param callback
     */
    public void setServiceCallback(OnServiceMessageCallback callback){
        this.callback  = callback;
    }
}
