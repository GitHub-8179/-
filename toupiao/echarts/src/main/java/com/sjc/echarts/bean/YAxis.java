package com.sjc.echarts.bean;

import java.util.HashMap;
import java.util.Map;

public class YAxis {

	/**
	 * y���ʶĬ�ϡ�value��
	 */
	 private String type;
	 private Map axisLabel ; 
	 
	 public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map getAxisLabel() {
		return axisLabel;
	}
	public void setAxisLabel(Map axisLabel) {
		this.axisLabel = axisLabel;
	}
	/**
	  * ��ʽ��y������
	  * @param top ֵǰ�ӱ�־
	  * @param end ֵ��ӱ�־
	  */
	public void setFormatter(String top,String end) {
		String formatterValue = "{value}";
		if(top!=null) {
			formatterValue = top+formatterValue;
		}
		if(end!=null) {
			formatterValue += end;
		}
		axisLabel.put("formatter", formatterValue);
	}
	public YAxis() {
		super();
		this.type = "value";
		this.axisLabel = new HashMap<String, String>();
		this.axisLabel.put("formatter", "{value}");

	}
	 
}
