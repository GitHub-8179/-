package com.sjc.echarts.bean.twobean;

public class TitleTextStyle {

	/**
	 * 字体大小 默认14
	 */
	private String fontSize ;
	/**
	 * 字体颜色 默认为黑色
	 */
	private String color;//'#4c4c4c',
    /**
	 * 字体加粗
	 */
	private String fontWeight;//'bolder'
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFontWeight() {
		return fontWeight;
	}
	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}
	public TitleTextStyle() {
		super();
		this.fontSize = "14";
		this.fontWeight = "bolder";
		this.color = "#4c4c4c";
	}
	
	
	
}
