package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component;

import android.content.Context;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.repository.MissionRepository;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ApplicationModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //exposed to sub graphs
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutorThread postExecutorThread();
    MissionRepository missionRepository();
}
