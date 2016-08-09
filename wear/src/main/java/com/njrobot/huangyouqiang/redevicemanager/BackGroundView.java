package com.njrobot.huangyouqiang.redevicemanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huangyouqiang on 2016/5/4.
 */
public class BackGroundView extends View {
	private static final String TEXT_SITE = "站 点 ";
	private static final String TEXT_TIME = "时 间 ";
	private String site = "";
	private String time = "";
	private Path mArc_site;
	private Path mArc_time;
	private Path mArc_site_value;
	private Path mArc_time_value;
	private Paint mPaintText;
	private Paint mPaintTextValue;
	private RectF oval;

	public BackGroundView(Context context) {
		this(context,null);
	}

	public BackGroundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mArc_site = new Path();
		mArc_time = new Path();
		mArc_site_value = new Path();
		mArc_time_value = new Path();
		oval = new RectF(0,0,320,320);
		mArc_site.addArc(oval, -150, 20);
		mArc_time.addArc(oval,-70,20);
		mArc_site_value.addArc(oval,-130,30);
		mArc_time_value.addArc(oval,-50,30);
		mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaintText.setColor(Color.WHITE);
		mPaintText.setTextSize(18f);
		mPaintTextValue = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintTextValue.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaintTextValue.setColor(Color.WHITE);
		mPaintTextValue.setTextSize(28f);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		reFreshTime();
		canvas.drawTextOnPath(TEXT_SITE, mArc_site, 0, 30, mPaintText);//文字沿弧线向下偏移30px
		canvas.drawTextOnPath(TEXT_TIME, mArc_time, 0, 30, mPaintText);
		canvas.drawTextOnPath(site, mArc_site_value,0,30,mPaintTextValue);
		canvas.drawTextOnPath(time, mArc_time_value,0,30,mPaintTextValue);
	}

	public void setSite(String s){
		Log.i("backGround","change site :"+s);
		if(s.length()>=9) {
			this.site = s.replaceAll(".{1}(?!$)", "$0 ").substring(0, 8);
		}else {
			this.site = s.replaceAll(".{1}(?!$)", "$0 ");
		}
		this.invalidate();
	}
	public void reFreshTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		String dateStr = simpleDateFormat.format(date);
		this.time = dateStr;
		this.invalidate();
	}
}
