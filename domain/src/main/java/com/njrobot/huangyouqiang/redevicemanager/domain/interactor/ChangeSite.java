package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/11.
 */
public class ChangeSite extends UseCase  {
    private WatchRepository watchRepository;
    private String nodeId;
    private String site;

    @Inject
    public ChangeSite(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchRepository) {
        super(threadExecutor, postExecutorThread);
        this.watchRepository = watchRepository;
    }

    public void reSetParams(String nodeId,String str){
        this.nodeId = nodeId;
        this.site = str;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return this.watchRepository.changeSite(nodeId,site);
    }
}
