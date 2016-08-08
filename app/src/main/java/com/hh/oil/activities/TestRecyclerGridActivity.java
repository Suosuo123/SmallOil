package com.hh.oil.activities;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.View;
import android.widget.Toast;

import com.hh.oil.R;
import com.hh.oil.adapter.RecyclerListAdapter;
import com.hh.oil.entity.App;
import com.hh.oil.utils.log.LogUtils;
import com.hh.oil.widget.RefreshLayoutForRecycler;
import com.hh.oil.widget.RefreshLayoutForRecycler.OnLoadListener;
import com.hh.oil.widget.familiarRecyclerview.FamiliarRecyclerView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_recycler_grid)
@SuppressLint("NewApi")
public class TestRecyclerGridActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
		FamiliarRecyclerView.OnItemClickListener {
	@ViewInject(R.id.mSwipeRefreshLayout)
	private RefreshLayoutForRecycler mSwipeLayout;

	@ViewInject(R.id.mRecyclerView)
	private FamiliarRecyclerView mRecyclerView;

	private RecyclerListAdapter mAdapter;

	@Override
	protected void initView() {
		super.initView();
		setActionBarTitle("recycler_grid");

		mSwipeLayout.setColorSchemeResources(android.R.color.holo_orange_dark, android.R.color.holo_green_dark,
				android.R.color.holo_blue_dark, android.R.color.holo_red_dark);
		mSwipeLayout.setOnRefreshListener(this);
		mSwipeLayout.setOnLoadListener(new OnLoadListener() {
			@Override
			public void onLoad() {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						setData();
					}
				}, 2000);
			}

		});

		mRecyclerView.setOnItemClickListener(this);

		// ItemAnimator
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		// head view
		mRecyclerView.addHeaderView(HeaderAndFooterViewUtil.getHeadView(this, true,
				getResources().getColor(R.color.main_color), "Head View 1"));

		// mRecyclerView.addFooterView(mFooterLoadMoreView);

		mAdapter = new RecyclerListAdapter(mActivity);
		mRecyclerView.setAdapter(mAdapter);

	}

	@Override
	protected void initData() {
		super.initData();

		setData();

		// initCallBack();
		// netWorkGetData();
	}

	private void setData() {

		List<App> list = getSimulationData();

		if (list.size() < 5) {
			mSwipeLayout.setLoadEnable(false);
		}

		if (page_no == 1) {
			mAdapter.clearData();
			mAdapter.refresh(list);
		} else {
			mAdapter.bindData(list);
		}

		mSwipeLayout.setRefreshing(false);
		mSwipeLayout.setLoading(false);

		page_no++;
	}

	/**
	 * 获取模拟数据
	 */
	private List<App> getSimulationData() {
		List<App> list = new ArrayList<App>();
		String[] mImageResStrings = null;
		if (page_no == 1) {
			mImageResStrings = getResources().getStringArray(R.array.img_src_data);
		} else if (page_no == 2) {
			mImageResStrings = getResources().getStringArray(R.array.img_src_data1);
		} else if (page_no == 3) {
			mImageResStrings = getResources().getStringArray(R.array.img_src_data2);
		} else {
		}

		if (null != mImageResStrings) {
			LogUtils.d("=====================" + page_no + "==========" + mImageResStrings.length);

			for (int i = 0; i < mImageResStrings.length; i++) {
				App app = new App();
				app.setIcon(mImageResStrings[i]);
				list.add(app);
			}
		} else {
			LogUtils.d("=====================" + page_no + "==========" + "无更多数据");
		}

		return list;

	}

	@Override
	public void onRefresh() {
		mSwipeLayout.setLoadEnable(true);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				page_no = 1;
				setData();
			}
		}, 2000);
	}

	@Override
	public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
		Toast.makeText(getApplicationContext(), "onItemClick = " + position, Toast.LENGTH_SHORT).show();
	}
}
