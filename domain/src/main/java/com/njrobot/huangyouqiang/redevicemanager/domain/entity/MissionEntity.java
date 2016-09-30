package com.njrobot.huangyouqiang.redevicemanager.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/23.
 * 描述一个任务对象的所有信息
 */
public class MissionEntity {

	public MissionEntity(String mFinalPoint,String waitProp,String waitValue) {
		//empty
		this.userId = 2;
		this.priority = 1;
		this.missionPoints = new ArrayList<>();
		this.missionPoints.add(new MissionPointEntity(mFinalPoint,waitProp,waitValue));
	}

	private ParamsBeanEntity area;

	private ParamsBeanEntity map;

	@SerializedName("mission_type")
	private ParamsBeanEntity missionType;

	@SerializedName("robot_type")
	private ParamsBeanEntity robotType;

	private ParamsBeanEntity status;

	// 任务指派时间
	@SerializedName("assigned_time")
	private String assignedTime;

	// 任务结束时间
	@SerializedName("finished_time")
	private String finishedTime;

	// 任务被调度时间
	@SerializedName("scheduled_time")
	private String scheduledTime;

	// 任务开始时间
	@SerializedName("started_time")
	private String startedTime;

	@SerializedName("text_info")
	private String textInfo;

	//下达任务的用户名称
	@SerializedName("user_name")
	private String username;

	//下达任务的用户id
	@SerializedName("user_id")
	private int userId;

	//任务id，若为零表示尚未被系统分配
	private int id;

	private int priority ;

	//执行任务的机器人id，若为0表示尚未指派特定机器人执行
	@SerializedName("robot_id")
	private int robotId;

	// 任务停靠点序列，每一个元素表示一个停靠点
	@SerializedName("mission_points")
	private List<MissionPointEntity> missionPoints;

	@SerializedName("next_mission_point")
	private MissionPointEntity nextMissionPoint;

	@SerializedName("previous_mission_point")
	private MissionPointEntity.MissionPointInfo previousMissionPoint;

	@SerializedName("next_route_point")
	private MissionPointEntity nextRoutePoint;

	@SerializedName("previous_route_point")
	private PointInfoEntity previousRoutePoint;

	@SerializedName("route_points")
	private List<PointInfoEntity> routePoints;

	public int getId() {
		return id;
	}

	public ParamsBeanEntity getArea() {
		return area;
	}

	public void setArea(ParamsBeanEntity area) {
		this.area = area;
	}

	public ParamsBeanEntity getMap() {
		return map;
	}

	public void setMap(ParamsBeanEntity map) {
		this.map = map;
	}

	public ParamsBeanEntity getMissionType() {
		return missionType;
	}

	public void setMissionType(ParamsBeanEntity missionType) {
		this.missionType = missionType;
	}

	public ParamsBeanEntity getRobotType() {
		return robotType;
	}

	public void setRobotType(ParamsBeanEntity robotType) {
		this.robotType = robotType;
	}

	public ParamsBeanEntity getStatus() {
		return status;
	}

	public void setStatus(ParamsBeanEntity status) {
		this.status = status;
	}

	public String getAssignedTime() {
		return assignedTime;
	}

	public void setAssignedTime(String assignedTime) {
		this.assignedTime = assignedTime;
	}

	public String getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(String startedTime) {
		this.startedTime = startedTime;
	}

	public String getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getRobotId() {
		return robotId;
	}

	public void setRobotId(int robotId) {
		this.robotId = robotId;
	}

	public List<MissionPointEntity> getMissionPoints() {
		return missionPoints;
	}

	public void setMissionPoints(List<MissionPointEntity> missionPoints) {
		this.missionPoints = missionPoints;
	}

	public MissionPointEntity getNextMissionPoint() {
		return nextMissionPoint;
	}

	public void setNextMissionPoint(MissionPointEntity nextMissionPoint) {
		this.nextMissionPoint = nextMissionPoint;
	}

	public MissionPointEntity.MissionPointInfo getPreviousMissionPoint() {
		return previousMissionPoint;
	}

	public void setPreviousMissionPoint(MissionPointEntity.MissionPointInfo previousMissionPoint) {
		this.previousMissionPoint = previousMissionPoint;
	}

	public MissionPointEntity getNextRoutePoint() {
		return nextRoutePoint;
	}

	public void setNextRoutePoint(MissionPointEntity nextRoutePoint) {
		this.nextRoutePoint = nextRoutePoint;
	}

	public PointInfoEntity getPreviousRoutePoint() {
		return previousRoutePoint;
	}

	public void setPreviousRoutePoint(PointInfoEntity previousRoutePoint) {
		this.previousRoutePoint = previousRoutePoint;
	}

	public List<PointInfoEntity> getRoutePoints() {
		return routePoints;
	}

	public void setRoutePoints(List<PointInfoEntity> routePoints) {
		this.routePoints = routePoints;
	}
}
