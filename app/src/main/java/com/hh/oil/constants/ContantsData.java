package com.hh.oil.constants;

import com.hh.oil.BuildConfig;

public class ContantsData {

	public static final String SP_FILE_NAME = "myframe";

	public static final String SDCARD_FILE_NAME = "myframe";

	public static final String DEFAULT_HOST;

	static {
		if (BuildConfig.DEBUG) {
			DEFAULT_HOST = "http://hw.156.cn/game/orderXun.php?method=recommend&page=1";// 测试环境
		} else {
			DEFAULT_HOST = "https://g.corp.gome.com.cn/rpt18";// 正式环境
		}
	}

	public static final String METHOD_RECOMMEND_LIST = "recommend";// 推荐列表
}
