package com.njrobot.huangyouqiang.redevicemanager.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.google.gson.Gson;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.NetworkConnectionException;
import com.njrobot.huangyouqiang.redevicemanager.data.exception.ResponseNotCorrectException;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.ParamsBeanEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.RobotEntity;

import java.util.Collections;
import java.util.List;

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
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
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
	 * 指定任务信息
	 * @param reqMissionList
	 * @param missionId
     */
	public Observable<MissionEntity> getMission(final ReqMissionList reqMissionList, final int missionId){
		Log.i("robotClient","getmission");
		return robotApi.getMissionList(reqMissionList)
				.flatMap(new Func1<ResMissionList, Observable<List<MissionEntity>>>() {
					@Override
					public Observable<List<MissionEntity>> call(ResMissionList resMissionList) {
						ParamsBeanEntity errorBundle = resMissionList.getHeader().getError_code();
						if(errorBundle.getCode()!= 0){
							Log.i(TAG,"error");
							return Observable.error(new ResponseNotCorrectException(errorBundle.getInfo()));
						}
						List<MissionEntity> missionListRecently = resMissionList.getMission_list();
						Collections.reverse(missionListRecently);
						return Observable.just(missionListRecently);
					}
				})
				.flatMap(new Func1<List<MissionEntity>, Observable<MissionEntity>>() {
					@Override
					public Observable<MissionEntity> call(List<MissionEntity> missionEntities) {
						return Observable.from(missionEntities);
					}
				}).filter(new Func1<MissionEntity, Boolean>() {
					@Override
					public Boolean call(MissionEntity entity) {
						return entity.getId() == missionId;
					}
				});
	}

	/**
	 * 获取任务列表
	 * @param reqMissionList
	 * @return
     */
	public Observable<List<MissionEntity>> getMissionList(ReqMissionList reqMissionList){
		return robotApi.getMissionList(reqMissionList)
				.flatMap(new Func1<ResMissionList, Observable<List<MissionEntity>>>() {
					@Override
					public Observable<List<MissionEntity>> call(ResMissionList resMissionList){
						ParamsBeanEntity errorBundle = resMissionList.getHeader().getError_code();
						if(errorBundle.getCode()!= 0){
							return Observable.error(new ResponseNotCorrectException(errorBundle.getInfo()));
						}
						return Observable.just(resMissionList.getMission_list());
					}
				});

	}

	/**取消指定任务
	 * qu
	 * @param reqCancelMission
	 * @return
     */
	public Observable<MissionEntity> cancelMission(ReqCancelMission reqCancelMission){
		if(isNetworkAvailable()) {
			return robotApi.cancelMission(reqCancelMission)
					.map(new Func1<ResCancelMission, MissionEntity>() {
						@Override
						public MissionEntity call(ResCancelMission resCancelMission) {
							return resCancelMission.getMission_info();
						}
					});
		}
		return Observable.error(new NetworkConnectionException());
	}
	public Observable<RobotEntity> getRobot(final ReqRobot reqRobot){
		Gson gson = new Gson();
		String s = gson.toJson(reqRobot);
		Log.i(TAG,s);
		return robotApi.getRobot(reqRobot)
				.map(new Func1<ResRobot, ResRobot>() {
					@Override
					public ResRobot call(ResRobot resRobot) {
						if(resRobot.getHeader().getError_code().getCode() != 0){
							throw new ResponseNotCorrectException(resRobot.getHeader().getError_code().getInfo());
						}
						return resRobot;
					}
				})
				.filter(new Func1<ResRobot, Boolean>() {
					@Override
					public Boolean call(ResRobot resRobot) {
						return !resRobot.getRobotEntities().isEmpty();
					}
				}).map(new Func1<ResRobot, RobotEntity>() {
					@Override
					public RobotEntity call(ResRobot resRobot) {
						return resRobot.getRobotEntities().get(0);
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
		return false;
	}
}
