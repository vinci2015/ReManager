package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.WatchModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;

import dagger.Component;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
@PerService
@Component(dependencies = ApplicationComponent.class,modules = WatchModule.class)
public interface WatchComponent {
    void inject(CommunicationService service);
}
