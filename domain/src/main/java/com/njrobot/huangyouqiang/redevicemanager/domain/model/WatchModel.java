package com.njrobot.huangyouqiang.redevicemanager.domain.model;


import java.io.Serializable;

/**
 * Created by huangyouqiang on 2016/4/28.
 */
public class WatchModel implements Serializable {
	private String id;
	private String name;
	private String site;

	public WatchModel(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
