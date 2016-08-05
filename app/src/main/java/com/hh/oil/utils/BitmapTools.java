package com.hh.oil.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class BitmapTools {

	public BitmapTools() {
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {
		if (null != bitmap) {
			int width = bitmap.getWidth();
			int height = bitmap.getHeight();
			float roundPx;
			float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
			if (width <= height) {
				roundPx = width / 2;
				top = 0;
				bottom = width;
				left = 0;
				right = width;
				height = width;
				dst_left = 0;
				dst_top = 0;
				dst_right = width;
				dst_bottom = width;
			} else {
				roundPx = height / 2;
				float clip = (width - height) / 2;
				left = clip;
				right = width - clip;
				top = 0;
				bottom = height;
				width = height;
				dst_left = 0;
				dst_top = 0;
				dst_right = height;
				dst_bottom = height;
			}

			Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(output);

			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
			final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
			final RectF rectF = new RectF(dst);

			paint.setAntiAlias(true);

			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, src, dst, paint);
			return output;
		}
		return null;
	}

	/**
	 * 压缩图片
	 * 
	 * @param data
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap decodeBitmap(byte[] data, int width, int height) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, options);
		options.inSampleSize = calculateInSampleSize(options, width, height);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}

	/**
	 * 计算压缩图片的比例
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		int inSampleSize = 1;
		final int height = options.outHeight;
		final int width = options.outWidth;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 将图片存入sdcard
	 * 
	 * @param bitmap
	 * @param path
	 * @param imageName
	 * @param mActivity
	 * @return
	 */
	@SuppressWarnings("resource")
	public static boolean compressBitmap(Bitmap bitmap, String path, String imageName, Context mActivity) {
		File file = null;
		String real_path = "";
		try {
			if (SDcardTools.getInstance().hasSdcard()) {
				real_path = SDcardTools.getInstance().getExtPath()
						+ (path != null && path.startsWith("/") ? path : "/" + path);
			} else {
				real_path = SDcardTools.getInstance().getPackagePath(mActivity)
						+ (path != null && path.startsWith("/") ? path : "/" + path);
			}
			file = new File(real_path, imageName);
			if (!file.exists()) {
				File file2 = new File(real_path + "/");
				file2.mkdirs();
			}
			file.createNewFile();
			FileOutputStream fos = null;
			if (SDcardTools.getInstance().hasSdcard()) {
				fos = new FileOutputStream(file);
			} else {
				fos = mActivity.openFileOutput(imageName, Context.MODE_PRIVATE);
			}

			if (imageName != null && (imageName.contains(".png") || imageName.contains(".PNG"))) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
			} else {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
			}
			fos.flush();
			if (fos != null) {
				fos.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 从文件中拿图片
	 * 
	 * @param mActivity
	 * @param imageName
	 * @param path
	 * @return
	 */
	public static Bitmap getBitmapFromFile(Context mActivity, String imageName, String path) {
		Bitmap bitmap = null;
		if (imageName != null) {
			File file = null;
			String real_path = "";
			try {
				if (SDcardTools.getInstance().hasSdcard()) {
					real_path = SDcardTools.getInstance().getExtPath()
							+ (path != null && path.startsWith("/") ? path : "/" + path);
				} else {
					real_path = SDcardTools.getInstance().getPackagePath(mActivity)
							+ (path != null && path.startsWith("/") ? path : "/" + path);
				}
				file = new File(real_path, imageName);
				if (file.exists())
					bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
				bitmap = null;
			}
		}
		return bitmap;
	}
}
