package com.sjc.fieldsCopy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.sjc.timestamp.TimestampBizc;
import com.sjc.util.FrequentlyController;
import com.sjc.util.KeyValeVerify;
import com.sjc.util.OutFocusController;
import com.sjc.util.WriteFile;

public class FieldsCopyBizc implements ActionListener{

	JTextField field ;//起始值
	JTextField dataSize ; //数据量 
	JTextField filepathInput ; //文件选择路径 
	JTextField fileNameInput ; //文件名称选择
	
	/**
	 * 设置起始值、数据量、生成文件名
	 * @return
	 */
	public JPanel getViewUp(){
		//设置上面一行条（起始值、数据量）
		JPanel viewUp = new JPanel();//视图
		viewUp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("起始值:");//文本框
		viewUp.add(label);
		field = new JTextField(6);//输入框
		field.setText("1");
		field.addFocusListener(new OutFocusController("1", field));//添加焦点事件反映
		field.addKeyListener(new KeyValeVerify(FrequentlyController.NUM_INPUT_LEN,field,0));
		field.setEditable(true);//不可编辑
		viewUp.add(field);
		
		
		JLabel label1 = new JLabel("数据量:");//文本框
		viewUp.add(label1);
		dataSize = new JTextField(6);//输入框
		dataSize.setText("20");
		dataSize.addFocusListener(new OutFocusController("20", dataSize));//添加焦点事件反映
		dataSize.addKeyListener(new KeyValeVerify(FrequentlyController.NUM_INPUT_LEN,dataSize,0));
		dataSize.setEditable(true);//编辑
		viewUp.add(dataSize);
		
		JLabel label2 = new JLabel("生成文件名:");//文本框
		viewUp.add(label2);
		fileNameInput = new JTextField(13);//输入框
//		fileNameInput.setText(TimestampBizc.timeStamp);
//		fileNameInput.addFocusListener(new OutFocusController(TimestampBizc.timeStamp, fileNameInput));//添加焦点事件反映
		fileNameInput.addKeyListener(new KeyValeVerify(FrequentlyController.FILENAME_INPUT_LEN,fileNameInput,1));
		fileNameInput.setEditable(true);//编辑
		viewUp.add(fileNameInput);
		
		return viewUp ;
	}
	JTextArea testInput;
	/**
	 * 设置生成文件浏览位置、选择按钮、文本框
	 * @return
	 */
	public JPanel getViewConter(){
		
		//设置中间视图选择文件
		JPanel viewCentre = new JPanel();//视图
		viewCentre.setLayout(new BorderLayout());
		
		JPanel viewCentreUp = new JPanel();//视图
		viewCentreUp.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel label2= new JLabel("生成文件位置:");//文本框
		viewCentreUp.add(label2);
		filepathInput = new JTextField(28);//输入框
		filepathInput.setEditable(false);//编辑
//		 File desktop = new File(System.getProperty("user.home")+System.getProperty("file.separator")+"XX");
//		 FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句,获取桌面路径
//		 String path = fsv.getHomeDirectory().getPath() ;
//		filepathInput.setText(path);
		viewCentreUp.add(filepathInput);
		JButton elect = new JButton("选择");
		elect.setActionCommand("elect");
		elect.addActionListener(this);
		viewCentreUp.add(elect);
		viewCentre.add(viewCentreUp,BorderLayout.NORTH);
		
		
		JPanel viewCentreDown = new JPanel();//视图
		viewCentreDown.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("输入复制文本:");//文本框
		viewCentreDown.add(label,FlowLayout.LEFT);
		testInput=new JTextArea(5,35);//文本框
		JScrollPane scroll = new JScrollPane(testInput); //设置滑动框
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		testInput.setVisible(true);
//	    testInput.setSelectedTextColor(Color.RED);
//	    testInput.setLineWrap(true);        //激活自动换行功能 
//	    testInput.setWrapStyleWord(true);            // 激活断行不断字功能
	    testInput.addKeyListener(new KeyValeVerify(FrequentlyController.TEXT_INPUT_LEN,testInput,1));
	    viewCentreDown.add(scroll);
	    viewCentre.add(viewCentreDown,BorderLayout.CENTER);
		
		return viewCentre ;
	}
	public JPanel intJpanel(){
		
		JPanel view = new JPanel();//视图
//		view.setLayout(new FlowLayout(FlowLayout.RIGHT));
		view.setLayout(new BorderLayout());
		view.add(getViewUp(),BorderLayout.NORTH);
		view.add(getViewConter(),BorderLayout.CENTER);
		fcDlg = setJFileChooser();
		view.add(getViewDown(),BorderLayout.SOUTH);
		
		
		return view;
	}
	
	/**
	 * 设置一键生成、退出按钮
	 * @return
	 */
	public JPanel getViewDown(){
		JPanel viewDown = new JPanel();//视图
		
		JButton create = new JButton("一键生成");
		create.setActionCommand("create");
		create.addActionListener(this);
		viewDown.add(create);
		
		JButton exit = new JButton("退出");
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		viewDown.add(exit);
		
		return viewDown ;
	}
	 JFileChooser fcDlg ;
	 
	 /**
	  * 初始化一个文件浏览窗口
	  * @return
	  */
	 public JFileChooser setJFileChooser(){
		 
		  FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句,获取桌面路径
		  filepathInput.setText(fsv.getHomeDirectory().getPath());
		  fcDlg = new JFileChooser();
		  fcDlg.setCurrentDirectory(fsv.getHomeDirectory());//设置初始化获取桌面路径
		  fcDlg.setDialogTitle("选择保存文件路径");
		  fcDlg.setApproveButtonToolTipText("保存选择路径");

		  fcDlg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY  );
		  fcDlg.setDialogTitle("请选择生成文件的路径");
		  fcDlg.setFileFilter(new FileNameExtensionFilter("日志文件 (*.log)", "log"));
		  fcDlg.setFileFilter(new FileNameExtensionFilter("数据文件 (*.sql)", "sql"));
		  fcDlg.setFileFilter(new FileNameExtensionFilter("文本文件 (*.txt)", "txt"));
		  fcDlg.setAcceptAllFileFilterUsed(false);//去掉所有文件选项
		return fcDlg;
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		 if("elect".equals(actionCommand))
		  {
			      int returnVal = fcDlg.showDialog(null,"保存");
			      if (returnVal == JFileChooser.APPROVE_OPTION) {
			        String filepath = fcDlg.getSelectedFile().getPath();
			        filepathInput.setText(filepath);
			      }
		  }else{
			  if("exit".equals(actionCommand)){
				  System.exit(0);
			  }else{
				  if("create".equals(actionCommand)){
					  String fileName = fileNameInput.getText().trim();
					  if(fileName==null||"".equals(fileName)){
						  fileName=TimestampBizc.getTimeStamp();
						  fileNameInput.setText(fileName);
					  }
					  String con = testInput.getText();
					  String filePath = filepathInput.getText();  
					  
					  String startNum = field.getText();
					  int startNum1 = "".equals(startNum.trim())?1: Integer.valueOf(startNum);
					  
					  String num = dataSize.getText();
					  int num1="".equals(num.trim())?0:Integer.valueOf(num);
					  
					  String fileNameSuffix = fcDlg.getFileFilter().getDescription();
					  if("文本文件 (*.txt)".equals(fileNameSuffix)){
						  fileName+=".txt";
					  }else{
						  if("数据文件 (*.sql)".equals(fileNameSuffix)){
							  fileName+=".sql";
						  }else{
							  fileName+=".log";
						  }
					  }
					  
					  File file1=new File(filePath,fileName);
					 
						if(file1.exists()){
								 Object[] options = { "是", "取消" }; 
								 int res = JOptionPane.showOptionDialog(null, "文件已存在，是否删除重新生成？", "警告",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
						            
								 if(res==JOptionPane.YES_OPTION){ 
					                  //点击“是”后执行这个代码块

									 if(WriteFile.setIoWriter(filePath, fileName, con,startNum1,num1)){
										 JOptionPane.showMessageDialog(null, "已生成文件 ："+fileName+"   ");
									 }
								 }else{
				                     //点击“否”后执行这个代码块
				                     return;
				    	         	   } 
						}else{
							if(fileName.length() < 145 && !fileName.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$")){
								JOptionPane.showMessageDialog(null, "请输入正确的文件名", "错误提示", JOptionPane.ERROR_MESSAGE);
								return;
							}
							if(WriteFile.setIoWriter(filePath, fileName, con,startNum1,num1)){
								int ss = "s sf ".indexOf("\\s");
								 JOptionPane.showMessageDialog(null, "已生成文件 ："+fileName+"   ");
							 }
						}
					  
				  }
			  }
		  }
		 
	}
}
