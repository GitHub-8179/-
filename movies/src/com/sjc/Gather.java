package com.sjc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Gather {
	private static final String URLS = "https://hao.360.cn/?s0001";
	private static final String URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//发送短息

//	private static final String urls = "https://movie.douban.com/subject/1606456/photos?type=R";
	
	private static final String type = "utf-8";

	public static void main(String[] args) {
		
		StringBuffer sb = new StringBuffer();
		BufferedReader br= null;
		InputStreamReader isr = null;
		try {
			URL url = new URL(URL);//网络地址
			URLConnection uc = url.openConnection();//打开地址
			isr = new InputStreamReader(uc.getInputStream(),type);//建立管道
			br = new BufferedReader(isr);//缓冲
			
			String temp = "";//创建临时文件
			
			while ((temp=br.readLine())!=null) {
				sb.append(temp+"\n");
			}
			
			System.out.println("下載下的html"+sb.toString());
			
			Document document = Jsoup.parse(sb.toString());
			//拿到盒子id=comments
			Element element = document.getElementById("comments");
			//拿到comments-item集合
			Elements elements = document.getElementsByClass("comments-item");
			//遍历所有的评论组
			for (Element e : elements) {
				//用户名   拿到<s>标签下的值
				String name = e.getElementsByTag("s").last().text();
				//用户评论 拿到<p>标签里的值
				String desc = e.getElementsByTag("p").text();
				//时间  拿到<comment-date>标签里的值
				String date = e.getElementsByClass("comment-date").last().text();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)br.close();
				if(isr!=null)isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
