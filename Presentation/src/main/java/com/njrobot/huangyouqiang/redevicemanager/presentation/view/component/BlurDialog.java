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
				dismiss();
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

		return fastblur(overlay, (int) radius);
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
	/**
	 * 模糊处理
	 * @param sentBitmap
	 * @param radius
	 * @return
	 */
	private static Bitmap fastblur(Bitmap sentBitmap, int radius) {

		// Stack Blur v1.0 from
		// http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
		// Java Author: Mario Klingemann <mario at quasimondo.com>
		// http://incubator.quasimondo.com

		// created Feburary 29, 2004
		// Android port : Yahel Bouaziz <yahel at kayenko.com>
		// http://www.kayenko.com
		// ported april 5th, 2012

		// This is a compromise between Gaussian Blur and Box blur
		// It creates much better looking blurs than Box Blur, but is
		// 7x faster than my Gaussian Blur implementation.

		// I called it Stack Blur because this describes best how this
		// filter works internally: it creates a kind of moving stack
		// of colors whilst scanning through the image. Thereby it
		// just has to add one new block of color to the right side
		// of the stack and remove the leftmost color. The remaining
		// colors on the topmost layer of the stack are either added on
		// or reduced by one, depending on if they are on the right or
		// on the left side of the stack.

		// If you are using this algorithm in your code please add
		// the following line:
		// Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

		if (radius < 1) {
			return (null);
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int[] pix = new int[w * h];
		// Log.e("pix", w + " " + h + " " + pix.length);
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);

		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;

		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];

		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}

		yw = yi = 0;

		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;

		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;

			for (x = 0; x < w; x++) {

				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];

				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;

				sir = stack[i + radius];

				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];

				rbs = r1 - Math.abs(i);

				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;

				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}

				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				// Preserve alpha channel: ( 0xff000000 & pix[yi] )
				pix[yi] = ( 0xff000000 & pix[yi] ) | ( dv[rsum] << 16 ) | ( dv[gsum] << 8 ) | dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];

				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi += w;
			}
		}

		//Log.e("pix", w + " " + h + " " + pix.length);
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);

		return (bitmap);
	}
}
