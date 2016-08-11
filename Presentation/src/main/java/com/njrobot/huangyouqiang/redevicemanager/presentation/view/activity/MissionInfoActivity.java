package com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.HasComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.DaggerMissionInfoComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.component.MissionInfoComponent;
import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.module.MissionModule;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.Fragment.MissionInfoFragment;

public class MissionInfoActivity extends BaseActivity implements HasComponent<MissionInfoComponent>{

    private static final String TAG = MissionInfoActivity.class.getSimpleName();
    private MissionInfoComponent missionInfoComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_info);
        addFragment(R.id.fragment_mission_info, MissionInfoFragment.newInstance("",""),TAG);
        initInjector();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public MissionInfoComponent getComponent() {
        return missionInfoComponent;
    }

    /**
     * c初始化注入器
     */
    public void initInjector(){
        this.missionInfoComponent = DaggerMissionInfoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .missionModule(new MissionModule(583,2))
                .build();
    }
}
