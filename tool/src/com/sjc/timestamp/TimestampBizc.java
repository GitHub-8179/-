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
		
		
		
		JPanel view = new JPanel();//��ͼ
		JLabel label = new JLabel("timestamp:");//�ı���
		view.add(label);
		
		field = new JTextField(20);//�����
		timeStamp = getTimeStamp();
		field.setText(timeStamp);
//      field.addFocusListener(new FocusListenerBizc("", field));//��ӽ����¼���ӳ
		field.setEditable(false);//���ɱ༭
//		field.setOpaque(true);
		field.setBackground( Color.white   ) ;
		view.add(field);
		
		JButton anew = new JButton("��������");
		anew.setActionCommand("anew");
		anew.addActionListener(this);
		view.add(anew);
	      
		crop = new JButton("����");
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
				 Object[] options = { "��", "ȡ��" }; 
				 int res = JOptionPane.showOptionDialog(null, "�ѱ�ʹ�ù����Ƿ����", "����",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		            
				 if(res==JOptionPane.YES_OPTION){ 
	                  //������ǡ���ִ����������
	            	 StringSelection editText = new StringSelection(timeStamp);
					 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//��ȡϵͳ���а�  
					 clipboard.setContents(editText,null);//��ӵ�ϵͳ���а���
					 field.setBackground( Color.LIGHT_GRAY   ) ;
    	         }else{
                     //������񡱺�ִ����������
                     return;
    	         	   } 

		            
			 }else{
				 crop.setEnabled(false);
				 StringSelection editText = new StringSelection(timeStamp);
				 Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();//��ȡϵͳ���а�  
				 clipboard.setContents(editText,null);//��ӵ�ϵͳ���а���
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
