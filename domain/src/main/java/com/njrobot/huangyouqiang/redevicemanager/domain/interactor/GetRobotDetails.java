package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/5.
 */
public class GetRobotDetails extends UseCase {
    private MissionRepository missionRepository;
    private final int robotId;

    @Inject
    public GetRobotDetails(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository, int robotId) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
        this.robotId = robotId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.missionRepository.robot(this.robotId);
    }
}
