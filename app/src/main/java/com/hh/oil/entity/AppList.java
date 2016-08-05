package com.hh.oil.entity;

import java.util.List;

public class AppList extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 528404774766080393L;

	private List<App> dataList;

	public List<App> getDataList() {
		return dataList;
	}

	public void setDataList(List<App> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "AppList [dataList=" + dataList + "]";
	}

}
