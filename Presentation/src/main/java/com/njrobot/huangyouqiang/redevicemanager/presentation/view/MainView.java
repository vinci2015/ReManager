package com.njrobot.huangyouqiang.redevicemanager.presentation.view;

import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.WatchRecyclerAdapter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.component.BlurDialog;

/**
 * Created by huangyouqiang on 2016/8/5.
 */
public interface MainView extends BaseView {
    void initView(WatchRecyclerAdapter adapter);
    void Dialog(String tittle, String defContent, BlurDialog.OnConfirmClickListener listener);
}
