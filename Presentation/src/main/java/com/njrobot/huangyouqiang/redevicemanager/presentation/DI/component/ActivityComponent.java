package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component;

import android.support.v7.app.AppCompatActivity;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ActivityModule;

import dagger.Component;

/**
 * @author huangyouqiang
 * @date 2016/8/3
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
interface ActivityComponent {

    //exposed to sub_graphs
    AppCompatActivity activity();
}
