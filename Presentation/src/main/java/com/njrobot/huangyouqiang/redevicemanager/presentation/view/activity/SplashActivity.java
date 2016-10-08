package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.databinding.ActivitySplashBinding;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.handler.SplashActivityHandler;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.NumberRecyclerAdapter;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        SplashActivityHandler activityHandler = new SplashActivityHandler(this);
        binding.setHandler(activityHandler);
        binding.recycler.setLayoutManager(new GridLayoutManager(this,3));
        binding.recycler.setAdapter(new NumberRecyclerAdapter(this, activityHandler));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
