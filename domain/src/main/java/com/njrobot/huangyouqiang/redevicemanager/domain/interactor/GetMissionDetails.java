package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;


import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/28.
 */
public class GetMissionDetails extends UseCase {
    private final int missionId;
    private MissionRepository missionRepository;

    @Inject
    public GetMissionDetails(MissionRepository missionRepository, ThreadExecutor threadExecutor,
                             PostExecutorThread postExecutorThread, int missionId) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
        this.missionId = missionId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.mission(this.missionId);
    }
}
