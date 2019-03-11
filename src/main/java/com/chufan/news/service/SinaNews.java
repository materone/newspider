package com.chufan.news.service;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chufan.news.domain.News;
import com.chufan.news.domain.NewsStore;
import com.chufan.util.StringUtil;

import javassist.bytecode.ByteArray;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class SinaNews implements PageProcessor {
	
//	static String xpath_news = "//*[@id=\"syncad_1\"]/h1[1]/a/text()";
//	static String xpath_news = "//*[@id=\"20181231V0ZGBO_24\"]/div/h3/a/text()";
//	static String xpath_news = "//*[@id=\"content_right\"]/div[3]/ul/*/div[2]/a/text()";
//	static String xpath_news = "//*[@id='content_right']/div[@class='content_list']/ul/li/allText()";

//	static String xpath_news = "//*[@id='content_right']/div[@class='content_list']/ul/li[@class!='nocontent']/allText()";

	static String xpath_news = "//*[@id='content_right']/div[@class='content_list']/ul/li";
	
//	static String url = "https://news.sina.com.cn/";
//	static String url = "https://news.qq.com/";
	static String url = "http://www.chinanews.com/scroll-news/news1.html";
	public static NewsStore store = new NewsStore();
	static List <News> data = new ArrayList<News>();
	News news;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//    	System.out.println(page);
//        System.out.println("XX:"+page.getHtml().xpath("//h1[@id=\"pL_Title\"]/text()"));
        ////*[@id="syncad_1"]/h1[1]/a
//    	page.getHtml().$(selector)
        System.out.println("XX:"+page.getHtml().xpath(xpath_news).all().size());
        int cnt = 0;
        List<Selectable> listNews = page.getHtml().xpath(xpath_news).nodes();
        data.clear();
        for (Selectable selectable : listNews) {
			if(!selectable.xpath("/[@class]").match()) {
				news = new News();
				news.setNewstype(selectable.xpath("/*/div[@class='dd_lm']/allText()").get());
				news.setTitle(StringUtil.full2Half(StringUtil.cToe(selectable.xpath("/*/div[@class='dd_bt']/a/text()").get())));
				news.setDate(selectable.xpath("/*/div[@class='dd_time']/text()").get());
				data.add(news);
				System.out.println(cnt++ + "--" + selectable.xpath("/*/div[@class='dd_lm']/allText()") + " -- "+selectable.xpath("/*/div[@class='dd_bt']/a/text()") + " -- "+selectable.xpath("/*/div[@class='dd_time']/text()"));
//				page.putField("title", selectable.xpath("/*/div[@class='dd_bt']/a/text()"));
			}
		}
        store.setCount(data.size());
        store.setLastUpdate(new Date());
        store.setNewsData(data);
        //System.out.println(JSON.toJSONString(store,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.PrettyFormat));
//        List<String> news = page.getHtml().xpath(xpath_news).all();
//        int cnt = 0;
//        for (String article : news) {
//			System.out.println(cnt++ + "--" + article);
//		}

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider.create(new SinaNews()).addUrl("https://news.sina.com.cn/roll/#pageid=153&lid=2968&k=&num=50&page=1").thread(1).run();
    	Spider spider = Spider.create(new SinaNews()).addUrl(url).thread(1).setExitWhenComplete(true);
        System.out.println(spider.getThreadAlive());
        System.out.println("Spider Start status:"+spider.getStatus());
        spider.run();
        System.out.println(spider.getThreadAlive());
        System.out.println("Spider Start status:"+spider.getStatus());
        while(spider.getStatus()!=Status.Stopped) {
        	System.out.println("Spider status:"+spider.getStatus());
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        System.out.println("Spider END status:"+spider.getStatus());
        Spider.create(new SinaNews()).addUrl(url).thread(1).setExitWhenComplete(true).run();
        System.out.println("run again");
        //https://news.sina.com.cn/
    }
}