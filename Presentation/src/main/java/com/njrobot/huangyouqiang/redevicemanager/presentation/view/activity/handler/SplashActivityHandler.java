package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.handler;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.njrobot.huangyouqiang.redevicemanager.presentation.BR;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.MainActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.SplashActivity;


/**
 * @author huangyouqiang
 * @date 2016/9/28
 */
public class SplashActivityHandler extends BaseObservable {
    private String pwd;
    private Context mContext;
    private Handler mHandler;

    public SplashActivityHandler(Context mContext) {
        this.mContext = mContext;
        pwd = new String("");
        mHandler = new Handler();
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyPropertyChanged(BR.pwd);
        if(pwd.length() == 4) {
            mHandler.postDelayed(runnable, 200);
        }
    }
    public void clearPwd(@Nullable View v){
        setPwd("");
    }
    public void clickNumber(String s){
        setPwd(getPwd()+s);
    }

    private void checkPwd() {
        if(getPwd().equals("1245")){
            this.mContext.startActivity(new Intent(mContext, MainActivity.class));
            ((SplashActivity)mContext).finish();
        }else{
            Toast.makeText(mContext,"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
            clearPwd(null);
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            checkPwd();
        }
    };
}
