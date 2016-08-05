package com.hh.oil.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hh.oil.MainApplication;
import com.hh.oil.utils.CommonUtils;

public class RoundCornerImageView extends ImageView {
	int pixels;
	Context context;

	public RoundCornerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		pixels = CommonUtils.dip2pixel(context, 6);
	}

	// @Override
	// public void setImageDrawable(Drawable drawable) {
	// }

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(getRoundedCornerBitmap(bm, pixels));
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		return getRoundedCornerBitmap(bitmap, CommonUtils.dip2pixel(MainApplication.getInstance(), 3));
	}
}
