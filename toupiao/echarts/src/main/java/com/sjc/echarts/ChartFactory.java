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
	 * ����ͼ��
	 * @param list
	 */
	public ChartFactory(List list) {
		map = new HashMap<String, Object>();
		
		this.tooltip = new HashMap<String, String>();
		this.tooltip.put("trigger", "axis");
		map.put("tooltip", tooltip);
		
		this.map.put("series", initSeries(list));//ʼ��Ҫչʾ������
		this.map.put("title", new Title());;//��ʼ������Ĭ��Ϊ��
		this.map.put("xAxis", new XAxis()); //��ʼ��������
		this.map.put("yAxis", new YAxis()); // ��ʼ��������
	}
	public Map getCharts() {
		if(this.color!=null) {
			map.put("color", this.color);
		}
		return map;
	}
	
	
	
	/**
	 * ��ָ���е����ݽ���ͳ��
	 * @param i �ڼ������� ��Ϊ-1������������е��������һ��
	 * @param map   {type: 'average', name: 'ƽ��ֵ'},
	 * 				{ name: '��Сֵ',yAxis:10},
                    { name: '���ֵ',yAxis:99}
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
	 * �����Ƿ�ͬ��һ����ʾ��
	 * @param bool Ĭ��true 
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
	 * ����y��ÿ�����ݹյ�ķ���ͼƬĬ��Ϊcircle
	 * @param type   circle rectangle triangle diamond 
	 * 					emptyCircle emptyRectangle emptyTriangle emptyDiamond
	 * 				star:�����(starn:n����)  heart:���� droplet��ˮ��  pin:��־ arrow:��ͷ
	 * 				image://+"ͼƬ·��"
	 */
	public void setYAxisSymbol(String[] symbol) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setSymbol(symbol[i]);
		}
	}
	
	/**
	 * ����y��ÿ�����ݹյ�Ĵ�С Ĭ��Ϊ4
	 * @param symbolSize   
	 */
	public void setYAxisSymbolSize(String[] symbolSize) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setSymbolSize(symbolSize[i]);
		}
	}
	
	/**
	 * ����y��ÿ������չʾ������
	 * @param type   line:��״ͼ, bar:��״ͼ 
	 */
	public void setYAxisType(String[] type) {
		List<Series> seriesList =( List<Series>) this.map.get("series");
		for (int i = 0,num = seriesList.size(); i < num; i++) {
			seriesList.get(i).setType(type[i]);
		}
	}
	
	
	/**
	 * ����y��ÿ������ ��ʾ������
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
	 * ����ͷ����������С����ɫ
	 * @param fontSize Ĭ��14
	 * @param color Ĭ�Ϻ�ɫ
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
	 * ����ͷ����
	 * @param text ��������
	 * @param subtext ������
	 * @param x ����λ�� left,right,Ĭ��center
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
	  * ��ʽ��y��������ʾ
	  * @param top ֵǰ�ӱ�־
	  * @param end ֵ��ӱ�־
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
	 * ����ͼ���Ƿ���y������ Ĭ��Ϊtrue 
	 * ��ȫΪ��״ͼʱ����ʹ��false
	 * @param bool
	 */
	public void setXAxisBoundaryGap(boolean bool) {
		XAxis xAxis = (XAxis) this.map.get("xAxis");
		xAxis.setBoundaryGap(bool);;
	}
	
	
	/**
	/**��ʼ��������
	 * x������
	 * @param data
	 */
	public void setXAxis(String[] data) {
		this.map.put("xAxis", new XAxis(data)); //��ʼ��������
//		XAxis xAxis = (XAxis) this.map.get("xAxis");
//		xAxis.setData(data);
	}
	/**
	 * ��ʼ��Ҫչʾ������
	 * @param list Ҫչʾ������
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
