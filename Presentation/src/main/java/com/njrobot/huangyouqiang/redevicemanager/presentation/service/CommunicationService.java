package com.njrobot.huangyouqiang.redevicemanager.presentation.service;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.wearable.DataEventBuffer;
import com.mobvoi.android.wearable.MessageEvent;
import com.mobvoi.android.wearable.Node;
import com.mobvoi.android.wearable.Wearable;
import com.mobvoi.android.wearable.WearableListenerService;
import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.Constant;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.exception.DefaultErrorBundle;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.ChangeSite;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.DefaultSubscriber;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.FindRobot;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetNode;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.ResetView;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.SendMission;
import com.njrobot.huangyouqiang.redevicemanager.presentation.AndroidApplication;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.ApplicationComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.DaggerWatchComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.WatchModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.exception.ErrorMessageFactory;

import javax.inject.Inject;

import rx.Subscriber;

public class CommunicationService extends WearableListenerService implements MobvoiApiClient.ConnectionCallbacks,MobvoiApiClient.OnConnectionFailedListener{
    private static final String TAG = CommunicationService.class.getSimpleName();
    @Inject MobvoiApiClient client;

    //usecase
    @Inject GetNode getNode;
    @Inject ChangeSite changeSite;
    @Inject FindRobot findRobot;
    @Inject ResetView resetView;
    @Inject SendMission sendMission;

    //status
    private boolean isInMission = false;


    private OnServiceMessageCallback callback;
    private Node remoteNode;

    public CommunicationService() {
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i(TAG,"onMessageReceived()");
        if(messageEvent.getPath().equals(Constant.MESSAGE_ROBOT_REQUIREMENT)){
            // // TODO: 2016/8/12 手表端请求一个任务
            if(!isInMission) {
                String site = new String(messageEvent.getData());
                sendMission(site);
            }else {
                Log.e(TAG,"is in mission, can not sent a  brand new mission");
            }
        }else if(messageEvent.getPath().equals(Constant.MESSAGE_CANCEL_MISSION)){
            //// TODO: 2016/8/12  手表端请求结束当前任务
        }
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.i(TAG,"onDataChanged()");
    }

    @Override
    public void onPeerConnected(final Node node) {
        Log.i(TAG,"onPeerConnected()");
        new Thread(new Runnable() {
            @Override
            public void run() {
                callback.onFindWatch(WatchModel.transformFromNode(node));
            }
        }).start();

    }

    @Override
    public void onPeerDisconnected(Node node) {
        Log.i(TAG,"onPeerDisconnected()");
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG,"onConnected()");
        Looper looper = Looper.myLooper();
        Log.i(TAG," "+(looper == Looper.getMainLooper()));
        Wearable.MessageApi.addListener(client,this);
        Wearable.DataApi.addListener(client,this);
        Wearable.NodeApi.addListener(client,this);
        this.callback.sendMessage("connected watch");
        getNode();
    }

    public void getNode(){
        getNode.execute(new SubGetNode());
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
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind()");
        callback = null;
        return super.onUnbind(intent);
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
        initInjection();
        this.client.registerConnectionCallbacks(this);
        this.client.registerConnectionFailedListener(this);
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
        callback = null;
    }

    private void initInjection(){
        ApplicationComponent applicationComponent = ((AndroidApplication)getApplication()).getApplicationComponent();
        DaggerWatchComponent.builder().applicationComponent(applicationComponent).watchModule(new WatchModule()).build().inject(this);
    }
    public void sendMission(String site){
        sendMission.resetParams(site);
        sendMission.execute(new Subscriber<MissionEntity>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                String errorMsg = ErrorMessageFactory.create(new DefaultErrorBundle((Exception) e).getException());
                Log.i(TAG,"onError()"+errorMsg+e.getMessage());
                e.printStackTrace();
                // TODO: 2016/8/12 错误处理 
            }

            @Override
            public void onNext(MissionEntity missionEntity) {
                Log.i(TAG,"onNext()");
                // TODO: 2016/8/12 start a polling to check if the mission is running
            }
        });
    }
    public void changeSite(String nodeId,String site){
        //initInjection(nodeId,site);
        changeSite.reSetParams(nodeId,site);
        changeSite.execute(new DefaultSubscriber<Boolean>(){
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"onError()");
            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.i(TAG,"onNext()");
                Log.i(TAG,"result "+aBoolean);
            }
        });
    }
    public void findRobot(String nodeId,String distance){
        findRobot.resetParams(nodeId,distance);
        findRobot.execute(new DefaultSubscriber<Boolean>(){
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                String errorMsg = ErrorMessageFactory.create(new DefaultErrorBundle((Exception) e).getException());
                Log.i(TAG,"onError()"+errorMsg);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.i(TAG,"onNext()");
                Log.i(TAG,"result "+aBoolean);
            }
        });
    }
    public void resetView(String nodeId){
        resetView.resetParams(nodeId);
        resetView.execute(new DefaultSubscriber<Boolean>(){
            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                String errorMsg = ErrorMessageFactory.create(new DefaultErrorBundle((Exception) e).getException());
                Log.i(TAG,"onError()"+errorMsg);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.i(TAG,"onNext()");
                Log.i(TAG,"result "+aBoolean);
            }
        });
    }
    /**
     * 设置service的事件回调
     * @param callback
     */
    public void setServiceCallback(OnServiceMessageCallback callback){
        this.callback  = callback;
    }
    public void ResetCallback(){
        this.callback = null;
    }

    private class SubGetNode extends DefaultSubscriber<Node>{
        @Override
        public void onCompleted() {
            Log.i(TAG,"onCompleted()");
        }

        @Override
        public void onError(Throwable e) {
            String errorMsg = ErrorMessageFactory.create(new DefaultErrorBundle((Exception) e).getException());
            Log.i(TAG,"onError()  : "+errorMsg);
        }

        @Override
        public void onNext(Node o) {
            Log.i(TAG,"onNext()");
            remoteNode  = o;
            Log.i(TAG,o.getDisplayName());
            if(callback != null) {
                callback.onFindWatch(WatchModel.transformFromNode(o));
            }
        }
    }
}
