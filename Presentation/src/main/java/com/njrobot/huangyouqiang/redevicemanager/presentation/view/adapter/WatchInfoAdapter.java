package com.njrobot.huangyouqiang.redevicemanager.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.presentation.model.WatchModel;
import com.njrobot.huangyouqiang.redevicemanager.presentation.utils.LocalSavingManager;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.MainActivity;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.component.BlurDialog;

import java.util.List;

/**
 * Created by huangyouqiang on 2016/4/28.
 */
public class WatchInfoAdapter extends BaseAdapter {
	private static final String TAG = WatchInfoAdapter.class.getSimpleName();
	private LayoutInflater mLayoutInflater;
	private Context context;
	private String tittle;
	private List<WatchModel> watchList;
	private LocalSavingManager localSavingManager;
	private DeliverMessage deliverMessage;

	public WatchInfoAdapter(List<WatchModel> watchList, Context context, String tittle, DeliverMessage deliverMessage){
		this.watchList = watchList;
		this.mLayoutInflater = LayoutInflater.from(context);
		this.context = context;
		this.tittle = tittle;
		this.deliverMessage = deliverMessage;
		localSavingManager = LocalSavingManager.getInstance(this.context);
	}
	public interface DeliverMessage{
		void changeSite(WatchModel watchModel);
	}
	@Override
	public int getCount() {
		return this.watchList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.watchList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		final ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.watch_list_item,null);
			viewHolder.tv_watch_id = (TextView) convertView.findViewById(R.id.tv_watch_id);
			viewHolder.tv_watch_site = (TextView) convertView.findViewById(R.id.tv_watch_site);
			viewHolder.btn_edit = (Button) convertView.findViewById(R.id.btn_edt);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tv_watch_id.setText(watchList.get(position).getName());
		viewHolder.tv_watch_site.setText(watchList.get(position).getSite());
		viewHolder.btn_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(((MainActivity)context).isInMission()){
					Toast.makeText(context,"正在执行任务，不可修改站点", Toast.LENGTH_SHORT).show();
					return;
				}
				// alert edit dialog
				final BlurDialog dialog = new BlurDialog(context, R.style.MyDialog,true, tittle, watchList.get(position).getSite());
				dialog.show();
				dialog.setOnConfirmClickListener( new BlurDialog.OnConfirmClickListener() {
					@Override
					public void onConfirm(View v, String content) {
						dialog.dismiss();
						WatchModel watchModel = watchList.get(position);
						watchModel.setSite(content);
						localSavingManager.setWatch(watchModel);
						viewHolder.tv_watch_site.setText(content);
						deliverMessage.changeSite(watchModel);
					}
				});
			}
		});
		return convertView;
	}

	public void addItem(WatchModel watchModel){
		if(watchList.isEmpty()) {
			this.watchList.add(watchModel);
			notifyDataSetChanged();
		}else{
			if(!watchModel.getId().equals(watchList.get(0).getId())){
				this.watchList.add(watchModel);
				notifyDataSetChanged();
			}
		}
	}
	class ViewHolder {
		TextView tv_watch_id;
		TextView tv_watch_site;
		Button btn_edit;
	}
}
