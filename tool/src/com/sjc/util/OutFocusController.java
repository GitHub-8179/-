package com.sjc.util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * 文本框聚焦，离焦事件
 * @author 宋
 *
 */
public class OutFocusController implements FocusListener{

	String text;
	JTextField jtf;
	
	public OutFocusController(String text, JTextField jtf){
		this.text = text;
		this.jtf = jtf;
	}
	@Override
	public void focusGained(FocusEvent e) {//获得焦点的时候,清空提示文字
		if(text.equals(jtf.getText().trim())){
			jtf.setText("");
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {//失去焦点的时候,判断如果为空,就显示提示文字
		if("".equals(jtf.getText().trim())){
			jtf.setText(text);
		}
	}
}
