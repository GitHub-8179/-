package com.sjc.echarts.bean.twobean;

public class TitleTextStyle {

	/**
	 * �����С Ĭ��14
	 */
	private String fontSize ;
	/**
	 * ������ɫ Ĭ��Ϊ��ɫ
	 */
	private String color;//'#4c4c4c',
    /**
	 * ����Ӵ�
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
