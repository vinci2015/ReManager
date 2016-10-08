package com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource;

import android.content.Context;

import com.njrobot.huangyouqiang.redevicemanager.data.net.RobotClient;

import javax.inject.Inject;

/**
 * @author huangyouqiang
 * @date 2016/7/29
 */
public class MissionDataStoreFactory {
    private Context context;

    @Inject
    MissionDataStoreFactory(Context context){
        if(context == null){
            throw new IllegalArgumentException("argument is illegal!");
        }
        this.context  = context.getApplicationContext();
    }

    public MissionDataStore create(){
        //there is no need to create local cache
        return createCloudDataStore();
    }
    public MissionDataStore createCloudDataStore(){
        RobotClient robotClient = new RobotClient().build(context);
        return  new CloudMissionDataStore(robotClient);
    }
}
