package com.hh.oil.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hh.oil.R;
import com.hh.oil.utils.log.LogUtils;
import com.hh.oil.widget.uiView.UIImageView;
import com.lidroid.xutils.ViewUtils;

@SuppressLint("NewApi")
public class BaseActivity extends AppCompatActivity {

    private UIImageView iv_back;
    private TextView tv_title;
    private UIImageView iv_right;

    protected Activity mActivity;

    public int page_no = 1;
    public int page_range = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindow();
        mActivity = this;
        ViewUtils.inject(mActivity);
        findViews();

        onCreate();
        initView();
        initData();
    }

    protected void onCreate() {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    /**
     * 初始化标题控件
     */
    public void findViews() {
        iv_back = (UIImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_right = (UIImageView) findViewById(R.id.iv_right);
        if (null != iv_back) {
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setActionBarTitle(String title) {
        LogUtils.d("======tv_title=========" + tv_title);
        if (null != tv_title) {
            tv_title.setText(title);
        }
    }

    /**
     * 显示左边按钮
     */
    public void showLeftIcon() {
        if (null != iv_back) {
            iv_back.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏左边按钮
     */
    public void hideLeftIcon() {
        if (null != iv_back) {
            iv_back.setVisibility(View.GONE);
        }
    }

    /**
     * 显示右边按钮
     */
    public void showRightIcon() {
        if (null != iv_right) {
            iv_right.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏右边按钮
     */
    public void hideRightIcon() {
        if (null != iv_right) {
            iv_right.setVisibility(View.GONE);
        }
    }

    /**
     * 隐藏右边按钮
     */
    public void setRightIcon(int iconResId, View.OnClickListener listener) {
        if (null != iv_right) {
            iv_right.setVisibility(View.VISIBLE);
            iv_right.setImageResource(iconResId);
            iv_right.setOnClickListener(listener);
        }
    }


    /**
     * 初始化窗口主样式
     */
    private void initWindow() {

        Window window = getWindow();
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(getResources().getColor(R.color.main_color));
            window.setNavigationBarColor(getResources().getColor(R.color.main_color));
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            }
        }

    }
}
