package com.hh.oil.entity;

import java.io.Serializable;

import com.lidroid.xutils.db.annotation.Transient;

public class BaseEntity implements Serializable {
	@Transient
	private static final long serialVersionUID = 1L;
	@Transient
	private int status = 0;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
