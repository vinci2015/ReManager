package com.njrobot.huangyouqiang.redevicemanager.presentation.presenter;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.Constant;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.LocalSavingManager;
import com.njrobot.huangyouqiang.redevicemanager.presentation.BR;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MainView;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.MissionInfoActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchRecyclerAdapter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.handler.WatchRecyclerHandler;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.component.BlurDialog;

import java.util.ArrayList;

/**
 * Created by huangyouqiang on 2016/8/5.
 */
public class MainPresenter  extends BaseObservable implements Presenter{
    private MainView mainView;
    private Context mContext;
    private LocalSavingManager localSavingManager;
    private WatchRecyclerAdapter adapter;
    private CommunicationService service;
    private String serverIP = Constant.DEFAULT_SERVER_IP;

    public MainPresenter(MainView mainView, Context context) {
        this.mainView = mainView;
        this.mContext = context;
        this.localSavingManager = LocalSavingManager.getInstance(mainView.context());
       // this.adapter = new WatchInfoAdapter(new ArrayList<WatchModel>(),mContext,"站点",this);
        this.adapter = new WatchRecyclerAdapter(context,new ArrayList<WatchModel>());
        init();
    }

    public void init(){
        String serverIP = this.localSavingManager.getServerIP();
        this.mainView.initView(this.adapter);
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
        this.mContext = null;
    }

    public void setService(CommunicationService service){
        this.service = service;
    }
   /* @Override
    public void changeSite(WatchModel watchModel) {
        if(this.service != null){
            this.service.changeSite(watchModel.getId(),watchModel.getSite());
        }
    }*/
    public void editServer(){
        String defIP = localSavingManager.getServerIP();
        this.mainView.Dialog("服务器IP", defIP, new BlurDialog.OnConfirmClickListener() {
            @Override
            public void onConfirm(View v, String content) {
                
            }
        });
    }
    public void setServerIP(String ip){
        if(!TextUtils.isEmpty(ip)) {
            this.serverIP = ip;
            this.localSavingManager.setServerIP(ip);
            notifyPropertyChanged(BR.serverIP);
        }
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
            notifyPropertyChanged(BR.serverIP);
        }
    }
    @Bindable
    public String getServerIP() {
        return serverIP;
    }
    public void onClickChangeServerIP(){
        this.mainView.Dialog(Constant.CHANGE_SERVER_IP_TITTLE, getServerIP(), new BlurDialog.OnConfirmClickListener() {
            @Override
            public void onConfirm(View v, String content) {
                // TODO: 2016/9/29  if is in mission 
                setServerIP(content);
            }
        });
    }
    public void onClickChangeSite(final WatchRecyclerHandler handler){
        this.mainView.Dialog(Constant.CHANGE_WATCH_SITE_TITTLE, handler.getWatch().getSite(), new BlurDialog.OnConfirmClickListener() {
            @Override
            public void onConfirm(View v, String content) {
                // TODO: 2016/9/29  if is in mission ? 
                WatchModel watchModel = handler.getWatch();
                watchModel.setSite(content);
                handler.setWatch(watchModel);
            }
        });
    }
    public void moreMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this.mContext,view);
        popupMenu.getMenuInflater().inflate(R.menu.toolbar_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu_find_robot){
                    mContext.startActivity(new Intent(mContext,MissionInfoActivity.class));
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
