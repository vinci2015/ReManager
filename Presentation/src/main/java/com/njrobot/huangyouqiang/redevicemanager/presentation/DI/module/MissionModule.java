package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import android.support.annotation.Nullable;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetMissionDetails;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetMissionList;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetRobotDetails;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
@Module
public class MissionModule {
    private int missionId = -1;
    private int robotId = -1;

    public MissionModule() {
    }

    public MissionModule( int missionId, int robotId) {
        this.missionId = missionId;
        this.robotId = robotId;
    }

    @Provides @PerActivity @Named("mission")
    UseCase provideMissionInfo(MissionRepository missionRepository, ThreadExecutor threadExecutor,
                               PostExecutorThread postExecutorThread){
        return new GetMissionDetails(missionRepository,threadExecutor,postExecutorThread,this.missionId);
    }

    @Provides @PerActivity @Named("missionList")
    UseCase provideMissionInfoList(GetMissionList getMissionList){
        return getMissionList;
    }

    @Provides @PerActivity @Named("robot")
    UseCase provideRobotInfo(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository){
        return new GetRobotDetails(threadExecutor,postExecutorThread,missionRepository,this.robotId);
    }
}
