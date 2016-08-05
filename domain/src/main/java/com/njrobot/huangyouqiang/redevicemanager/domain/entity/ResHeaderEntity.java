package com.njrobot.huangyouqiang.redevicemanager.domain.entity;

/**
 * Created by huangyouqiang on 2016/8/4.
 */
public class ResHeaderEntity extends HeaderEntity {
    private ParamsBeanEntity error_code;

    public ResHeaderEntity(ParamsBeanEntity error_code) {
        super();
        this.error_code = error_code;
    }

    public ParamsBeanEntity getError_code() {
        return error_code;
    }
}
