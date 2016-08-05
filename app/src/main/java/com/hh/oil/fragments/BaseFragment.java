package com.hh.oil.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;

public abstract class BaseFragment extends Fragment {

	protected View mainLayout;
	protected LayoutInflater mInflater;
	protected Activity mActivity;
	protected int page_no = 1;
	protected int page_range = 10;

	protected abstract int setLayout();

	protected abstract void initData();

	protected abstract void initView();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mInflater = inflater;
		mainLayout = inflater.inflate(setLayout(), null);
		ViewUtils.inject(this, mainLayout);
		return mainLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
	}
}
