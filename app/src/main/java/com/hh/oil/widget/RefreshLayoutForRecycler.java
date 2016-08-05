package com.hh.oil.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hh.oil.R;
import com.hh.oil.utils.log.LogUtils;
import com.hh.oil.widget.familiarRecyclerview.FamiliarRecyclerView;
import com.hh.oil.widget.familiarRecyclerview.FamiliarRecyclerViewOnScrollListener;

/**
 * 继承自SwipeRefreshLayout,从而实现滑动到底部时上拉加载更多的功能.
 * 
 * @author mrsimple
 */
public class RefreshLayoutForRecycler extends SwipeRefreshLayout {

	/**
	 * listview实例
	 */
	private FamiliarRecyclerView mRecyclerView;

	/**
	 * 上拉监听器, 到了最底部的上拉加载操作
	 */
	private OnLoadListener mOnLoadListener;

	/**
	 * ListView的加载中footer
	 */
	private View mListViewFooter;

	/**
	 * 是否在加载中 ( 上拉加载更多 )
	 */
	private boolean isLoading = false;

	// private boolean mCanLoad = true;

	/**
	 * @param context
	 */
	public RefreshLayoutForRecycler(Context context) {
		this(context, null);
	}

	public RefreshLayoutForRecycler(Context context, AttributeSet attrs) {
		super(context, attrs);

		mListViewFooter = LayoutInflater.from(context).inflate(R.layout.listview_footer, mRecyclerView, false);
		mListViewFooter.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		// 初始化ListView对象
		if (mRecyclerView == null) {
			getListView();
		}
	}

	/**
	 * 获取ListView对象
	 */
	private void getListView() {
		int childs = getChildCount();
		if (childs > 0) {
			View childView = getChildAt(0);
			if (childView instanceof FamiliarRecyclerView) {
				mRecyclerView = (FamiliarRecyclerView) childView;

				mRecyclerView.addFooterView(mListViewFooter);

				// 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
				mRecyclerView.setOnScrollListener(new FamiliarRecyclerViewOnScrollListener(mRecyclerView
						.getLayoutManager()) {
					@Override
					public void onScrolledToTop() {
						Log.i("wg", "onScrolledToTop ...");
					}

					@Override
					public void onScrolledToBottom() {
						Log.i("wg", "onScrolledToBottom ..." + canLoad());

						// 滚动时到了最底部也可以加载更多
						if (canLoad()) {
							loadData();
						}
					}
				});
				Log.d(VIEW_LOG_TAG, "### 找到listview");
			}
		}
	}

	/**
	 * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
	 * 
	 * @return
	 */
	private boolean canLoad() {
		return !isLoading;
	}

	/**
	 * 如果到了最底部,那么执行onLoad方法
	 */
	private void loadData() {
		if (mOnLoadListener != null) {
			// 设置状态
			setLoading(true);

			//
			mOnLoadListener.onLoad();
		}
	}

	public void setLoadEnable(boolean enable) {
		LogUtils.d("=========setLoadEnable================" + enable);
		if (null != mRecyclerView && null != mListViewFooter) {
			if (enable) {
				mRecyclerView.addFooterView(mListViewFooter);
			} else {
				mRecyclerView.removeFooterView(mListViewFooter);
			}
		}
	}

	/**
	 * @param loading
	 */
	public void setLoading(boolean loading) {
		isLoading = loading;
		if (null != mRecyclerView && null != mListViewFooter) {
			if (isLoading) {
				mListViewFooter.setVisibility(View.VISIBLE);

				// mRecyclerView.addFooterView(mListViewFooter);
			} else {
				mListViewFooter.setVisibility(View.GONE);

				// mRecyclerView.removeFooterView(mListViewFooter);
			}
		}
	}

	/**
	 * @param loadListener
	 */
	public void setOnLoadListener(OnLoadListener loadListener) {
		mOnLoadListener = loadListener;
	}

	/**
	 * 加载更多的监听器
	 * 
	 * @author mrsimple
	 */
	public static interface OnLoadListener {
		public void onLoad();
	}

}
