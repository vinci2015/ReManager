package com.njrobot.huangyouqiang.redevicemanager.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/29.
 * 描述一个机器人对象的所有
 */
public class RobotEntity {

    private ParamsBeanEntity area;

    @SerializedName("critical_power_percent")
    private int criticalPowerPercent;

    // 在地图上朝向
    private float heading;

    //机器人id
    private int id;

    @SerializedName("load_status")
    private ParamsBeanEntity loadStatus;

    //机器人mac地址信息
    @SerializedName("mac_address")
    private String macAddress;

    /**
     * “code”: (integer) // 机器人所处地图id
     “info”: (striing) // 机器人所处地图名称
     */
    private ParamsBeanEntity map;

    // 机器人当前速度
    @SerializedName("nominal_vel")
    private float speed;

    // 机器人当前角速度
    @SerializedName("nominal_w")
    private float angularVelocity;

    // 在地图上的x坐标
    @SerializedName("pos_x")
    private float posX;

    // 在地图上的y坐标
    @SerializedName("pos_y")
    private float posY;

    // 机器人当前电量百分比
    @SerializedName("power_percent")
    private int powerPercent;

    @SerializedName("response_time")
    private float responseTime;

    @SerializedName("safe_distance")
    private float safeDistance;

    @SerializedName("schedule_status")
    private ParamsBeanEntity scheduleStatus;

    /**
     * “code”: (integer) // 机器人状态id
     “info”: (striing) // 机器人状态描述符
     */
    private ParamsBeanEntity status;

    /**
     *  “code”: (integer) // 机器人类型id
     “info”: (striing) // 机器人类型描述符
     */
    private ParamsBeanEntity type;

    private float vel;

    private List<ParamsBeanEntity.DataBean> props;

    public ParamsBeanEntity getArea() {
        return area;
    }

    public void setArea(ParamsBeanEntity area) {
        this.area = area;
    }

    public int getCriticalPowerPercent() {
        return criticalPowerPercent;
    }

    public float getHeading() {
        return heading;
    }

    public int getId() {
        return id;
    }

    public ParamsBeanEntity getLoadStatus() {
        return loadStatus;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public ParamsBeanEntity getMap() {
        return map;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public int getPowerPercent() {
        return powerPercent;
    }

    public float getResponseTime() {
        return responseTime;
    }

    public float getSafeDistance() {
        return safeDistance;
    }

    public ParamsBeanEntity getScheduleStatus() {
        return scheduleStatus;
    }

    public ParamsBeanEntity getStatus() {
        return status;
    }

    public ParamsBeanEntity getType() {
        return type;
    }

    public float getVel() {
        return vel;
    }

    public List<ParamsBeanEntity.DataBean> getProps() {
        return props;
    }
}
