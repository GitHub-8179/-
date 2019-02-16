package com.sjc.echarts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sjc.echarts.bean.Series;
import com.sjc.echarts.bean.Title;
import com.sjc.echarts.bean.XAxis;
import com.sjc.echarts.bean.YAxis;

public class ChartFactory {

	private Map map;
	private String color[] ; 
	private Map tooltip ;
	private Map legend;
	
	public void setColor(String[] color) {
		this.color = color;
	}
	
	public ChartFactory() {
		super();
	}

	

	
	/**
	 * 创建图形
	 * @param list
	 */
	public ChartFactory(List list) {
		map = new HashMap<String, Object>();
		
		this.tooltip = new HashMap<String, String>();
		this.tooltip.put("trigger", "axis");
		map.put("tooltip", tooltip);
		
		this.map.put("series", initSeries(list));//始化要展示的数据
		this.map.put("title", new Title());;//初始化标题默认为空
		this.map.put("xAxis", new XAxis()); //初始化横坐标
		this.map.put("yAxis", new YAxis()); // 初始化纵坐标
	}
	public Map getCharts() {
		if(this.color!=null) {
			map.put("color", this.color);
		}
		return map;
	}
	
	
	
	/**
	 * 对指定行的数据进行统计
	 * @param i 第几行数据 若为-1，则是针对所有的数据添加一行
	 * @param map   {type: 'average', name: '平均值'},
	 * 				{ name: '最小值',yAxis:10},
                    { name: '最大值',yAxis:99}
	 */
	public void addMarkLine(int i ,Map map) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		if(i==-1) {
			for (Series series : seriesList) {
				series.setMarkLine(map);
			}
		}else {
			Series series = seriesList.get(i);
			series.setMarkLine(map);
		}
	}
	
	
	/**
	 * 设置是否同用一个提示框
	 * @param bool 默认true 
	 */
	public void setTrigger(boolean bool) {
		Map<String, String> trigger =( Map) this.map.get("tooltip");
		if(bool) {
			trigger.put("trigger", "axis");
		}else {
			trigger.put("trigger", "item");
		}
	}
	
	/**
	 * 设置y轴每组数据拐点的符号图片默认为circle
	 * @param type   circle rectangle triangle diamond 
	 * 					emptyCircle emptyRectangle emptyTriangle emptyDiamond
	 * 				star:五角星(starn:n角形)  heart:心形 droplet：水滴  pin:标志 arrow:箭头
	 * 				image://+"图片路径"
	 */
	public void setYAxisSymbol(String[] symbol) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setSymbol(symbol[i]);
		}
	}
	
	/**
	 * 设置y轴每组数据拐点的大小 默认为4
	 * @param symbolSize   
	 */
	public void setYAxisSymbolSize(String[] symbolSize) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setSymbolSize(symbolSize[i]);
		}
	}
	
	/**
	 * 设置y轴每组数据展示的类型
	 * @param type   line:线状图, bar:柱状图 
	 */
	public void setYAxisType(String[] type) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setType(type[i]);
		}
	}
	
	
	/**
	 * 设置y轴每组数据 提示的名称
	 * @param name 
	 */
	public void setYAxisName(String[] name) {
		this.legend = new HashMap<String, String>();
//		this.legend.put("x", "right");
		this.legend.put("y", "bottom");
		this.legend.put("data", name);
		map.put("legend", legend);
		
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setName(name[i]);
		}
	}
	
	/**
	 * 设置头标题的字体大小和颜色
	 * @param fontSize 默认14
	 * @param color 默认黑色
	 */
	public void setTitleTextStyle(String fontSize,String color) {
		Title title = (Title) this.map.get("title");
		com.sjc.echarts.bean.twobean.TitleTextStyle titleTextStyle = title.getTextStyle();
		if(fontSize!=null) {
			titleTextStyle.setFontSize(fontSize);
		}
		if(color!=null) {
			titleTextStyle.setColor(color);
		}
	}
	/**
	 * 设置头标题
	 * @param text 标题名称
	 * @param subtext 副标题
	 * @param x 标题位置 left,right,默认center
	 */
	public void setTitle(String text,String subtext,String x) {
		Title title = (Title) this.map.get("title");
		if(text!=null) {
			title.setText(text);
		}
		if(subtext!=null) {
			title.setSubtext(subtext);	
		}
		if(x!=null) {
			title.setX(x);
		}
	}
	
	 /**
	  * 格式化y轴坐标显示
	  * @param top 值前加标志
	  * @param end 值后加标志
	  */
	public void setYAxisFormatter(String top,String end) {
		YAxis yAxis = (YAxis) this.map.get("yAxis");
		yAxis.setFormatter(top,end);
//		StringBuffer formatterValue = new StringBuffer("{value}");
//		if(top!=null) {
//			formatterValue.insert(0, top);
//		}
//		if(end!=null) {
//			formatterValue.append(end);
//		}
//		yAxis.getAxisLabel().put("formatter", "formatterValue");
	}
	
	/**
	 * 设置图形是否与y轴相连 默认为true 
	 * 当全为现状图时可以使用false
	 * @param bool
	 */
	public void setXAxisBoundaryGap(boolean bool) {
		XAxis xAxis = (XAxis) this.map.get("xAxis");
		xAxis.setBoundaryGap(bool);;
	}
	
	
	/**
	/**初始化横坐标
	 * x轴坐标
	 * @param data
	 */
	public void setXAxis(String[] data) {
		this.map.put("xAxis", new XAxis(data)); //初始化横坐标
//		XAxis xAxis = (XAxis) this.map.get("xAxis");
//		xAxis.setData(data);
	}
	/**
	 * 初始化要展示的数据
	 * @param list 要展示的数据
	 * @return
	 */
	public List<Series> initSeries(List list) {
		Series series = null;
		List<Series> seriesList = new ArrayList<Series>();
		for (int i = 0,num=list.size(); i < num; i++) {
			series = new Series();
			series.setData(list.get(i));
			seriesList.add(series);
		}
		return seriesList;
	}
	
}
