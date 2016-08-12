package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/12.
 */
public class ResetView extends UseCase{
    private WatchRepository watchRepository;
    private String nodeId;

    @Inject
    public ResetView(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchRepository) {
        super(threadExecutor, postExecutorThread);
        this.watchRepository = watchRepository;
    }

    public void resetParams(String nodeId){
        this.nodeId = nodeId;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return this.watchRepository.resetView(this.nodeId);
    }
}
