package com.sjc.util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * �ı���۽����뽹�¼�
 * @author ��
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
	public void focusGained(FocusEvent e) {//��ý����ʱ��,�����ʾ����
		if(text.equals(jtf.getText().trim())){
			jtf.setText("");
		}
		
	}

	@Override
	public void focusLost(FocusEvent e) {//ʧȥ�����ʱ��,�ж����Ϊ��,����ʾ��ʾ����
		if("".equals(jtf.getText().trim())){
			jtf.setText(text);
		}
	}
}
