package com.sgcc.work;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

/**
 * 用于文本替换
 * 仅适合与doc文件
 * @author 宋
 *
 */
public class HwpfTest {  
	

	public static void main(String[] args) {
		try {
			testWrite();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	   public static void testWrite() throws Exception {  
	      String templatePath = "C:\\Users\\songjinchen\\Desktop\\3.doc";  
	      InputStream is = new FileInputStream(templatePath);  
	      HWPFDocument doc = new HWPFDocument(is);  
	      Range range = doc.getRange();  
	      //把range范围内的${reportDate}替换为当前的日期  
	      range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
	      range.replaceText("${appleAmt}", "100.00");  
	      range.replaceText("${bananaAmt}", "200.00");  
	      range.replaceText("${totalAmt}", "300.00");  
	      range.replaceText("${Name}", "300.00");  
	      range.replaceText("${aj}", "300.00");  
	      OutputStream os = new FileOutputStream("C:\\Users\\songjinchen\\Desktop\\5.doc");  
	      //把doc输出到输出流中  
	      doc.write(os);  
	      closeStream(os);  
	      closeStream(is);  
	   }  
	    
	   /** 
	    * 关闭输入流 
	    * @param is 
	    */  
	   private static void closeStream(InputStream is) {  
	      if (is != null) {  
	         try {  
	            is.close();  
	         } catch (IOException e) {  
	            e.printStackTrace();  
	         }  
	      }  
	   }  
	   
	   /** 
	    * 关闭输出流 
	    * @param os 
	    */  
	   private static void closeStream(OutputStream os) {  
	      if (os != null) {  
	         try {  
	            os.close();  
	         } catch (IOException e) {  
	            e.printStackTrace();  
	         }  
	      }  
	   }  
	    
	   
	}  