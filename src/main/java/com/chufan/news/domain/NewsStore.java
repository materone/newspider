package com.chufan.news.domain;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class NewsStore {
	@JSONField(name="Count",ordinal = 0)
	private int count;
	@JSONField(name="LastUpdate",ordinal = 1,format="yyyy-MM-dd HH:mm:ss.SSS")
	private Date lastUpdate;
	@JSONField(name="Data",ordinal = 2)
	private List<News> newsData;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<News> getNewsData() {
		return newsData;
	}

	public void setNewsData(List<News> newsData) {
		this.newsData = newsData;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
