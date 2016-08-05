package com.hh.oil.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hh.oil.R;
import com.hh.oil.entity.App;
import com.hh.oil.utils.BitmapHelp;
import com.hh.oil.widget.RoundCornerImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ListViewAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	public BitmapUtils mBitmapUtils;
	private List<App> mList = new ArrayList<App>();

	public ListViewAdapter(Context context) {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);

		mBitmapUtils = BitmapHelp.getDefaultHeadIconBitmapUtils(mContext);
	}

	public void bindData(List<App> list) {
		this.mList.addAll(list);
		notifyDataSetChanged();
	}

	public void updateList(List<App> list) {
		this.mList = list;
		notifyDataSetChanged();
	}

	public void clearData() {
		this.mList.clear();
	}

	@Override
	public int getCount() {
		return mList != null ? mList.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	private class ViewHolder {
		@ViewInject(R.id.imageView1)
		public RoundCornerImageView niv_icon;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		App app = mList.get(position);
		View view = convertView;
		if (view == null) {
			ViewHolder vh = new ViewHolder();
			view = mInflater.inflate(R.layout.list_item_round_corner, arg2, false);
			ViewUtils.inject(vh, view);
			view.setTag(vh);
		}
		ViewHolder vh = (ViewHolder) view.getTag();
		mBitmapUtils.display(vh.niv_icon, app.getIcon());
		return view;
	}

}
