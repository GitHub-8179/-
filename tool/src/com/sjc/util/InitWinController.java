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
	     
		JFrame win = new JFrame(map.get("winName")==null?"bool":map.get("winName").toString());//窗口
		win.setLayout(new BorderLayout());
//		win.setBounds(450, 200, 500, 300);//靠左上,宽高
		win.setSize(500, 270);
//		win.setTitle("自动生成时间串");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出就关闭界面
//		win.setSize(230, 170);// 大小
	    win.setLocationRelativeTo(null);// 居中
	    win.setResizable(false);// 不可缩放
//		win.setVisible(true);
		return win;
	}
}
