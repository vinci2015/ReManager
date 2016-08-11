package com.njrobot.huangyouqiang.redevicemanager.presentation.view.component;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.njrobot.huangyouqiang.redevicemanager.presentation.R;
import com.njrobot.huangyouqiang.redevicemanager.data.utils.PictureUtils;


/**
 * Created by huangyouqiang on 2016/5/3.
 */
public class BlurDialog extends Dialog {
	private ImageView imageView;
	private Context context;
	private OnConfirmClickListener onConfirmClickListener;
	private EditText edtContent;
	private boolean needBlur = true;

	/**
	 *
	 * @param context 上下文
	 * @param themeResId style id
	 * @param mNeedBlur 是否需要背景模糊
	 * @param tittle 内容名
     * @param contentHint 默认填充的内容
     */
	public BlurDialog(final Context context, int themeResId, boolean mNeedBlur, String tittle, String contentHint) {
		super(context, themeResId);
		this.context = context;
		final LayoutInflater inflater = LayoutInflater.from(this.context);
		View view = inflater.inflate(R.layout.alert_dialog,null);
		setContentView(view);
		((TextView)(view.findViewById(R.id.tv_info))).setText(tittle);
		edtContent = ((EditText)(view.findViewById(R.id.edt_info)));
		edtContent.setText(contentHint);
		if(tittle.equals("站点")){
			edtContent.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final PopupMenu popupMenu = new PopupMenu(context,v);
					popupMenu.getMenuInflater().inflate(R.menu.menu_site_list,popupMenu.getMenu());
					popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {
							edtContent.setText(item.getTitle().toString());
							popupMenu.dismiss();
							return false;
						}
					});
					popupMenu.show();
				}
			});
		}
		this.needBlur = mNeedBlur;
		if(needBlur) {
			setBlurBackground(this.context);
		}
		setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				if(needBlur){
					removeBackground();
				}
			}
		});
		(view.findViewById(R.id.btn_confirm))
				.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(needBlur){
					removeBackground();
				}
				onConfirmClickListener.onConfirm(v,edtContent.getText().toString());
			}
		});
	}
	private void setBlurBackground(Context context){
		imageView = new ImageView(context);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		View root = ((Activity)context).getWindow().getDecorView();
		root.setDrawingCacheEnabled(true);
		Bitmap bmp = root.getDrawingCache();
		imageView.setImageDrawable(new BitmapDrawable(context.getResources(),blur(bmp, root)));
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT);
		((Activity)context).getWindow().addContentView(imageView,layoutParams);
	}
	private Bitmap blur(Bitmap bkg, View view) {
		float scaleFactor = 20;
		float radius = 2;
		/*
		 * if (downScale.isChecked()) { scaleFactor = 8; radius = 2; }
		 */
		Bitmap overlay = Bitmap.createBitmap(
						(int) (view.getMeasuredWidth() / scaleFactor),
						(int) (view.getMeasuredHeight() / scaleFactor),
						Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(overlay);
		canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()
						/ scaleFactor);
		canvas.scale(1 / scaleFactor, 1 / scaleFactor);
		Paint paint = new Paint();
		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
		canvas.drawBitmap(bkg, 0, 0, paint);

		return PictureUtils.fastblur(overlay, (int) radius);
	}

	private void removeBackground(){
		ViewGroup group = (ViewGroup) imageView.getParent();
		if(group != null) {
			group.removeView(imageView);
			imageView = null;
		}
	}

	public void setOnConfirmClickListener(OnConfirmClickListener listener){
		this.onConfirmClickListener = listener;
	}
	public interface OnConfirmClickListener {
		void onConfirm(View v, String content);
	}
}
