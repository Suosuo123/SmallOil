package com.hh.oil.activities;

import android.view.View;
import android.widget.TextView;

import com.hh.oil.R;
import com.hh.oil.widget.MultiStateView;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_loading_test)
public class TestStandardActivity extends BaseActivity {

	@ViewInject(R.id.multiStateView)
	private MultiStateView mMultiStateView;

	@ViewInject(R.id.tv_content)
	private TextView tv_content;

	@OnClick(R.id.btn_show_loading)
	public void click1(View view) {
		mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
	}

	@OnClick(R.id.btn_show_empty)
	public void click2(View view) {
		mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
	}

	@OnClick(R.id.btn_show_error)
	public void click3(View view) {
		mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
	}

	@OnClick(R.id.btn_show_content)
	public void click4(View view) {
		mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
	}

	@Override
	protected void initView() {
		super.initView();
		setActionBarTitle("一个标准ACT");

		mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR).findViewById(R.id.btn_retry)
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
					}
				});

	}

	@Override
	protected void initData() {
		super.initData();

	}

}
