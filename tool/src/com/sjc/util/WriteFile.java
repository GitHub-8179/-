package com.sjc.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WriteFile {

//	public static void main(String[] args) {
////		setIOBufWriter("C:\\Users\\宋\\Desktop","这是测试使用#");
////		setIOBufWriter("C:\\Users\\宋\\Desktop\\","新建文本文档.txt","这是测试使用#");
//		setIoWriter("C:\\Users\\宋\\Desktop\\","新建文本文档.txt","这是测试使用#",23,32);
//	}
	
	public static boolean setIoWriter(String path,String name,String content,int startNum,int num){
		BufferedWriter bos=null;
		try {
			File file=new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			File file1=new File(path,name);
//			File file1=new File(path+"\\"+name);
			if(file1.exists()){
				file1.delete();
			}
			bos=new BufferedWriter(new FileWriter(file1,true));
			int nu = startNum+num;
			for (int i = startNum; i < nu; i++) {
				bos.write(content.replace("#", i+""));
				bos.newLine();
			}
			bos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(bos==null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void setIOBufWriter(String path,String name,String content){
	    
		FileOutputStream out=null;
		try {
				File file=new File(path);
				if(!file.exists()){
					file.mkdirs();
				}
				File file1=new File(path+name);
				if(file1.exists()){
					file1.delete();
				}
				out=new FileOutputStream(file1,true);        
				for(int i=0;i<10;i++){
				    StringBuffer sb=new StringBuffer();
				    sb.append(content.replace("#", i+""));
				    sb.append("\r\n");
				    out.write(sb.toString().getBytes("utf-8"));
				}        
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(out==null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	   }
		
}
