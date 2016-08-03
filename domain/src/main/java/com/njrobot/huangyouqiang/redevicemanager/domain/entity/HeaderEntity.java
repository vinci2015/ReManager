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

	// 请求处理的错误码,code = 0 表示正确处理,"info":（String）错误描述字符串
	@SerializedName("cmd_code")
	private ParamsBeanEntity cmdCode;

	@SerializedName("device_id")
	private String deviceId;

	@SerializedName("msg_id")
	private int msgId;


	private String timestamp;

	private String token;

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
