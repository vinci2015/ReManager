package com.njrobot.huangyouqiang.redevicemanager.presentation.view;

import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchInfoAdapter;

/**
 * Created by huangyouqiang on 2016/8/5.
 */
public interface MainView extends BaseView {
    void initView(String serveIP, WatchInfoAdapter adapter);
    void Dialog(String tittle,String defContent);
}
