package com.njrobot.huangyouqiang.redevicemanager.presentation.DI;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by huangyouqiang on 2016/8/10.
 */
@Scope
@Retention(RUNTIME)
public @interface PerService {}