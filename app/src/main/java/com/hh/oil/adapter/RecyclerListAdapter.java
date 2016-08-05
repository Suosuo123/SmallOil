package com.hh.oil.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hh.oil.R;
import com.hh.oil.entity.App;
import com.hh.oil.utils.BitmapHelp;
import com.lidroid.xutils.BitmapUtils;

public class RecyclerListAdapter extends RecyclerView.Adapter<MyViewHolder> {
	private LayoutInflater mInflater;
	private Context mContext;
	public BitmapUtils mBitmapUtils;

	private List<App> mList = new ArrayList<App>();

	public RecyclerListAdapter(Context context) {
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);

		mBitmapUtils = BitmapHelp.getDefaultHeadIconBitmapUtils(mContext);
	}

	/**
	 * 下拉刷新时调用
	 * 
	 * @param list
	 */
	public void refresh(List<App> list) {
		this.mList.addAll(list);
		notifyDataSetChanged();
	}

	/**
	 * 上垃加载时调用
	 * 
	 * @param list
	 */
	public void bindData(List<App> list) {
		this.mList.addAll(list);
		notifyItemRangeChanged(mList.size() - 1, list.size());
	}

	public void updateList(List<App> list) {
		this.mList = list;
		notifyDataSetChanged();
	}

	public void clearData() {
		this.mList.clear();
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		mBitmapUtils.display(holder.iv_icon, mList.get(position).getIcon());
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = mInflater.inflate(R.layout.list_item, parent, false);

		return new MyViewHolder(itemView);
	}
}

class MyViewHolder extends RecyclerView.ViewHolder {
	ImageView iv_icon;

	public MyViewHolder(View itemView) {
		super(itemView);
		iv_icon = (ImageView) itemView.findViewById(R.id.imageView1);
	}
}