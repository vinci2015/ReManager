package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.databinding.ActivityMainCopyBinding;
import com.njrobot.huangyouqiang.redevicemanager.presentation.presenter.MainPresenter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.service.CommunicationService;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MainView;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchRecyclerAdapter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.component.BlurDialog;



public class MainActivity extends BaseActivity implements MainView{
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenter mainPresenter;
    private ActivityMainCopyBinding binding;
    //about service
    private CommunicationService service;
    private Intent serviceIntent;
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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainPresenter = new MainPresenter(this,this);
        binding.setPresenter(mainPresenter);
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
    public void initView(WatchRecyclerAdapter adapter) {
       binding.recyclerWatches.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerWatches.setAdapter(adapter);
    }

    @Override
    public void Dialog(final String tittle, String defContent, BlurDialog.OnConfirmClickListener listener) {
        final BlurDialog dialog = new BlurDialog(this, R.style.MyDialog,true,tittle, defContent);
        dialog.show();
        dialog.setOnConfirmClickListener(listener);
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(binding.root,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    public boolean isInMission(){
        return false;
    }
}

