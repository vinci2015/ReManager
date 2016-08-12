package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.presentation.presenter.MainPresenter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MainView;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchInfoAdapter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.component.BlurDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainView{
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.rl_root) RelativeLayout rootView;
    @BindView(R.id.iv_more) ImageView moreMenu;
    @BindView(R.id.tv_server_ip) TextView tvServerIP;
    @BindView(R.id.lv_watches) ListView lvWatches;

    private MainPresenter mainPresenter;

    //about service
    private CommunicationService service;
    private Intent serviceIntent;

    @OnClick(R.id.iv_more)
    void moreMenu(){
        PopupMenu popupMenu = new PopupMenu(this,moreMenu);
        popupMenu.getMenuInflater().inflate(R.menu.toolbar_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.menu_find_robot){
                    startActivity(new Intent(MainActivity.this,MissionInfoActivity.class));
                }
                return false;
            }
        });
        popupMenu.show();
    }
    @OnClick(R.id.btn_set_server)
    void editServerIP(){
       mainPresenter.editServer();
    }
    private CommunicationService.OnServiceMessageCallback callback = new CommunicationService.OnServiceMessageCallback() {
        @Override
        public void onStartMission(int robotId) {

        }

        @Override
        public void onFindWatch(WatchModel watchModel) {
            Log.i(TAG,"onFindWatch");
            mainPresenter.findWatch(watchModel);
        }

        @Override
        public void sendMessage(String s) {

        }
    };
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG,"onServiceConnected");
            service = ((CommunicationService.ServiceBinder)iBinder).getService();
            service.setServiceCallback(callback);
            mainPresenter.setService(service);
            mainPresenter.getNode();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e(TAG,"onServiceDisconnected()");
            service.ResetCallback();
            service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);
        serviceIntent = new Intent(MainActivity.this,CommunicationService.class);
        //已startservice方式启动service，只有调用stopservice方法才结束service
        //与service的通讯方式采用回调和直接持有service对象的方式
        startService(serviceIntent);
        bindService(serviceIntent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        mainPresenter.destroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void initView(String serverIP, WatchInfoAdapter adapter) {
        tvServerIP.setText(serverIP);
        lvWatches.setAdapter(adapter);
    }

    @Override
    public void Dialog(String tittle, String defContent) {
        final BlurDialog dialog = new BlurDialog(this, R.style.MyDialog,true,tittle, defContent);
        dialog.show();
        dialog.setOnConfirmClickListener(new BlurDialog.OnConfirmClickListener() {
            @Override
            public void onConfirm(View v, String content) {
                dialog.dismiss();
                mainPresenter.setServerIP(content);
                tvServerIP.setText(content);
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(rootView,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    public boolean isInMission(){
        return false;
    }
}

