package com.njrobot.huangyouqiang.redevicemanager.data.entity;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.Mission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/23.
 * ����һ����������������Ϣ
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

	// ����ָ��ʱ��
	@SerializedName("assigned_time")
	private String assignedTime;

	// �������ʱ��
	@SerializedName("finished_time")
	private String finishedTime;

	// ���񱻵���ʱ��
	@SerializedName("scheduled_time")
	private String scheduledTime;

	// ����ʼʱ��
	@SerializedName("started_time")
	private String startedTime;

	@SerializedName("text_info")
	private String textInfo;

	//�´�������û�����
	@SerializedName("user_name")
	private String username;

	//�´�������û�id
	@SerializedName("user_id")
	private int userId;

	//����id����Ϊ���ʾ��δ��ϵͳ����
	private int id;

	private int priority ;

	//ִ������Ļ�����id����Ϊ0��ʾ��δָ���ض�������ִ��
	@SerializedName("robot_id")
	private int robotId;

	// ����ͣ�������У�ÿһ��Ԫ�ر�ʾһ��ͣ����
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

	public Mission transform(){
		Mission mission  = new Mission(getId());
		mission.setUserId(getUserId());
		mission.setPriority(getPriority());
		mission.setDestinationSite(getMissionPoints().get(0).getMission_point().getPoint_info().getName());
		return mission;
	}
}
