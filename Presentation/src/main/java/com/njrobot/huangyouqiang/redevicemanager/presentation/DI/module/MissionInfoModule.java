package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.CancelMission;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetMissionDetails;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetMissionList;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetRobotDetails;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.SendMission;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author huangyouqiang
 * @date 2016/8/12
 */
@Module
public class MissionInfoModule {


    public MissionInfoModule() {
    }

    @Provides
    @PerService
    @Named("mission")
    UseCase provideMissionInfo(MissionRepository missionRepository, ThreadExecutor threadExecutor,
                               PostExecutorThread postExecutorThread){
        return new GetMissionDetails(missionRepository,threadExecutor,postExecutorThread);
    }

    @Provides @PerService @Named("missionList")
    UseCase provideMissionInfoList(GetMissionList getMissionList){
        return getMissionList;
    }

    @Provides @PerService @Named("robot")
    UseCase provideRobotInfo(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository){
        return new GetRobotDetails(threadExecutor,postExecutorThread,missionRepository);
    }

    @Provides @PerService
    SendMission provideSendMission(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository){
        return new SendMission(threadExecutor,postExecutorThread,missionRepository);
    }

    @Provides @PerService
    UseCase provideCancelMission(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository){
        return new CancelMission(threadExecutor,postExecutorThread,missionRepository);
    }
}
