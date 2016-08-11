package com.njrobot.huangyouqiang.redevicemanager.data.model;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.PointInfoEntity;

/**
 * Created by huangyouqiang on 2016/8/8.
 */
public class PointInfo {
    private String name;
    private double posX;
    private double posY;

    public PointInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public PointInfo transform(PointInfoEntity entity){
        PointInfo pointInfo = null;
        if(entity != null){
            pointInfo = new PointInfo(entity.getName());
            pointInfo.setPosX(entity.getPos_x());
            pointInfo.setPosY(entity.getPos_y());
        }
        return pointInfo;
    }
    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append("********* Point Info ********\n");
        sb.append("name : "+this.getName()+"\n");
        sb.append("posX :"+this.getPosX()+"\n");
        sb.append("posY:"+this.getPosY()+"\n");
        sb.append("*********************************");
        return sb.toString();
    }
}
