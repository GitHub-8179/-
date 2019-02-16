package com.sjc.echarts.bean;

import java.util.HashMap;
import java.util.Map;

public class XAxis {

	/**
	 * 默认category
	 */
	private String  type ;//: 'category',
	/**
	 * 边框距离默认为true 柱状图需要   线状图为false
	 */
	private boolean boundaryGap ;//: false
	/**
	 * 坐标展示数据
	 */
	private String[] data ;
     //坐标轴颜色
//	private String axisLine; 
	
	/**
	 * 坐标轴文字旋转
	 * 默认
	 * otate:0,  旋转0度 
	 * interval:0  隔一个显示一个 
	 */
	private AetAxisLabel axisLabel ;
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBoundaryGap() {
		return boundaryGap;
	}

	public void setBoundaryGap(boolean boundaryGap) {
		this.boundaryGap = boundaryGap;
	}

//	public String getAxisLine() {
//		return axisLine;
//	}
//
//	public void setAxisLine(String axisLine) {
//		this.axisLine = axisLine;
//	}

	

	
	public AetAxisLabel getAxisLabel() {
		return axisLabel;
	}

	public void setAxisLabel(AetAxisLabel axisLabel) {
		this.axisLabel = axisLabel;
	}

	public XAxis(String[] data) {
		this.data = data;
		this.type = "category";
		this.axisLabel = new AetAxisLabel();
		this.boundaryGap = true;
		
//		  lineStyle:{
//            color:'red'
//        }

	}
	
	public XAxis() {
		super();
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}
	
	class AetAxisLabel {
		private String rotate ; 
		private String interval;
		public String getRotate() {
			return rotate;
		}
		public void setRotate(String rotate) {
			this.rotate = rotate;
		}
		public String getInterval() {
			return interval;
		}
		public void setInterval(String interval) {
			this.interval = interval;
		}
		public AetAxisLabel() {
			super();
			rotate = "0";
			interval = "0";
		}
		
		
	 }
}


	
