package com.sjc.util;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JFrame;

public class InitWinController {

	public JFrame intiWin(){
		
//	     add(new Button("North"), BorderLayout.NORTH);
//	     add(new Button("South"), BorderLayout.SOUTH);
//	     add(new Button("East"), BorderLayout.EAST);
//	     add(new Button("West"), BorderLayout.WEST);
//	     add(new Button("Center"), BorderLayout.CENTER);
		Map map = FileData.getFileDate();
	     
		JFrame win = new JFrame(map.get("winName")==null?"bool":map.get("winName").toString());//����
		win.setLayout(new BorderLayout());
//		win.setBounds(450, 200, 500, 300);//������,���
		win.setSize(500, 270);
//		win.setTitle("�Զ�����ʱ�䴮");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �˳��͹رս���
//		win.setSize(230, 170);// ��С
	    win.setLocationRelativeTo(null);// ����
	    win.setResizable(false);// ��������
//		win.setVisible(true);
		return win;
	}
}
