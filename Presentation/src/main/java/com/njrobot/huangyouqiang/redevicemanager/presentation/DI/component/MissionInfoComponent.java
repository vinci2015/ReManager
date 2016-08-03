package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ActivityModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.MissionModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.Fragment.MissionInfoFragment;

import dagger.Component;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class,MissionModule.class})
public interface MissionInfoComponent extends ActivityComponent {
    void inject(MissionInfoFragment missionInfoFragment);
}
