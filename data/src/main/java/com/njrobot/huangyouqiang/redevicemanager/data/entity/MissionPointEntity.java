package com.njrobot.huangyouqiang.redevicemanager.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/23.
 * 	关于单独停靠点相关信息
 */
public class MissionPointEntity {
	private float est_distance;
	private int est_seconds;
	private int percent;
	private MissionPointInfo mission_point;

	public MissionPointEntity(String siteName,String waitProp,String waitValue) {
		//empty
		this.mission_point = new MissionPointInfo(siteName,waitProp,waitValue);
	}

	public float getEst_distance() {
		return est_distance;
	}

	public int getEst_seconds() {
		return est_seconds;
	}

	public int getPercent() {
		return percent;
	}

	public MissionPointInfo getMission_point() {
		return mission_point;
	}

	// 该停靠点所对应的路径点信息
	public static class MissionPointInfo{
		public MissionPointInfo(String siteName,String waitProp,String waitValue) {
			//empty
			this.point_info = new PointInfoEntity(siteName);
			this.wait_conditions = new ArrayList<>();
			ParamsBeanEntity waitParam = new ParamsBeanEntity(waitProp,waitValue);
			this.wait_conditions.add(waitParam);
		}

		private ParamsBeanEntity action;
		private String due_time_end;
		private String due_time_start;
		private List<ParamsBeanEntity> preconditions;
		private List<ParamsBeanEntity> wait_conditions;
		private List<ParamsBeanEntity.DataBean> props;
		private PointInfoEntity point_info;

		public ParamsBeanEntity getAction() {
			return action;
		}

		public String getDue_time_end() {
			return due_time_end;
		}

		public String getDue_time_start() {
			return due_time_start;
		}

		public List<ParamsBeanEntity> getPreconditions() {
			return preconditions;
		}

		public List<ParamsBeanEntity> getWait_conditions() {
			return wait_conditions;
		}

		public List<ParamsBeanEntity.DataBean> getProps() {
			return props;
		}

		public PointInfoEntity getPoint_info() {
			return point_info;
		}
	}
}
