package com.njrobot.huangyouqiang.redevicemanager;

import android.app.Application;
import android.util.Log;

import com.mobvoi.android.common.MobvoiApiManager;
import com.mobvoi.android.common.NoAvailableServiceException;

/**
 * Created by huangyouqiang on 2016/5/20.
 */
public class WearApplication extends Application {

	private static final String TAG = WearApplication.class.getSimpleName();
	@Override
	public void onCreate() {
		super.onCreate();
		if (!MobvoiApiManager.getInstance().isInitialized()) {
			try {
				MobvoiApiManager.getInstance().adaptService(this);
			} catch (NoAvailableServiceException e) {
				Log.e(TAG, "no avaliable service.", e);
				return;
			}
		}
	}
}
