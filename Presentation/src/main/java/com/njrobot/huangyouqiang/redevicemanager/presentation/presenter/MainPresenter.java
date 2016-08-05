package com.njrobot.huangyouqiang.redevicemanager.presentation.presenter;

import android.content.Context;

import com.njrobot.huangyouqiang.redevicemanager.presentation.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.presentation.utils.LocalSavingManager;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MainView;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.MainActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchInfoAdapter;

import java.util.ArrayList;

/**
 * Created by huangyouqiang on 2016/8/5.
 */
public class MainPresenter implements Presenter ,WatchInfoAdapter.DeliverMessage{
    private MainView mainView;
    private LocalSavingManager localSavingManager;
    private WatchInfoAdapter adapter;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        this.localSavingManager = LocalSavingManager.getInstance(mainView.context());
        this.adapter = new WatchInfoAdapter(new ArrayList<WatchModel>(),(MainActivity)mainView,"站点",this);
        init();
    }

    public void init(){
        String serverIP = this.localSavingManager.getServerIP();
        this.mainView.initView(serverIP,this.adapter);
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void changeSite(WatchModel watchModel) {

    }
    public void editServer(){
        String defIP = localSavingManager.getServerIP();
        this.mainView.Dialog("服务器IP",defIP);
    }
    public void setServerIP(String ip){

        this.localSavingManager.setServerIP(ip);
    }
}
