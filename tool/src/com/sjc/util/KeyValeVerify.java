package com.sjc.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class KeyValeVerify implements KeyListener {

	int leng;
	JTextComponent jtf;
	int type ;
//	String oldValue="";
	
	public KeyValeVerify(int leng,JTextComponent jtf,int type) {
		this.leng = leng;
		this.jtf = jtf;
		this.type = type ;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		manage(e);
	}

	//按下某个键时调用此方法。
	@Override
	public void keyPressed(KeyEvent e) {

	}

	//释放某个键时调用此方法。
	@Override
	public void keyReleased(KeyEvent e) {
//		manage(e);
		// TODO	manage(e); Auto-generated method stub
	}
	
	public void manage(KeyEvent e){
		int keyChar=e.getKeyChar();
		String tex = jtf.getText() ;
		switch (type) {
		case 0:
			System.out.println(tex.length());
			if (tex.length()<leng&&keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
				
			} else {
				e.consume();  
			}
			break;
		case 1:
			
			if (tex.length()<leng&&keyChar!=22) {

			} else {
				e.consume();  
			}
			break;
		default:
			break;
		}
		
	}

}
