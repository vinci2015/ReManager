package com.njrobot.huangyouqiang.redevicemanager;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author huangyouqiang
 * @date 2016/5/5
 */
public class CallButton extends View {
	private static final  String TAG = CallButton.class.getSimpleName();
	private float innerRadius = 47.5f;
	private float outerRadius = 66f;
	private String text = "呼叫";
	private Paint innerPaint;
	private Paint outerPaint;
	private Paint innerBehindPaint;
	private Paint textPaint;
	private Paint scanPaint ;
	private BlurMaskFilter blurMaskFilterOut;
	private BlurMaskFilter blurMaskFilterSolid;
	private Shader mSweepGradient;
	private boolean isInCalling = false;//是否在呼叫
	private boolean isFoundRobot = false;//是否找到了robot
	private float radiusStep = 1f;//圆形涟漪动画中每次重绘时半径的增量
	private float starRadiusStep = 1f;//圆形星星动画中每次重绘时半径的增量
	float starX;//星星X坐标
	float starY;//星星Y坐标
	double randomRadius;//用于在圆环区域随机选定一个点，用半径和角度随机确定一个点
	double randomAngle;
	private float angelStep = 0f;//扫描动画中每次重绘时旋转的角度
	private boolean isMagnify = true;//是否在放大中

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if(radiusStep < 50) {
				radiusStep += 1;
			}else {
				radiusStep = 0;
			}
			if(starRadiusStep<7&&isMagnify){
				starRadiusStep += 0.3;
			}else{
				isMagnify = false;
				starRadiusStep -=0.3;
				if(starRadiusStep<0) {
					isMagnify = true;
					randomRadius = 50 * Math.random() + 66;
					randomAngle = Math.PI * 2 * Math.random();
					starX = (float) (randomRadius * Math.cos(randomAngle));
					starY = (float) (randomRadius * Math.sin(randomAngle));
					starRadiusStep = 0;
				}
			}
			if(isFoundRobot){
				angelStep = (angelStep+3f)%360;
			}
			invalidate();
		}
	};
	public CallButton(Context context) {
		//super(context);
		this(context,null);
	}

	public CallButton(Context context, AttributeSet attrs) {
		//super(context,attrs);
		super(context,attrs);
		setLayerType(LAYER_TYPE_SOFTWARE,null);
		innerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		innerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		innerPaint.setColor(Color.rgb(41,154,224));
		innerPaint.setMaskFilter(new EmbossMaskFilter(new float[]{1,1,1},0.4f,6,5f));
		outerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		outerPaint.setStrokeWidth(2f);

		innerBehindPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		innerBehindPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		innerBehindPaint.setColor(Color.rgb(64,64,64));
		innerBehindPaint.setMaskFilter(new BlurMaskFilter(5f,BlurMaskFilter.Blur.INNER));
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(20f);

		randomRadius = 50*Math.random()+66;
		randomAngle = Math.PI*2*Math.random();
		starX = (float) (randomRadius*Math.cos(randomAngle));
		starY = (float) (randomRadius*Math.sin(randomAngle));

		scanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

		blurMaskFilterOut = new BlurMaskFilter(10,BlurMaskFilter.Blur.OUTER);
		blurMaskFilterSolid = new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID);
		mSweepGradient = new SweepGradient(0, 0,new int[]{Color.argb(10,109, 185, 247),Color.argb(210,109,185,247)},new float[]{0,70/360f});
	}


	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawARGB(0,0,0,0);
		canvas.translate(this.getWidth()/2,this.getHeight()/2);
		if(!isInCalling) {
			outerPaint.setColor(Color.argb(89, 109, 185, 247));
			outerPaint.setMaskFilter(blurMaskFilterOut);
		}else {
			outerPaint.setColor(Color.rgb(41,154,228));
			outerPaint.setMaskFilter(blurMaskFilterSolid);
		}
		canvas.save();
		if(isFoundRobot) {
			scanPaint.setShader(mSweepGradient);
			canvas.rotate(angelStep);
			canvas.drawArc(new RectF(canvas.getClipBounds()),0,70,true,scanPaint);
		//	canvas.drawCircle(0,0,300,scanPaint);
		}
		canvas.restore();
		canvas.drawCircle(0,0,outerRadius,outerPaint);
		canvas.drawCircle(0,0,innerRadius,innerBehindPaint);
		if(!isInCalling) {
			canvas.drawCircle(0, 0, innerRadius, innerPaint);
		}
		Paint.FontMetricsInt fontMetric = textPaint.getFontMetricsInt();
		int baseLine = -(fontMetric.top+fontMetric.bottom)/2;
		textPaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText(text,0,baseLine,textPaint);
		if(isInCalling){
			if(!isFoundRobot) {
				//ripple
				Paint ripplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
				ripplePaint.setColor(Color.rgb(109, 185, 247));
				ripplePaint.setStyle(Paint.Style.STROKE);
				ripplePaint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.SOLID));
				ripplePaint.setStrokeWidth(2);
				canvas.drawCircle(0, 0, outerRadius + radiusStep, ripplePaint);
			}
			//shine star
			Paint starPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			starPaint.setColor(Color.rgb(109,185,247));
			starPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			starPaint.setMaskFilter(new BlurMaskFilter(5,BlurMaskFilter.Blur.SOLID));

			canvas.drawCircle(starX,starY,starRadiusStep,starPaint);
			this.postDelayed(runnable,20);
		}
	}
	public void rebuildView(boolean mIsInCalling,boolean mIsFoundRobot,String s){
		if(mIsInCalling){
			isInCalling = true;
			if(mIsFoundRobot){//status 3
				this.isFoundRobot = true;
				setText(s+"M");
			}else{//status 2
				setText("呼叫中");
			}
		}
	}
	public void resetView(){
		Log.i(TAG,"resetView()");
		isInCalling = false;
		isFoundRobot = false;
		setText("呼叫");
	}
	public void doCall(){
		if(!isInCalling) {
			this.isInCalling = true;
			this.setText("呼叫中");
		}
	}
	public void onFindRobot(){
		if(isInCalling){
			this.isFoundRobot = true;
			this.invalidate();
			Log.i("callButton","isFoundRobot:"+isFoundRobot());
		}
	}
	public void setText(String s){
		this.text = s;
		this.invalidate();
	}
	public boolean isInCalling(){
		return  isInCalling;
	}

	public boolean isFoundRobot() {
		return isFoundRobot;
	}
}
