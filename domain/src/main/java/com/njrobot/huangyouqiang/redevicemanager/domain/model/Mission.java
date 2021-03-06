package com.njrobot.huangyouqiang.redevicemanager.domain.model;


/**
 * @author huangyouqiang
 * @date 2016/7/29
 */
public class Mission {
    private int id;
    private int userId;
    private int priority;
    private String destinationSite;

    public Mission(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDestinationSite() {
        return destinationSite;
    }

    public void setDestinationSite(String destinationSite) {
        this.destinationSite = destinationSite;
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append("********* Mission Info ********\n");
        sb.append("id : "+this.getId()+"\n");
        sb.append("userId :"+this.getUserId()+"\n");
        sb.append("priority :"+this.getPriority()+"\n");
        sb.append("site name :"+this.getDestinationSite()+"\n");
        sb.append("*********************************");
        return sb.toString();
    }
}
