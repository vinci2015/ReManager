package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.MissionInfoModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;

import dagger.Component;

/**
 * @author huangyouqiang
 * @date 2016/8/12
 */
@PerService
@Component(dependencies = ApplicationComponent.class,modules = MissionInfoModule.class)
public interface MissionInfoServiceComponent {
    void inject(CommunicationService service);
}
