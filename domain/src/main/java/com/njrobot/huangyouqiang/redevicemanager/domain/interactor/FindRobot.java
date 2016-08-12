package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/12.
 */
public class FindRobot extends UseCase {
    private WatchRepository watchRepository;
    private String nodeId;
    private String distance;

    @Inject
    public FindRobot(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchRepository) {
        super(threadExecutor, postExecutorThread);
        this.watchRepository = watchRepository;
    }

    public void resetParams(String nodeId,String distance){
        this.nodeId = nodeId;
        this.distance = distance;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return this.watchRepository.findRobot(this.nodeId,this.distance);
    }
}
