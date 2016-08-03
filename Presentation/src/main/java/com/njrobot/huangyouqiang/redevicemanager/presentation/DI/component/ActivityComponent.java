package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ActivityModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ApplicationModule;

import dagger.Component;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
@PerActivity
@Component(dependencies = ApplicationModule.class,modules = ActivityModule.class)
public interface ActivityComponent {

    //exposed to sub_graphs
    AppCompatActivity activity();
}
