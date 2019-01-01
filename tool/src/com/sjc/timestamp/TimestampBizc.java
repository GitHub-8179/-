package com.sjc.timestamp;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TimestampBizc implements ActionListener{

	public static String  timeStamp = "";
	JTextField field ;
	JButton crop;
	public JPanel intJpanel(){
		
		
		
		JPanel view = new JPanel();//视图
		JLabel label = new JLabel("timestamp:");//文本框
		view.add(label);
		
		field = new JTextField(20);//输入框
		timeStamp = getTimeStamp();
		field.setText(timeStamp);
//      field.addFocusListener(new FocusListenerBizc("", field));//添加焦点事件反映
		field.setEditable(false);//不可编辑
//		field.setOpaque(true);
		field.setBackground( Color.white   ) ;
		view.add(field);
		
		JButton anew = new JButton("重新生成");
		anew.setActionCommand("anew");
		anew.addActionListener(this);
		view.add(anew);
	      
		crop = new JButton("剪切");
		crop.setActionCommand("crop");
		crop.addActionListener(this);
//	      clipboard = win.getToolkit().getSystemClipboard();
	     
	      view.add(crop);
	      
//	      win.add(view);
	      
	      return view;
	      
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		 if("crop".equals(actionCommand))
		  {
			 if(field.getBackground().equals(Color.LIGHT_GRAY)){
				 Object[] options = { "是", "取消" }; 
				 int res = JOptionPane.showOptionDialog(null, "已被使用过，是否继续", "警告",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		            
				 if(res==JOptionPane.YES_OPTION){ 
	                  //点击“是”后执行这个代码块
	            	 StringSelection editText = new StringSelection(timeStamp);
					 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪切板  
					 clipboard.setContents(editText,null);//添加到系统剪切板中
					 field.setBackground( Color.LIGHT_GRAY   ) ;
    	         }else{
                     //点击“否”后执行这个代码块
                     return;
    	         	   } 

		            
			 }else{
				 crop.setEnabled(false);
				 StringSelection editText = new StringSelection(timeStamp);
				 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//获取系统剪切板  
				 clipboard.setContents(editText,null);//添加到系统剪切板中
				 field.setBackground( Color.LIGHT_GRAY   ) ;
			 }
		  }else{
			  crop.setEnabled(true);
			  timeStamp = getTimeStamp();
			  field.setBackground( Color.white   ) ;
			  field.setText(timeStamp);
		  }
		
	}
     
	
	public static String getTimeStamp(){
		Date date = new Date();
		String dateTest = new SimpleDateFormat("yyMMddHHmmSSS").format(date);
		return dateTest;
	}
}
