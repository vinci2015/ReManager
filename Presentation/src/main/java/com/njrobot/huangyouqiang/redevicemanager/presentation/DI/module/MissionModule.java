package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetMissionDetails;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
@Module
public class MissionModule {

    @Provides @PerActivity
    UseCase provideMissionInfo(GetMissionDetails getMissionDetails){
        return getMissionDetails;
    }
}
