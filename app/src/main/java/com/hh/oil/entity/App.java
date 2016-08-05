package com.hh.oil.entity;

import java.util.List;

public class App extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1252359303817260380L;

	private String id;
	private String name;
	private String importance;
	private String download;
	private String size;
	private String icon;
	private String address;
	private String description;
	private String version;
	private String developer;
	private String packagename;
	private String source_from;
	private String deadline;
	private String moshi;
	private String rate;
	private String adimage;

	private List<String> image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getSource_from() {
		return source_from;
	}

	public void setSource_from(String source_from) {
		this.source_from = source_from;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getMoshi() {
		return moshi;
	}

	public void setMoshi(String moshi) {
		this.moshi = moshi;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getAdimage() {
		return adimage;
	}

	public void setAdimage(String adimage) {
		this.adimage = adimage;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "App [id=" + id + ", name=" + name + ", importance=" + importance + ", download=" + download + ", size="
				+ size + ", icon=" + icon + ", address=" + address + ", description=" + description + ", version="
				+ version + ", developer=" + developer + ", packagename=" + packagename + ", source_from="
				+ source_from + ", deadline=" + deadline + ", moshi=" + moshi + ", rate=" + rate + ", adimage="
				+ adimage + ", image=" + image + "]";
	}

}
