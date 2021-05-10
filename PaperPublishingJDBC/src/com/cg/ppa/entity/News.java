package com.cg.ppa.entity;

public class News {
	private int newsId;
	private String headline;
	private int reporterId;
	private String location;
	private int categoryId;
	private String newsDescription;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public int getReporterId() {
		return reporterId;
	}

	public void setReporterId(int reporterId) {
		this.reporterId = reporterId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getNewsDescription() {
		return newsDescription;
	}

	public void setNewsDescription(String newsDescription) {
		this.newsDescription = newsDescription;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", headline=" + headline + ", reporterId=" + reporterId + ", location="
				+ location + ", categoryId=" + categoryId + ", newsDescription=" + newsDescription + "]";
	}

	public News(int newsId, String headline, int reporterId, String location, int categoryId, String newsDescription) {
		super();
		this.newsId = newsId;
		this.headline = headline;
		this.reporterId = reporterId;
		this.location = location;
		this.categoryId = categoryId;
		this.newsDescription = newsDescription;
	}

	public News() {
		super();
	}

}
