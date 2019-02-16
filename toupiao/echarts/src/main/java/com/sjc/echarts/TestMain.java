package com.sjc.echarts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add(new int[]{32,23,43,55,26});
		list.add(new int[]{34,64,49,25,84});
		list.add(new int[]{23,61,26,21,58});

		ChartFactory cf = new ChartFactory(list);
		cf.setColor(new String[] {"#FFC0CB","#32d2c9","#ee804b","#ffc668"});
		cf.setTitle("测试图片",null, "center");
		cf.setXAxis(new String[]{"第一组","第一组","第一组","第一组","第一组"});
		cf.setYAxisFormatter(null, "票");
		cf.setYAxisName(new String[] {"张三","李四","王二"});
		cf.setYAxisType(new String[] {"line","line","line"});
		cf.setYAxisSymbol(new String[] {"droplet","star","diamond"});
		Map mapMark = new HashMap();
		mapMark.put("name", "1");
		mapMark.put("yAxis", "2");
		cf.addMarkLine(1, mapMark);
		mapMark = new HashMap();
		mapMark.put("name", "平均值");
		mapMark.put("type", "average");
		cf.addMarkLine(-1, mapMark);
		mapMark = new HashMap();
		mapMark.put("name", "22");
		mapMark.put("yAxis", "12");
		cf.addMarkLine(2, mapMark);
		
//        System.out.println(JSONSerializer.toJSON(cf.getCharts()).toString());

	}
}
