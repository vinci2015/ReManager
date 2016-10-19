package com.njrobot.huangyouqiang.redevicemanager.presentation.presenter;


import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.njrobot.huangyouqiang.redevicemanager.domain.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.Constant;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.LocalSavingManager;
import com.njrobot.huangyouqiang.redevicemanager.presentation.BR;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MainView;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.MainActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchRecyclerAdapter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.handler.WatchRecyclerHandler;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.component.BlurDialog;

import java.util.ArrayList;

/**
 * @author huangyouqiang
 * @date 2016/8/5
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
        this.adapter = new WatchRecyclerAdapter(context,new ArrayList<WatchModel>(),this);
    }

    public void init(){
        serverIP = this.localSavingManager.getServerIP();
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
    @Bindable
    public String getServerIP() {
        return serverIP;
    }
    public void onClickChangeServerIP(){
        if(service.getIsInMission()){
            this.mainView.showMessage("当前有任务在执行，不能修改服务器信息");
            return;
        }
        this.mainView.Dialog(Constant.CHANGE_SERVER_IP_TITTLE, getServerIP(), new BlurDialog.OnConfirmClickListener() {
            @Override
            public void onConfirm(View v, String content) {
                setServerIP(content);
            }
        });
    }
    public void onClickChangeSite(final WatchRecyclerHandler handler){
        Log.i("MainPresenter","onClickChangeSite()");
        if(service.getIsInMission()){
            this.mainView.showMessage("当前有任务在执行，不能修改站点信息");
            return;
        }
        this.mainView.Dialog(Constant.CHANGE_WATCH_SITE_TITTLE, handler.getWatch().getSite(), new BlurDialog.OnConfirmClickListener() {
            @Override
            public void onConfirm(View v, String content) {
                WatchModel watchModel = handler.getWatch();
                watchModel.setSite(content);
                handler.setWatch(watchModel);
                localSavingManager.setWatch(watchModel);
                service.changeSite(watchModel.getId(),watchModel.getSite());
            }
        });
    }
    public void moreMenu(View view){
        PopupMenu popupMenu = new PopupMenu(mContext,view, Gravity.END);
        popupMenu.getMenuInflater().inflate(R.menu.toolbar_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu_cancel){
                    ((MainActivity)mContext).finish();
                }
                return true;
            }
        });
        popupMenu.show();
    }
}
