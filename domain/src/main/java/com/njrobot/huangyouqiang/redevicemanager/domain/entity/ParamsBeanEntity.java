package com.njrobot.huangyouqiang.redevicemanager.domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/23.
 */
public class ParamsBeanEntity {
	private int code;
	private List<DataBean> data;
	private String info;

	public ParamsBeanEntity() {
		this.code = 0;
	}

	public ParamsBeanEntity(String waitProp, String waitValue){
		this.code = 0;
		this.data = new ArrayList<>();
		DataBean waitData = new DataBean(waitProp,waitValue);
		this.data.add(waitData);
	//empty
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		private String prop;
		private int prop_id;
		private String value;
		private int value_id;

		public DataBean(String waitProp,String waitValue){
			this.prop = waitProp;
			this.value = waitValue;
			//empty
		}

		public String getProp() {
			return prop;
		}

		public void setProp(String prop) {
			this.prop = prop;
		}

		public int getProp_id() {
			return prop_id;
		}

		public void setProp_id(int prop_id) {
			this.prop_id = prop_id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public int getValue_id() {
			return value_id;
		}

		public void setValue_id(int value_id) {
			this.value_id = value_id;
		}
	}
}

