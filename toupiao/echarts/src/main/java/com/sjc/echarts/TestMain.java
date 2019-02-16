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
		cf.setTitle("����ͼƬ",null, "center");
		cf.setXAxis(new String[]{"��һ��","��һ��","��һ��","��һ��","��һ��"});
		cf.setYAxisFormatter(null, "Ʊ");
		cf.setYAxisName(new String[] {"����","����","����"});
		cf.setYAxisType(new String[] {"line","line","line"});
		cf.setYAxisSymbol(new String[] {"droplet","star","diamond"});
		Map mapMark = new HashMap();
		mapMark.put("name", "1");
		mapMark.put("yAxis", "2");
		cf.addMarkLine(1, mapMark);
		mapMark = new HashMap();
		mapMark.put("name", "ƽ��ֵ");
		mapMark.put("type", "average");
		cf.addMarkLine(-1, mapMark);
		mapMark = new HashMap();
		mapMark.put("name", "22");
		mapMark.put("yAxis", "12");
		cf.addMarkLine(2, mapMark);
		
//        System.out.println(JSONSerializer.toJSON(cf.getCharts()).toString());

	}
}
