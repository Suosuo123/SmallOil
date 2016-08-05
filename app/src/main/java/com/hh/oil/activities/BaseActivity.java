package com.hh.oil.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hh.oil.R;
import com.lidroid.xutils.ViewUtils;

@SuppressLint("NewApi")
public class BaseActivity extends AppCompatActivity {

	protected Activity mActivity;

	private Toolbar mToolbar;

	private TextView tv_title;

	protected int page_no = 1;
	protected int page_range = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		mActivity = this;
		initWindow();

		onCreate();
		initView();
		initData();
	}

	private void onCreate() {
	}

	protected void initView() {
	}

	protected void initData() {
	}

	// 设置标题
	public void setActionBarTitle(Object s) {
		initTitleBar();
		/* 这些通过ActionBar来设置也是一样的，注意要在setSupportActionBar(toolbar);之后，不然就报错了 */
		// getSupportActionBar().setTitle(s.toString());
		// getSupportActionBar().setSubtitle("副标题");
		// getSupportActionBar().setLogo(R.drawable.img_back);
		tv_title.setText(s.toString());

		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	// 隐藏左边标题
	public void hideLeftIcon() {
		initTitleBar();
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
	}

	public void setActionBarRightBtnListener(OnClickListener listener) {
	}

	public void setActionBarRightBtnText(String text) {
	}

	private void initWindow() {

		Window window = getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
				| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		// | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		window.getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
		window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

		if (android.os.Build.VERSION.SDK_INT >= 21) {
			// 很明显，这两货是新API才有的。
			window.setStatusBarColor(getResources().getColor(R.color.main_orange));
			window.setNavigationBarColor(getResources().getColor(R.color.main_orange));

			// window.setStatusBarColor(getResources().getColor(R.color.half_transparent));
			// window.setNavigationBarColor(getResources().getColor(R.color.half_transparent));

		} else {
			if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
				window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				// window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			}
		}

	}

	private void initTitleBar() {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		// toolbar.setLogo(R.drawable.ic_launcher);
		// mToolbar.setTitle("MAP");// 标题的文字需在setSupportActionBar之前，不然会无效
		// toolbar.setSubtitle("副标题");
		tv_title = (TextView) mToolbar.findViewById(R.id.tv_title);
		setSupportActionBar(mToolbar);

		mToolbar.setNavigationIcon(R.drawable.img_back);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {// 返回按钮的监听
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
