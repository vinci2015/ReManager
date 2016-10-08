package com.njrobot.huangyouqiang.redevicemanager.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.njrobot.huangyouqiang.redevicemanager.data.model.WatchModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author huangyouqiang
 * @date 2016/5/3
 */
public class LocalSavingManager {
	private static volatile LocalSavingManager instance = null;
	private SharedPreferences localShare;

	private LocalSavingManager(Context context){
		localShare = context.getSharedPreferences(Constant.FILE_NAME, Context.MODE_PRIVATE);
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
	/*public void setDistance(String distance){
		SharedPreferences.Editor editor = localShare.edit();
		editor.putString(Constant.ROBOT_DISTANCE,distance);
		editor.apply();
	}
	public String getDistance(){
		return localShare.getString(Constant.ROBOT_DISTANCE,"");
	}*/
	public void setServerIP(String serverIP){
		SharedPreferences.Editor editor = localShare.edit();
		editor.putString(Constant.SERVER_IP,serverIP);
		editor.apply();
	}
	public String getServerIP(){
		return localShare.getString(Constant.SERVER_IP,"");
	}
	public void setWatch(WatchModel watchModel){
			SharedPreferences.Editor editor = localShare.edit();
			editor.putString(watchModel.getId(), objectToString(watchModel));
			editor.apply();
	}
	public WatchModel getWatch(String id){
		String result = localShare.getString(id,"");
		if(result.equals("")){
			return null;
		}else {
			return stringToObject(result);
		}
	}
	public void addWatch(WatchModel watchModel){
		if(!localShare.contains(watchModel.getId())){
			setWatch(watchModel);
		}
	}
	private String objectToString(Object object) {
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
				baos.close();
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private <T> T stringToObject(String result) {
			byte[] buffer = Base64.decode(result, Base64.DEFAULT);
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(bais);
				return (T) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} finally {
				try {
					bais.close();
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
