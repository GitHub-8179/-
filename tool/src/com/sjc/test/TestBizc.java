package com.sjc.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sjc.fieldsCopy.FieldsCopyBizc;
import com.sjc.timestamp.TimestampBizc;
import com.sjc.util.InitWinController;

public class TestBizc {

	public static void main(String[] args) {
		try {
			InitWinController win = new InitWinController();
			TimestampBizc tikeJP = new TimestampBizc();
			FieldsCopyBizc fieldsCopyBizc = new FieldsCopyBizc();
			
			JFrame getwin = win.intiWin();
			getwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//添加滑动条
			JPanel view = tikeJP.intJpanel();
			JPanel view1 = fieldsCopyBizc.intJpanel();
			getwin.add(view,BorderLayout.NORTH);
			getwin.add(view1,BorderLayout.CENTER);	
			getwin.setVisible(true);
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, "程序执行出错！  ");
		}
		
	}
	
	
	
}
