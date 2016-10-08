package com.njrobot.huangyouqiang.redevicemanager.presentation;

import android.app.Application;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.ApplicationComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.DaggerApplicationComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author huangyouqiang
 * @date 2016/8/3
 */
public class AndroidApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        initLeakCanary();
    }

    private void initLeakCanary() {
        LeakCanary.install(this);
    }

    private void initInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
