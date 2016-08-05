package com.njrobot.huangyouqiang.redevicemanager.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.njrobot.huangyouqiang.redevicemanager.presentation.Constant;
import com.njrobot.huangyouqiang.redevicemanager.presentation.model.WatchModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by huangyouqiang on 2016/5/3.
 */
public class LocalSavingManager {
	private static volatile LocalSavingManager instance = null;
	private Context context;
	private SharedPreferences localShare;

	private LocalSavingManager(Context context){
		localShare = context.getSharedPreferences(Constant.FILE_NAME,context.MODE_PRIVATE);
		this.context  = context;
	}

	public static LocalSavingManager getInstance(Context context){
		if(instance == null){
			synchronized (LocalSavingManager.class){
				if(instance == null){
					instance = new LocalSavingManager(context);
				}
			}
		}
		return instance;
	}
	public void setDistance(String distance){
		SharedPreferences.Editor editor = localShare.edit();
		editor.putString(Constant.ROBOT_DISTANCE,distance);
		editor.commit();
	}
	public String getDistance(){
		return localShare.getString(Constant.ROBOT_DISTANCE,"");
	}
	public void setServerIP(String serverIP){
		SharedPreferences.Editor editor = localShare.edit();
		editor.putString(Constant.SERVER_IP,serverIP);
		editor.commit();
	}
	public String getServerIP(){
		return localShare.getString(Constant.SERVER_IP,"");
	}
	public void setWatch(WatchModel watchModel){
			SharedPreferences.Editor editor = localShare.edit();
			editor.putString(watchModel.getId(), objectToString(watchModel));
			editor.commit();
	}
	public WatchModel getWatch(String id){
		String result = localShare.getString(id,"");
		if(result == ""){
			return null;
		}else {
			WatchModel watchModel = stringToObject(result, WatchModel.class);
			return watchModel;
		}
	}
	public void addWatch(WatchModel watchModel){
		if(!localShare.contains(watchModel.getId())){
			setWatch(watchModel);
		}
	}
	public String objectToString(Object object) {
		String result = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(baos);
			out.writeObject(object);
		result = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public <T> T stringToObject(String result, Class<T> clazz) {
			byte[] buffer = Base64.decode(result, Base64.DEFAULT);
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(bais);
				T t = (T) ois.readObject();
				return t;
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bais != null) {
						bais.close();
					}
					if (ois != null) {
						ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return null;
	}
}
