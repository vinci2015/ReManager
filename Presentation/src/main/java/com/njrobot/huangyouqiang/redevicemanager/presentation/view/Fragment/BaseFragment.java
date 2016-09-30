package com.njrobot.huangyouqiang.redevicemanager.presentation.view.Fragment;

import android.app.Fragment;

import com.njrobot.huangyouqiang.redevicemanager.presentation.DI.HasComponent;


/**
 * Created by huangyouqiang on 2016/8/3.
 */
public abstract class BaseFragment extends Fragment {


    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
