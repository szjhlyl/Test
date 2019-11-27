package com.kawa.jd.task;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kawa.jd.entity.Phone;
import com.kawa.jd.util.JDUtil;

@Service
public class JDTask {
	
	private String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&s=1&click=0&page=";
	
	@Resource
	private JDUtil util;
	
	
	@Scheduled(fixedDelay = 3000)
	public void getPhone() {
		for (int i = 1; i <3; i=i+2) {
			System.err.println(url+i);
			String html = util.doGetHtml(url+i);
			//解析页面数据
			parse(html);
		}
	}
	public void parse(String html) {
		Document doc = Jsoup.parse(html);
		//获取所有手机的li
		Elements element = doc.select("div#J_goodsList>ul>li");
		for (int i = 0; i < element.size(); i++) {
			Phone phone = new Phone();
			//商品统称
			Element li = element.get(i);
			Long spu = Long.valueOf(li.attr("data-spu"));
			//每款手机的所有配置
			Elements psItem = li.select("li.ps-item");
			for (int j = 0; j <psItem.size(); j++) {
				Element phoneEle = psItem.get(j);
				Long sku = Long.valueOf(phoneEle.select("a>img").first().attr("data-sku"));
				
				String imageUrl =("https:" +phoneEle.select("a>img").first().attr("data-lazy-img")).replace("n9", "n1");
				String picPath = util.saveImage(imageUrl);
			}
			phone.setSpu(spu);
		}
	}
	

}
