package com.njrobot.huangyouqiang.redevicemanager.domain.entity;

/**
 * Created by huangyouqiang on 2016/6/23.
 */
public class PointInfoEntity {

	public PointInfoEntity(String name) {
		//empty
		this.name = name;
	}

	private String alias;
	private ParamsBeanEntity area;
	private int heading;
	private int id;
	private int index;
	private String info;
	private ParamsBeanEntity map;
	private String name;
	private int pos_x;
	private int pos_y;
	private ParamsBeanEntity status;
	private ParamsBeanEntity sub_type;
	private ParamsBeanEntity type;

	public String getAlias() {
		return alias;
	}

	public ParamsBeanEntity getArea() {
		return area;
	}

	public int getHeading() {
		return heading;
	}

	public int getId() {
		return id;
	}

	public int getIndex() {
		return index;
	}

	public String getInfo() {
		return info;
	}

	public ParamsBeanEntity getMap() {
		return map;
	}

	public String getName() {
		return name;
	}

	public int getPos_x() {
		return pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public ParamsBeanEntity getStatus() {
		return status;
	}

	public ParamsBeanEntity getSub_type() {
		return sub_type;
	}

	public ParamsBeanEntity getType() {
		return type;
	}
}
