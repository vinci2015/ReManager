package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;


import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/7/28.
 */
public class GetMissionDetails extends UseCase {
    private MissionRepository missionRepository;

    public GetMissionDetails(MissionRepository missionRepository,ThreadExecutor threadExecutor,
                                PostExecutorThread postExecutorThread) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.mission();
    }
}
