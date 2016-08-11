package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.WatchRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
public class GetNode extends UseCase {
    private WatchRepository watchDataRepository;
    private final int index;

    @Inject
    public GetNode(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, WatchRepository watchDataRepository, int index) {
        super(threadExecutor, postExecutorThread);
        this.watchDataRepository = watchDataRepository;
        this.index = index;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.watchDataRepository.getNode(this.index);
    }
}
