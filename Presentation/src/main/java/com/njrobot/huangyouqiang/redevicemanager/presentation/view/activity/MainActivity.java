package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.presenter.MainPresenter;
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

    @OnClick(R.id.iv_more)
    void moreMenu(){
        PopupMenu popupMenu = new PopupMenu(this,moreMenu);
        popupMenu.getMenuInflater().inflate(R.menu.toolbar_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
        popupMenu.show();
    }
    @OnClick(R.id.btn_set_server)
    void editServerIP(){
       mainPresenter.editServer();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);

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

