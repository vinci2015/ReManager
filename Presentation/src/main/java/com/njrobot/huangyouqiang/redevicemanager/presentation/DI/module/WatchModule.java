package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.ChangeSite;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetNode;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
@Module
public class WatchModule {

    private String nodeId = "";
    private String site = "";
    private int index = 0;

    public WatchModule() {
    }

    public WatchModule(String nodeId, String site, int index) {
        this.nodeId = nodeId;
        this.site = site;
        this.index = index;
    }

    @Provides
    @PerService
    @Named("node")
    UseCase provideNode(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository, int index){
        return  new GetNode(threadExecutor, postExecutorThread,watchDataRepository,index);
    }
    @Provides
    @PerService
    int provideInt(){
        return this.index;
    }

    @Provides
    @PerService
    @Named("changeSite")
    UseCase provideChangeSite(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository){
        return  new ChangeSite(threadExecutor,postExecutorThread,watchDataRepository,this.nodeId,this.site);
    }
}
