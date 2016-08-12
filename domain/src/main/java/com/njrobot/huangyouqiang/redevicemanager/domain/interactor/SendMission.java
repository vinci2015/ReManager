package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by huangyouqiang on 2016/8/12.
 */
public class SendMission extends UseCase {
    private MissionRepository missionRepository;
    private String site;

    @Inject
    public SendMission(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread, MissionRepository missionRepository) {
        super(threadExecutor, postExecutorThread);
        this.missionRepository = missionRepository;
    }

    public void resetParams(String site){
        this.site = site;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return missionRepository.sendMission(this.site);
    }
}
