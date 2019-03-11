package com.chufan.news.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class News {
	@JSONField(name = "NewsType", ordinal = 0)
	private String newstype;
	@JSONField(name = "Title", ordinal = 1)
	private String title;
	@JSONField(name = "Date", ordinal = 2)
	private String date;

	public String getNewstype() {
		return newstype;
	}

	public void setNewstype(String newstype) {
		this.newstype = newstype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
