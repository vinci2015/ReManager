package com.njrobot.huangyouqiang.redevicemanager.data.model;


import com.mobvoi.android.wearable.Node;

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

	public static WatchModel transformFromNode(Node node){
		WatchModel watchModel  = null;
		if(node != null){
			watchModel = new WatchModel(node.getId());
			watchModel.setName(node.getDisplayName());
		}
		return watchModel;
	}
}
