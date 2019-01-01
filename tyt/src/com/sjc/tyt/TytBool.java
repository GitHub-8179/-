package com.sjc.tyt;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TytBool extends JFrame{ 

	
	boolean bool = true;
	double x1,x2,y1,y2;
	public void getWin(){
		
		this.setLayout(new BorderLayout());
		this.setUndecorated(true);
		this.setOpacity(0.3f);
//		this.setAlwaysOnTop(true);//缃《
		this.setSize(330, 680);
		this.setTitle("宸ュ叿娴嬭瘯");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);	//璁剧疆绐楀彛灞呬腑
		this.setResizable(false);
		this.setVisible(true);

		JLabel jLabel=new JLabel();

		this.add(jLabel);

		//缁檍Label娣诲姞涓�涓洃鍚�
		
		this.addMouseListener(new MouseListener() {
			
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(bool){
					x1 = arg0.getX() ;
					y1 = arg0.getY() ;
					bool = false ;
					System.out.println("绗竴娆＄偣鍑伙細("+arg0.getX()+","+arg0.getY()+")");
				}else{
					x2 = arg0.getX() ;
					y2 = arg0.getY() ;
					bool = true ;
					System.out.println("绗簩娆＄偣鍑伙細("+arg0.getX()+","+arg0.getY()+")");

					double xn = Math.abs(x1-x2) ;
					double yn = Math.abs(y1-y2) ;
					int xyn = (int) Math.round(Math.hypot(xn,yn)*5) ;
					
					String cmd = "adb shell input swipe 180 187 180 187 "+xyn ;
					
					try {
						Runtime run = Runtime.getRuntime();
						run.exec(cmd);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					System.out.println(xyn+"==================   缁撴潫       =====================\n");
				}
					
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
