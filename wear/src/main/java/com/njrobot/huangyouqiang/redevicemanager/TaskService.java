package com.njrobot.huangyouqiang.redevicemanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.common.data.FreezableUtils;
import com.mobvoi.android.wearable.DataEvent;
import com.mobvoi.android.wearable.DataEventBuffer;
import com.mobvoi.android.wearable.DataItem;
import com.mobvoi.android.wearable.DataMapItem;
import com.mobvoi.android.wearable.MessageApi;
import com.mobvoi.android.wearable.MessageEvent;
import com.mobvoi.android.wearable.Node;
import com.mobvoi.android.wearable.Wearable;
import com.mobvoi.android.wearable.WearableListenerService;

import java.util.List;

public class TaskService extends WearableListenerService implements MobvoiApiClient.ConnectionCallbacks, MobvoiApiClient.OnConnectionFailedListener{
    private static final String TAG = TaskService.class.getSimpleName();
    private MobvoiApiClient mobvoiApiClient;
    private ThreadExecutor threadExecutor;
    private MessageLister messageLister;
    private Node phone;
    private String site = "";
    private int mDistance = 0;
    private boolean isConnected = false;
    private boolean isInMission = false;
    private boolean isFindRobot = false;

    public TaskService() {
    }



    public interface MessageLister{
        void onGetSite(String site);
        void onMessageShow(String msg);
        void onUpdateDistance(int distance);
        //重置视图
        void resetView(String msg);
    }
    public class MyBinder extends Binder{
        public TaskService getService(){
            return  TaskService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind()");
       return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        //messageLister = null;
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate()");
        threadExecutor = new JobExecutor();
        this.mobvoiApiClient = new MobvoiApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        this.mobvoiApiClient.connect();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy()");
        Wearable.MessageApi.removeListener(this.mobvoiApiClient,this);
        Wearable.DataApi.removeListener(this.mobvoiApiClient,this);
        Wearable.NodeApi.removeListener(this.mobvoiApiClient,this);
        this.mobvoiApiClient.disconnect();
        super.onDestroy();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG,"onConnected()");
        isConnected = true;
        Wearable.MessageApi.addListener(this.mobvoiApiClient,this);
        Wearable.NodeApi.addListener(this.mobvoiApiClient,this);
        Wearable.DataApi.addListener(this.mobvoiApiClient,this);
        //获取手表端存储的站点信息
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Node> nodes = Wearable.NodeApi.getConnectedNodes(mobvoiApiClient).await().getNodes();
                if(!nodes.isEmpty()) {
                    phone = nodes.get(0);
                    DataItem dataItem = Wearable.DataApi.getDataItem(mobvoiApiClient,
                            Uri.parse("wear://" + phone.getId() + Constant.SITE_CHANGED))
                            .await().getDataItem();
                    if(dataItem != null) {
                        DataMapItem dataMapItem = DataMapItem.fromDataItem(dataItem);
                        site = dataMapItem.getDataMap().getString(Constant.WATCH_SITE);
                        Log.i(TAG, "phone ;" + phone.getDisplayName() + " site:" + site);
                        if (messageLister != null) {
                            messageLister.onGetSite(site);
                        }
                    }
                }else{
                    messageLister.onMessageShow("与手机的连接已断开");
                }
            }
        });
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG,"onConnectionFailed()");
        isConnected = false;
    }
    @Override
    public void onPeerDisconnected(Node node) {
        super.onPeerDisconnected(node);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.i(TAG,"on data changed");
        final Handler mhandler = new Handler(Looper.getMainLooper());
        mhandler.post(new Runnable() {
            @Override
            public void run() {
                messageLister.onMessageShow("onDataChanged,");
            }
        });
        List<DataEvent> events = FreezableUtils.freezeIterable(dataEventBuffer);
        dataEventBuffer.close();
        for(DataEvent event : events){
            if(event.getType() == DataEvent.TYPE_CHANGED){
                Log.i(TAG,"type_changed");
                String path = event.getDataItem().getUri().getPath();
                if(path.equals(Constant.SITE_CHANGED)){
                    //site_change
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                    String watchId = dataMapItem.getDataMap().getString(Constant.WATCH_ID);
                    String selfId = Wearable.NodeApi.getLocalNode(mobvoiApiClient).await().getNode().getId();
                    if(!watchId.equals(selfId)){
                        Log.e(TAG,"watch id is not equal");
                        return;
                    }
                    site = dataMapItem.getDataMap().getString(Constant.WATCH_SITE);
                    if(messageLister != null) {
                        messageLister.onGetSite(site);
                    }
                }
            }else if(event.getType() == DataEvent.TYPE_DELETED){

            }else {

            }
        }
    }

    @Override
    public void onPeerConnected(Node node) {
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.i(TAG,"on message received, ");
        if(messageEvent.getPath().equals(Constant.COMMAND_FIND_ROBOT)){
            isFindRobot = true;
            mDistance = Integer.parseInt(new String(messageEvent.getData()));
            Log.i(TAG,"on message received, "+mDistance);
            if(mDistance == 0){
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long [] pattern = {100,400,100,400};
                vibrator.vibrate(pattern,-1);
                isInMission = false;
                isFindRobot = false;
               // stopSelf();
            }
           messageLister.onUpdateDistance(mDistance);
        }else if(messageEvent.getPath().equals(Constant.MESSAGE_SEND_MISSION_FAILED)){
            Log.i(TAG,"receive send mission failed");
            isFindRobot = false;
            messageLister.resetView("发送任务失败\n" +
                    "请检查站点是否错误！");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    public void sendMission(){
        Log.i(TAG,"sendMission()");
        if(isConnected) {
            threadExecutor.execute(new SendRobotRequirement(
                    mobvoiApiClient,
                    phone.getId(),
                    site.getBytes(),
                    new ResultCallBack() {
                        @Override
                        public void OnResult(boolean isSuccess, String info) {
                            Log.i(TAG, "send mission is Success :" + isSuccess);
                            if (isSuccess) {
                                isInMission = true;
                            }
                        }
                    }));
        }else{
            messageLister.resetView("请先连接手机");
        }
    }

    /**
     * 向手机端发送取消任务消息
     */
    public void cancelMission(){
        Wearable.MessageApi.sendMessage(mobvoiApiClient,phone.getId(),Constant.MESSAGE_CANCEL_MISSION,new byte[0]).setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
            @Override
            public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                if(sendMessageResult.getStatus().isSuccess()){
                    isInMission = false;
                    isFindRobot = false;
                    mDistance = 0;
                }else{
                    Log.i(TAG,"send cancel not successful");
                    messageLister.onMessageShow("send cancel not successful");
                }
            }
        });
    }

    public int getmDistance() {
        return mDistance;
    }

    public String getSite() {
        return site;
    }

    public boolean isFindRobot() {
        return isFindRobot;
    }

    public boolean isInMission() {
        return isInMission;
    }

    public void setMessageLister(MessageLister lister){
        this.messageLister = lister;
    }
}
