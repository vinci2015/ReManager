package com.njrobot.huangyouqiang.redevicemanager.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by huangyouqiang on 2016/6/23.
 */
public class RobotClient{
	private static final String TAG = RobotClient.class.getSimpleName();
	private static final String API_URL = "http://192.168.1.158:8080/overlord/";
	private String serverIP;
	private Context mContext;
	private RobotApi robotApi;

	public  RobotClient build(Context context){
		this.mContext = context;
		//this.serverIP = LocalSavingManager.getInstance(mContext).getServerIP();
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(API_URL)
		//		.baseUrl("http://"+this.serverIP+":8080/overlord/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.client(client)
				.build();
		robotApi = retrofit.create(RobotApi.class);
		return this;
	}

    /**
	 * 任务信息列表
	 * @param reqMissionList
     */
	public Observable<MissionEntity> getMissionList(final ReqMissionList reqMissionList){
		return robotApi.getMissionList(reqMissionList)
				.map(new Func1<ResMissionList, MissionEntity>() {
					@Override
					public MissionEntity call(ResMissionList resMissionList) {
						if(resMissionList.getMission_list().isEmpty()){
							return null;
						}
						return resMissionList.getMission_list().get(0);
					}
				});
	}
	/**
	 * 判断当前网络连接是否可用
	 * @return
     */
	private boolean isNetworkAvailable(){
		ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connectivityManager != null){
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if(networkInfo != null && networkInfo.isConnected()){
				if(networkInfo.getState() == NetworkInfo.State.CONNECTED){
					return  true;
				}
			}
		}
		Handler mHandler = new Handler(Looper.getMainLooper());
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mContext.getApplicationContext(),"当前网络不可用，请检查网络连接状况", Toast.LENGTH_SHORT).show();
			}
		});

		return false;
	}
}
