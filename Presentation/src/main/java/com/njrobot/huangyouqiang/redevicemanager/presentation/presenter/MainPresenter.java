package com.njrobot.huangyouqiang.redevicemanager.presentation.presenter;


import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.LocalSavingManager;
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
    private CommunicationService service;

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
        this.mainView = null;
        this.localSavingManager = null;
        this.adapter = null;
        this.service = null;
    }

    public void setService(CommunicationService service){
        this.service = service;
    }
    @Override
    public void changeSite(WatchModel watchModel) {
        if(this.service != null){
            this.service.changeSite(watchModel.getId(),watchModel.getSite());
        }
    }
    public void editServer(){
        String defIP = localSavingManager.getServerIP();
        this.mainView.Dialog("服务器IP",defIP);
    }
    public void setServerIP(String ip){

        this.localSavingManager.setServerIP(ip);
    }
    public void findWatch(WatchModel model){
        WatchModel localModel = localSavingManager.getWatch(model.getId());
        if(localModel == null){//preference 中没有存储这个watch
            localSavingManager.addWatch(model);
            adapter.addItem(model);
        }else{
            adapter.addItem(localModel);
        }
    }
    public void getNode(){
        if(this.service != null){
            this.service.getNode();
        }
    }
}
