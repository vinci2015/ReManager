package com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njrobot.huangyouqiang.redevicemanager.presentation.BR;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.handler.SplashActivityHandler;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.handler.NumberRecyclerHandler;

/**
 * @author huangyouqiang
 * @date 2016/9/28
 */

public class NumberRecyclerAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private String[] strs;
    private LayoutInflater mInflater;
    private SplashActivityHandler mHandler;

    public NumberRecyclerAdapter(Context context, SplashActivityHandler handler) {
        this.mInflater = LayoutInflater.from(context);
        this.mHandler = handler;
        strs = new String[11];
        for(int i=1;i<10;i++){
            strs[i-1] = String.valueOf(i);
        }
        strs[9] = "null";
        strs[10] = "0";
    }

    @Override
    public int getItemViewType(int position) {
        if(strs[position].equals("null")){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(this.mInflater, R.layout.splash_recycler_item,parent,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.handler,this.mHandler);
        final NumberRecyclerHandler handler = new NumberRecyclerHandler(strs[position]);
        holder.getBinding().setVariable(BR.item,handler);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return strs.length;
    }
}
