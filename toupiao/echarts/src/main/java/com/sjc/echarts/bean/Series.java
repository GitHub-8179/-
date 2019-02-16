package com.sjc.echarts.bean;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Series {

	 private String name;
	 private String type; // 'line', bar
	 private String symbol ;//'circle',��������
	 private String symbolSize;  //�յ�Բ�Ĵ�С
	 private boolean smooth;  //�ؼ��㣬Ϊtrue�ǲ�֧�����ߵģ�ʵ�߾���true
	 private String stack ;
	 private Object data ;
	 private Map markLine; //{data: [{type: 'average', name: 'ƽ��ֵ'}]}
	 private ArrayList listMarkLine = new ArrayList();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbolSize() {
		return symbolSize;
	}
	public void setSymbolSize(String symbolSize) {
		this.symbolSize = symbolSize;
	}
	
	public boolean isSmooth() {
		return smooth;
	}
	public void setSmooth(boolean smooth) {
		this.smooth = smooth;
	}
	public String getStack() {
		return stack;
	}
	public void setStack(String stack) {
		this.stack = stack;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	public Map getMarkLine() {
		return markLine;
	}
	public void setMarkLine(Map markLine) {
		listMarkLine.add(markLine);
		this.markLine.put("data", this.listMarkLine);
	}
	public Series() {
		super();
		this.symbol="circle";
		this.symbolSize="4";
		this.smooth=false;
		this.markLine= new HashMap<String, Map[]>();
	}
	

	 
	 
}
