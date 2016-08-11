package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import android.content.Context;

import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.wearable.Wearable;
import com.njrobot.huangyouqiang.redevicemanager.data.executor.JobExecutor;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.MissionDataRepository;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.WatchDataRepository;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;
import com.njrobot.huangyouqiang.redevicemanager.presentation.AndroidApplication;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication androidApplication;

    public ApplicationModule(AndroidApplication androidApplication) {
        this.androidApplication = androidApplication;
    }

    @Provides @Singleton
    Context provideContext(){
        return this.androidApplication;
    }
    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }
    @Provides @Singleton
    PostExecutorThread providePostExecutorThread(UIThread uiThread){
        return uiThread;
    }
    @Provides @Singleton
    MissionRepository provideMissionRepository(MissionDataRepository missionRepository){
        return missionRepository;
    }
    @Provides
    @Singleton
    MobvoiApiClient provideClient(Context context){
        return new MobvoiApiClient.Builder(context)
                .addApi(Wearable.API)
                .build();
    }
    @Provides
    @Singleton
    WatchRepository provideWatchDataRepository(WatchDataRepository watchDataRepository){
        return watchDataRepository;
    }
}
