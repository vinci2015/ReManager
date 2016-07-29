package com.njrobot.huangyouqiang.redevicemanager.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by huangyouqiang on 2016/6/23.
 */
public class HeaderEntity {

	public HeaderEntity() {
		//empty
		this.cmdCode = new ParamsBeanEntity();
		this.deviceId = "web";
		this.msgId = 0;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.timestamp = format.format(new Date());
		this.token = "string";
		this.userId = 2;
	}

	// 请求处理的错误码, code = 0 值表示正确处理,“info”: (string) // 错误描述字符串
	@SerializedName("cmd_code")
	private ParamsBeanEntity cmdCode;

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("msg_id")
	private int msgId;

	//请求时间戳or//响应时间戳
	private String timestamp;

	// 用于验证用户合法性的授权token，目前测试版本没有开启验证功能，只需要保证user_id非零即可
	private String token;

	//发起该请求的user_id
	@SerializedName("user_id")
	private int userId;

	public void setCmdCode(ParamsBeanEntity cmdCode) {
		this.cmdCode = cmdCode;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
