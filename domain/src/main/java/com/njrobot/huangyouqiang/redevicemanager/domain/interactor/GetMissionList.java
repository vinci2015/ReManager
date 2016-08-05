package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class GetMissionList extends UseCase {
    private MissionRepository missionRepository;

    @Inject
    public GetMissionList(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
    }


    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.missions();
    }
}
