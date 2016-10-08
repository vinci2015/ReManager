package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.ChangeSite;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.FindRobot;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetNode;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.ResetView;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * @author huangyouqiang
 * @date 2016/8/10
 */
@Module
public class WatchModule {

    public WatchModule() {
    }


    @Provides
    @PerService
    UseCase provideNode(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository){
        return  new GetNode(threadExecutor, postExecutorThread,watchDataRepository);
    }

    @Provides
    @PerService
    ChangeSite provideChangeSite(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository){
        return  new ChangeSite(threadExecutor,postExecutorThread,watchDataRepository);
    }

    @Provides
    @PerService
    FindRobot provideFindRobot(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository){
        return new FindRobot(threadExecutor,postExecutorThread,watchDataRepository);
    }

    @Provides
    @PerService
    ResetView provideResetView(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository){
        return new ResetView(threadExecutor,postExecutorThread,watchDataRepository);
    }
}
