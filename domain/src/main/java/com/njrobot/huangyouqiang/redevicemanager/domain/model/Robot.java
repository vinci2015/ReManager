package com.njrobot.huangyouqiang.redevicemanager.domain.model;

/**
 * Created by huangyouqiang on 2016/8/8.
 */
public class Robot {
    private int id;
    private double posX;
    private double posY;
    private int status;
    private String statusInfo;

    public Robot(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append("********* Robot Info ********\n");
        sb.append("id : "+this.getId()+"\n");
        sb.append("posX :"+this.getPosX()+"\n");
        sb.append("posY :"+this.getPosY()+"\n");
        sb.append("status:"+this.getStatus()+"\n");
        sb.append("status info :"+this.getStatusInfo()+"\n");
        sb.append("*********************************");
        return sb.toString();
    }
}
