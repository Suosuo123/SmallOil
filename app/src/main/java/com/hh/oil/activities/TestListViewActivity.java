package com.hh.oil.activities;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.hh.oil.R;
import com.hh.oil.adapter.ListViewAdapter;
import com.hh.oil.entity.App;
import com.hh.oil.widget.RefreshLayout;
import com.hh.oil.widget.RefreshLayout.OnLoadListener;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_listview)
@SuppressLint("NewApi")
public class TestListViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnScrollListener {
	@ViewInject(R.id.swipe_container)
	private RefreshLayout mSwipeLayout;

	@ViewInject(R.id.listview)
	private ListView mListView;

	private ListViewAdapter madAdapter;

	@Override
	protected void initView() {
		super.initView();
		setActionBarTitle("ListView");

		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setColorSchemeResources(android.R.color.holo_orange_dark, android.R.color.holo_green_dark,
				android.R.color.holo_blue_dark, android.R.color.holo_red_dark);

		// 加载监听器
		mSwipeLayout.setOnLoadListener(new OnLoadListener() {

			@Override
			public void onLoad() {

				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						mSwipeLayout.setLoading(false);
					}
				}, 3000);

			}
		});

		madAdapter = new ListViewAdapter(mActivity);
		mListView.setAdapter(madAdapter);

		mListView.setOnScrollListener(new PauseOnScrollListener(madAdapter.mBitmapUtils, false, true, this));
	}

	@Override
	protected void initData() {
		super.initData();

		setData();

		// initCallBack();
		// netWorkGetData();
	}

	private void setData() {
		madAdapter.clearData();

		madAdapter.bindData(getSimulationData());

		mSwipeLayout.setRefreshing(false);
	}

	/**
	 * 获取模拟数据
	 */
	private List<App> getSimulationData() {
		String[] mImageResStrings = getResources().getStringArray(R.array.img_src_data);
		List<App> list = new ArrayList<App>();
		for (int i = 0; i < mImageResStrings.length; i++) {
			App app = new App();
			app.setIcon(mImageResStrings[i]);
			list.add(app);
		}
		return list;
	}

	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				setData();
			}
		}, 2000);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (madAdapter != null && madAdapter.mBitmapUtils != null) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_IDLE:
				madAdapter.mBitmapUtils.resume();
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				madAdapter.mBitmapUtils.pause();
				break;
			case OnScrollListener.SCROLL_STATE_FLING:
				madAdapter.mBitmapUtils.pause();
				break;
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

	// private CallBack<AppList> mCallBack;
	//
	// private void initCallBack() {
	// mCallBack = new CallBack<AppList>(AppList.class, mActivity, "正在加载...") {
	//
	// @Override
	// public void doSuccess(AppList entity) {
	// mSwipeLayout.setRefreshing(false);
	//
	// if (null != entity) {
	// if (page_no == 1) {
	// madAdapter.clearData();
	// }
	//
	// madAdapter.bindData(entity.getDataList());
	//
	// page_no++;
	// }
	// }
	//
	// };
	// }

	// private void netWorkGetData() {
	//
	// Map<String, String> map = new HashMap<String, String>();
	// Network.postNetwork(ContantsData.DEFAULT_HOST, map,
	// RequestParamsPostion.PARAMS_POSITION_BODY, mCallBack);
	//
	// }
}
