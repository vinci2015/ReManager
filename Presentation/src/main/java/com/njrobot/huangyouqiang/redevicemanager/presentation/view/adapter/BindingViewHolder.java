package com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @author huangyouqiang
 * @date 2016/9/28
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected final T mBinding;
    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
    public T getBinding(){
        return this.mBinding;
    }
}
