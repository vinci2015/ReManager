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
    private int missionId;
    private MissionRepository missionRepository;

    @Inject
    public CancelMission(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
    }

    public void resetParams(int id){
        this.missionId = missionId;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.cancelMission(this.missionId);
    }
}
