package com.njrobot.huangyouqiang.redevicemanager;

import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.wearable.MessageApi;
import com.mobvoi.android.wearable.Wearable;

/**
 * Created by huangyouqiang on 2016/5/24.
 * send a command to the pad client to get a robot
 */
public class SendRobotRequirement implements Runnable {
	private static final String CommandStr = Constant.MESSAGE_ROBOT_REQUIREMENT;
	private MobvoiApiClient mobvoiApiClient;
	private byte[] datas;
	private String phoneId;
	private ResultCallBack callBack;

	public SendRobotRequirement(MobvoiApiClient mMobvoiApiClient,String mPhoneId,byte[] mDatas,ResultCallBack mCallBack){
		this.mobvoiApiClient = mMobvoiApiClient;
		this.datas = mDatas;
		this.phoneId = mPhoneId;
		this.callBack = mCallBack;
	}
	@Override
	public void run() {
		Wearable.MessageApi.sendMessage(this.mobvoiApiClient,this.phoneId,CommandStr,this.datas)
						.setResultCallback(new ResultCallback<MessageApi.SendMessageResult>() {
							@Override
							public void onResult(final MessageApi.SendMessageResult sendMessageResult) {

										callBack.OnResult(sendMessageResult.getStatus().isSuccess(),sendMessageResult.getStatus().getStatusMessage());
									}
								});
	}
}
