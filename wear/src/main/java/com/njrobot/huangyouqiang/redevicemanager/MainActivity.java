package com.njrobot.huangyouqiang.redevicemanager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.njrobot.redevicemanager.R;

import java.util.List;

import ticwear.design.app.AlertDialog;

public class MainActivity extends Activity implements TaskService.MessageLister,
				View.OnClickListener,
View.OnLongClickListener{

	private static final String TAG = MainActivity.class.getSimpleName();
	private BackGroundView backGroundView;
	private CallButton callButton;
	private ImageView iv_net;
	private Handler mHandler;
	private TaskService taskServer;
	private Intent serviceIntent;
	private boolean isMissionCancel = false;

	/**
	 *接收分钟改变广播
	 */
	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action == Intent.ACTION_TIME_TICK){
				Log.i(TAG,"on receive action time_stick");
				if(backGroundView != null){
					backGroundView.reFreshTime();
				}
			}
		}
	};
	ServiceConnection serviceConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			taskServer = ((TaskService.MyBinder)service).getService();
			taskServer.setMessageLister(MainActivity.this);
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					if(!taskServer.getSite().equals("")) {
						backGroundView.setSite(taskServer.getSite());
					}
					if(taskServer.isInMission()){
						if(taskServer.isFindRobot()){
							iv_net.setVisibility(View.VISIBLE);
						}
					}
					callButton.rebuildView(taskServer.isInMission(),taskServer.isFindRobot(),taskServer.getmDistance()+"");
				}
			});

			//callButton.rebuildView(taskServer.isInMission(),taskServer.isFindRobot(),taskServer.getmDistance()+"");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			taskServer = null;
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		stub.setBackground(new BackGroundView(this).getBackground());
		stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
			@Override
			public void onLayoutInflated(WatchViewStub stub) {
				backGroundView = (BackGroundView) stub.findViewById(R.id.background);
				callButton = (CallButton) stub.findViewById(R.id.call_button);
				callButton.setOnClickListener(MainActivity.this);
				iv_net = (ImageView) stub.findViewById(R.id.iv_net_bg);
				callButton.setOnLongClickListener(MainActivity.this);
			}
		});
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Intent.ACTION_TIME_TICK);
		registerReceiver(broadcastReceiver,intentFilter);
		mHandler = new Handler();
		serviceIntent = new Intent(MainActivity.this,TaskService.class);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(!isServiceWork(this,TaskService.class.getSimpleName())){
			startService(serviceIntent);
		}
		bindService(serviceIntent,serviceConnection,Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(taskServer != null){
			unbindService(serviceConnection);
			if(!taskServer.isInMission()){
				stopService(serviceIntent);
			}
		}
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG,"onDestroy");
		unregisterReceiver(broadcastReceiver);
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.call_button:
				if(!callButton.isInCalling()) {
					callButton.doCall();
					if(taskServer != null){
						taskServer.sendMission();
						isMissionCancel = false;
					}
				}
				break;
		}
	}

	@Override
	public boolean onLongClick(View v) {
		Log.i(TAG,"onLongClick");
		if(callButton != null && callButton.isInCalling() == true){
			isMissionCancel = true;
			iv_net.setVisibility(View.INVISIBLE);
			if(taskServer != null){
				taskServer.cancelMission();
			}
			callButton.resetView();
			return true;
		}
		return false;
	}

	@Override
	public void onGetSite(String site) {
		backGroundView.setSite(site);
	}

	@Override
	public void onMessageShow(String msg) {
		Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpdateDistance(final int distance) {
		if(isMissionCancel){
			return;
		}
		Log.i(TAG,"on message received, "+distance);
		if (distance == 0){
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					callButton.setText(distance+"M");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					callButton.resetView();
					iv_net.setVisibility(View.INVISIBLE);
				}
			});
		}else {
			if (callButton.isFoundRobot()) {//从第二次收到 FIND_ROBOT,只更新距离
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						callButton.setText(distance + "M");
					}
				});

			} else {//首次收到 FIND_ROBOT，更新背景和距离
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						callButton.setText(distance + "M");
						iv_net.setVisibility(View.VISIBLE);
						callButton.onFindRobot();
					}
				});
			}
		}
	}

	@Override
	public void resetView(final String msg) {
		Log.i(TAG,"resetView()");
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("错误")
						.setMessage(msg)
						.setPositiveButtonIcon(ticwear.design.R.drawable.tic_ic_btn_ok, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								callButton.resetView();
								isMissionCancel = true;
								dialog.dismiss();
							}
						}).show();
			}
		});

	}

	/**
	 * 判断某个服务是否正在运行的方法
	 *
	 * @param mContext
	 * @param serviceName eg:ConnectToServerService
	 * @return true代表正在运行，false代表服务没有正在运行
	 */
	public boolean isServiceWork(Context mContext, String serviceName) {
		boolean isWork = false;
		ActivityManager myAM = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
		if (myList.size() <= 0) {
			return false;
		}
		for (int i = 0; i < myList.size(); i++) {
			String mName = myList.get(i).service.getClass().getSimpleName();
			if (mName.equals(serviceName)) {
				isWork = true;
				break;
			}
		}
		return isWork;
	}

	/**
	 * 挠挠长按触发，此处用以取消任务
	 * @param e
	 * @return
     */
	public boolean onLongPressSidePanel(MotionEvent e) {
		Log.i(TAG, "onLongPressSidePanel");
		isMissionCancel = true;
		iv_net.setVisibility(View.INVISIBLE);
		if(taskServer != null){
			taskServer.cancelMission();
		}
		callButton.resetView();
		return true;
	}

}
