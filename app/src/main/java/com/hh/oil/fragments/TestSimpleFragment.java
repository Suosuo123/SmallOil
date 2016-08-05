package com.hh.oil.fragments;

import android.os.Bundle;

import com.hh.oil.R;

public class TestSimpleFragment extends BaseFragment {

	public static TestSimpleFragment newInstance() {
		TestSimpleFragment f = new TestSimpleFragment();
		Bundle b = new Bundle();
		f.setArguments(b);
		return f;
	}

	@Override
	protected int setLayout() {
		return R.layout.fragment_image;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

}