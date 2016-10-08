package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.njrobot.huangyouqiang.redevicemanager.presentation.AndroidApplication;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.ApplicationComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.ActivityModule;

/**
 * @author huangyouqiang
 * @date 2016/8/3
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getApplicationComponent().inject(this);
    }

    public void addFragment(int containerViewId, Fragment fragment,String tag){
        FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
        transaction.add(containerViewId,fragment,tag);
        transaction.commit();
    }
    public < T extends Fragment> T getFragment(String tag){
        return (T)getFragmentManager().findFragmentByTag(tag);
    }

    ApplicationComponent getApplicationComponent(){
        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

    ActivityModule getActivityModule(){
        return  new ActivityModule(this);
    }
}
