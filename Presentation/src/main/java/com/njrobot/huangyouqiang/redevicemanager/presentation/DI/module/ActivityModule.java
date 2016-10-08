package com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module;

import android.support.v7.app.AppCompatActivity;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author huangyouqiang
 * @date 2016/8/3
 */
@Module
public class ActivityModule {
    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity AppCompatActivity provideAppCompatActivity(){
        return activity;
    }
}
