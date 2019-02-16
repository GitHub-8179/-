package com.sjc.echarts.bean;

import com.sjc.echarts.bean.twobean.TitleTextStyle;

public class Title {

	/**
	 * 'left',right，center
	 */
	 private String  x;
	 /**
	  * 主标题
	  */
	 private String  text;
	 /**
	  * 副标题
	  */
	 private String  subtext;
	 
	 private TitleTextStyle textStyle;
	 
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubtext() {
		return subtext;
	}
	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}
	
	public TitleTextStyle getTextStyle() {
		return textStyle;
	}
	public void setTextStyle(TitleTextStyle textStyle) {
		this.textStyle = textStyle;
	}
	public Title() {
		super();
		this.x = "center";
		this.text = "";
		this.subtext = "";
		this.textStyle = new TitleTextStyle();
	}
      
	
	
}
