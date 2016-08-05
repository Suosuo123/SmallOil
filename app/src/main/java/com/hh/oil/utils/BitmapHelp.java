package com.hh.oil.utils;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import com.hh.oil.R;
import com.lidroid.xutils.BitmapUtils;

public class BitmapHelp {

	private static final String dirName = "myframe" + File.separator + "cache" + File.separator;
	private static BitmapUtils bitmapUtils;
	private static BitmapUtils bitmapUtils_headIcon;

	private BitmapHelp() {
	}

	/**
	 * BitmapUtils不是单例的 根据需要重载多个获取实例的方法
	 */
	public static BitmapUtils getDefaultBitmapUtils(Context context) {
		if (bitmapUtils == null) {
			bitmapUtils = getBitmapUtils(context, R.drawable.icon_default_big);
		}
		return bitmapUtils;
	}

	public static BitmapUtils getDefaultHeadIconBitmapUtils(Context context) {
		if (bitmapUtils_headIcon == null) {
			bitmapUtils_headIcon = getBitmapUtils(context, R.drawable.icon_default_small);
		}
		return bitmapUtils_headIcon;
	}

	public static BitmapUtils getBitmapUtils(Context context, int resId) {
		BitmapUtils bitmapUtils = new BitmapUtils(context, getDiskCacheDir(context));
		bitmapUtils.configDefaultLoadFailedImage(resId);
		bitmapUtils.configDefaultLoadingImage(resId);
		bitmapUtils.configDiskCacheEnabled(true);
		bitmapUtils.configMemoryCacheEnabled(true);
		bitmapUtils.configDefaultBitmapConfig(Bitmap.Config.RGB_565);
		return bitmapUtils;
	}

	public static String getDiskCacheDir(Context context) {
		String cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File externalCacheDir = context.getExternalCacheDir();
			if (externalCacheDir != null) {
				cachePath = externalCacheDir.getPath();
			}
		}
		if (cachePath == null) {
			File cacheDir = Environment.getExternalStorageDirectory();
			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.toString();
			}
		}
		if (cachePath == null) {
			File cacheDir = new File("/storage/sdcard0/");

			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.getPath();
			}
		}
		if (cachePath == null) {
			File cacheDir = context.getCacheDir();
			if (cacheDir != null && cacheDir.exists()) {
				cachePath = cacheDir.getPath();
			}
		}
		return cachePath + File.separator + dirName;
	}

	public static String formatImgUrl(String iconUrl) {
		if (!TextUtils.isEmpty(iconUrl) && iconUrl.contains(".jpg")) {
			iconUrl = iconUrl.split(".jpg")[0] + ".jpg";
		}
		return iconUrl;
	}
}
