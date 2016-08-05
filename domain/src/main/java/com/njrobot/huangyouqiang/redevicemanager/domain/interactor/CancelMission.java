package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class CancelMission extends UseCase {
    private final int missionId;
    private MissionRepository missionRepository;

    @Inject
    public CancelMission(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, int missionId, MissionRepository missionRepository) {
        super(threadExecutor, postExecutorThread);
        this.missionId = missionId;
        this.missionRepository = missionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.cancelMission(this.missionId);
    }
}
