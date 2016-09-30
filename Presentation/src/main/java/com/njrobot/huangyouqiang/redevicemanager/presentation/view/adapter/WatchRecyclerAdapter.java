package com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.presentation.BR;
import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.presenter.MainPresenter;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter.handler.WatchRecyclerHandler;

import java.util.List;

/**
 * @author huangyouqiang
 * @date 2016/9/29
 */

public class WatchRecyclerAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<WatchModel> watchList;
    private MainPresenter mainPresenter;

    public WatchRecyclerAdapter(Context context, List<WatchModel> watchList, MainPresenter presenter) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.watchList = watchList;
        this.mainPresenter = presenter;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(this.mLayoutInflater, R.layout.watch_list_item,parent,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        WatchRecyclerHandler handler = new WatchRecyclerHandler(watchList.get(position));
        holder.getBinding().setVariable(BR.item,handler);
        holder.getBinding().executePendingBindings();
        holder.getBinding().setVariable(BR.presenter,mainPresenter);
    }

    @Override
    public int getItemCount() {
        return this.watchList.size();
    }
    public void addItem(WatchModel watchModel){
        if(watchModel != null){
            this.watchList.add(watchModel);
            notifyDataSetChanged();
        }
    }
}
