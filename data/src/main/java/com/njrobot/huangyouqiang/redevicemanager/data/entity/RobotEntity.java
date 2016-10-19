package com.njrobot.huangyouqiang.redevicemanager.data.entity;

import com.google.gson.annotations.SerializedName;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.Robot;

import java.util.List;

/**
 * Created by huangyouqiang on 2016/6/29.
 * ����һ�������˶��������
 */
public class RobotEntity {

    private ParamsBeanEntity area;

    @SerializedName("critical_power_percent")
    private int criticalPowerPercent;

    // �ڵ�ͼ�ϳ���
    private float heading;

    //������id
    private int id;

    @SerializedName("load_status")
    private ParamsBeanEntity loadStatus;

    //������mac��ַ��Ϣ
    @SerializedName("mac_address")
    private String macAddress;

    /**
     * ��code��: (integer) // ������������ͼid
     ��info��: (striing) // ������������ͼ����
     */
    private ParamsBeanEntity map;

    // �����˵�ǰ�ٶ�
    @SerializedName("nominal_vel")
    private float speed;

    // �����˵�ǰ���ٶ�
    @SerializedName("nominal_w")
    private float angularVelocity;

    // �ڵ�ͼ�ϵ�x����
    @SerializedName("pos_x")
    private float posX;

    // �ڵ�ͼ�ϵ�y����
    @SerializedName("pos_y")
    private float posY;

    // �����˵�ǰ�����ٷֱ�
    @SerializedName("power_percent")
    private int powerPercent;

    @SerializedName("response_time")
    private float responseTime;

    @SerializedName("safe_distance")
    private float safeDistance;

    @SerializedName("schedule_status")
    private ParamsBeanEntity scheduleStatus;

    /**
     * ��code��: (integer) // ������״̬id
     ��info��: (striing) // ������״̬������
     */
    private ParamsBeanEntity status;

    /**
     *  ��code��: (integer) // ����������id
     ��info��: (striing) // ����������������
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

    public Robot transform(){
        Robot robot = new Robot(getId());
        robot.setPosX(getPosX());
        robot.setPosY(getPosY());
        robot.setStatus(getStatus().getCode());
        robot.setStatusInfo(getStatus().getInfo());
        return robot;
    }
}
