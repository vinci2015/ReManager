package com.njrobot.huangyouqiang.redevicemanager.domain.executor;

import rx.Scheduler;

/**
 * Created by huangyouqiang on 2016/7/28.
 */
public interface PostExecutorThread {
    Scheduler getScheduler();
}
