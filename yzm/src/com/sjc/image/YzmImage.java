package com.sjc.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebService(targetNamespace="yzmImage")
public class YzmImage extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ffffffff");
//		response.setContentType("test/image;chatset=utf-8");
		response.setContentType("test/html;chatset=utf-8");
		//2s后刷新到！！
//		response.setHeader("refresh", "2;url=/day06_01_response/1.html") ;
		
			int width = 100,height =25 ;
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			g.setColor(Color.RED);
			g.drawRect(0, 0, width, height);//绘画一矩形
			g.setColor(Color.yellow);
			g.fillRect(1, 1, width-2, height-2);
			g.setFont(new Font("幼圆",Font.BOLD+Font.ITALIC,20));
			Random r = new Random();
			
			g.setColor(Color.blue);
			//绘画随机的4个数字
//			for (int i = 0; i < 4; i++) {
//				int n = r.nextInt(10);
//				g.drawString(n+"", 20*i+10, 20);
//			}
			String s = "使肌肤大量进口的加拉地方减肥的拉萨";
			for (int i = 0; i < 4; i++) {
				char c = s.charAt(r.nextInt(s.length()));
				int dis = r.nextBoolean()?r.nextInt(10):-1*r.nextInt(10);
				g.drawString(c+"", i*20+10+dis, 20);
			}
			
			
			
			g.setColor(Color.GRAY);
			//绘画15行线段
			for (int i = 0; i < 15; i++) {
				int j = r.nextInt(height);
				g.drawLine(0,j, width, j);
			}
			//随机绘画1000个点
//			for (int i = 0; i < 1000; i++) {
//				g.drawOval(r.nextInt(width), r.nextInt(height), 1, 1);
//			}
		ImageIO.write(image,"jpg" , response.getOutputStream());
	}

	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ffffffff");
		doGet(request, response);
	}

	public static Color getColor(Random ran,int form,int to){
		int r = form + ran.nextInt(to-form);
		int g = form + ran.nextInt(to-form);
		int b = form + ran.nextInt(to-form);
		return new Color( r, g, b);
	}

}
