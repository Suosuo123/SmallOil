package com.hh.oil.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import com.hh.oil.R;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.activity_main)
@SuppressLint("NewApi")
public class MainActivity extends BaseActivity {

    @OnClick(R.id.btn_open_loading)
    public void openLoadingClick(View view) {
        startActivity(new Intent(this, TestStandardActivity.class));
    }

    @OnClick(R.id.btn_open_listview)
    public void openListviewClick(View view) {
        startActivity(new Intent(this, TestListViewActivity.class));
    }

    @OnClick(R.id.btn_open_recyler_list)
    public void openRecylerListClick(View view) {
        startActivity(new Intent(this, TestRecyclerListActivity.class));
    }

    @OnClick(R.id.btn_open_recyler_grid)
    public void openRecylerGridClick(View view) {
        startActivity(new Intent(this, TestRecyclerGridActivity.class));
    }

    @OnClick(R.id.btn_open_recyler_staggered)
    public void openRecylerStaggeredClick(View view) {
        startActivity(new Intent(this, TestRecyclerStaggeredActivity.class));
    }

    @OnClick(R.id.btn_open_tab)
    public void openTabClick(View view) {
        startActivity(new Intent(this, TestPagerTabActivity.class));
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initView() {
        super.initView();

        setActionBarTitle("主界面");
        hideRightIcon();
    }

    @Override
    protected void initData() {
        super.initData();
    }

}
