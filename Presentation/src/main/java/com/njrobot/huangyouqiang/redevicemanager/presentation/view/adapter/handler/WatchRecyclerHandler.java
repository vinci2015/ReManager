package com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.handler;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.njrobot.huangyouqiang.redevicemanager.presentation.BR;
import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;

/**
 * @author huangyouqiang
 * @date 2016/9/29
 */

public class WatchRecyclerHandler extends BaseObservable{
    private WatchModel watch ;

    public WatchRecyclerHandler(WatchModel watchModel) {
        this.watch = watchModel;
    }

    @Bindable
    public WatchModel getWatch() {
        return watch;
    }

    public void setWatch(WatchModel watch) {
        this.watch = watch;
        notifyPropertyChanged(BR.watch);
    }

}
