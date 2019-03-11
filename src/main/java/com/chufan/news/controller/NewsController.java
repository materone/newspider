package com.chufan.news.controller;

import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chufan.news.domain.NewsStore;
import com.chufan.news.service.SinaNews;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;

@RestController
public class NewsController {
	static long lastAccess = 0;

	@RequestMapping("/get")
	public String index() {
		String ret = "【news】新闻";
		return ret;
	}

	@RequestMapping(value ="/news",produces= "application/json; charset=GBK")
	private NewsStore getNews() {
		long now = System.currentTimeMillis();
		String url = "http://www.chinanews.com/scroll-news/news1.html";
		System.out.printf("refresh time: %d = %d \n",lastAccess,now);
		Spider spider = null;//
		if(lastAccess == 0 || (now - lastAccess) > 100*1000) {
			spider = Spider.create(new SinaNews()).addUrl(url).thread(1).setExitWhenComplete(true);
			spider.runAsync();
			lastAccess = now;
		}
		if(spider != null) {
			while (spider.getStatus() != Status.Stopped) {
				System.out.println("Spider status:" + spider.getStatus()+" at: "+new Date());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return SinaNews.store;
	}
}
