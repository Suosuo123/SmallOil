package com.hh.oil.utils;

import java.io.FileInputStream;
import java.text.DecimalFormat;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;

import com.hh.oil.constants.ContantsData;

public class SDcardTools {

	private static SDcardTools instance;
	public static int flag = 0;

	private SDcardTools() {
	}

	public static SDcardTools getInstance() {
		if (instance == null) {
			instance = new SDcardTools();
		}
		return instance;
	}

	public boolean hasSdcard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public String getExtPath() {
		if (hasSdcard()) {
			return Environment.getExternalStorageDirectory().getPath();
		}
		return null;
	}

	public String getLocalFile(String url) {
		String localFile = null;
		if (hasSdcard()) {
			// return Environment.getExternalStorageDirectory().getPath();
			if (url.contains("?")) {
				localFile = Environment.getExternalStorageDirectory().getPath() + "/" + ContantsData.SDCARD_FILE_NAME
						+ "/" + url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"));
			} else {
				localFile = Environment.getExternalStorageDirectory().getPath() + "/" + ContantsData.SDCARD_FILE_NAME
						+ "/" + url.substring(url.lastIndexOf("/") + 1);
			}
		}
		return localFile;
	}

	public String getPackagePath(Context mActivity) {
		return mActivity.getFilesDir().toString();
	}

	public String getImageName(String url) {
		String imageName = "";
		if (url != null) {
			imageName = url.substring(url.lastIndexOf("/") + 1);
		}
		return imageName;
	}

	/**
	 * 文件大小单位转换
	 * 
	 * @param length
	 * @return
	 */
	public static String getFileLengthM(long length) {
		DecimalFormat df = new DecimalFormat("0.0");
		float sizeM = length / 1024 / 1024;
		String sizeM_format = df.format(sizeM);
		if (sizeM_format.equals("0.0")) {
			float sizeK = length / 1024;
			String sizeK_format = df.format(sizeK);
			if (sizeK_format.equals("0.0")) {
				return length + "B";
			} else {
				return sizeK_format + "K";
			}
		} else {
			return sizeM_format + "M";
		}
	}

	/**
	 * 图片转换成二进制流
	 * 
	 * @param imgUrl
	 * @return
	 */
	public static String encodeImage(String imgUrl) {
		try {
			FileInputStream fis = new FileInputStream(imgUrl);
			byte[] rs = new byte[fis.available()];
			fis.read(rs);
			fis.close();
			return encode(rs);
		} catch (Exception e) {
		}
		return null;
	}

	public static String encode(byte[] bytes) {
		return Base64.encodeToString(bytes, 0);
	}
}
