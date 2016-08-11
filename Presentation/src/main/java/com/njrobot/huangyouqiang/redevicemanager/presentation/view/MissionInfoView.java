package com.njrobot.huangyouqiang.redevicemanager.presentation.view;

import com.njrobot.huangyouqiang.redevicemanager.data.model.Mission;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
public interface MissionInfoView extends LoadDataView{
    /**
     * rend a mission info to the view
     */
    void renderMission(Mission mission);
}
