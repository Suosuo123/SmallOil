package com.hh.oil.fragments;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

import com.hh.oil.R;
import com.hh.oil.widget.waterDrop.WaterDropListView;
import com.hh.oil.widget.waterDrop.WaterDropListView.IWaterDropListViewListener;
import com.lidroid.xutils.view.annotation.ViewInject;

public class TestWaterFreshFragment extends BaseFragment implements IWaterDropListViewListener {

	public static TestWaterFreshFragment newInstance() {
		TestWaterFreshFragment f = new TestWaterFreshFragment();
		Bundle b = new Bundle();
		f.setArguments(b);
		return f;
	}

	@ViewInject(R.id.waterdrop_listview)
	private WaterDropListView waterDropListView;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				waterDropListView.stopRefresh();
				break;
			case 2:
				waterDropListView.stopLoadMore();
				break;
			}

		}
	};

	@Override
	protected int setLayout() {
		return R.layout.fragment_water_refresh;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		waterDropListView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_expandable_list_item_1, getData()));
		waterDropListView.setWaterDropListViewListener(this);
		waterDropListView.setPullLoadEnable(true);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendEmptyMessage(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onLoadMore() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendEmptyMessage(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();
		data.add("To see a world in a grain of sand,");
		data.add("And a heaven in a wild flower,");
		data.add("Hold infinity in the palm of your hand,");
		data.add("And eternity in an hour.");
		data.add("To see a world in a grain of sand,");
		data.add("And a heaven in a wild flower,");
		data.add("Hold infinity in the palm of your hand,");
		data.add("And eternity in an hour.");
		data.add("To see a world in a grain of sand,");
		data.add("And a heaven in a wild flower,");
		data.add("Hold infinity in the palm of your hand,");
		data.add("And eternity in an hour.");
		data.add("To see a world in a grain of sand,");
		data.add("And a heaven in a wild flower,");
		data.add("Hold infinity in the palm of your hand,");
		data.add("And eternity in an hour.");

		return data;
	}

}