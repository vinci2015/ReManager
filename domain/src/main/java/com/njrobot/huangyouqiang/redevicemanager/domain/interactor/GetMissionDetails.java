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
    private int missionId;
    private MissionRepository missionRepository;

    @Inject
    public GetMissionDetails(MissionRepository missionRepository, ThreadExecutor threadExecutor,
                             PostExecutorThread postExecutorThread) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
    }

    public void resetParam(int id){
        this.missionId = id;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.mission(this.missionId);
    }
}
