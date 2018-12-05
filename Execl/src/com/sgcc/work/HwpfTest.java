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
 * �����ı��滻
 * ���ʺ���doc�ļ�
 * @author ��
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
	      //��range��Χ�ڵ�${reportDate}�滻Ϊ��ǰ������  
	      range.replaceText("${reportDate}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));  
	      range.replaceText("${appleAmt}", "100.00");  
	      range.replaceText("${bananaAmt}", "200.00");  
	      range.replaceText("${totalAmt}", "300.00");  
	      range.replaceText("${Name}", "300.00");  
	      range.replaceText("${aj}", "300.00");  
	      OutputStream os = new FileOutputStream("C:\\Users\\songjinchen\\Desktop\\5.doc");  
	      //��doc������������  
	      doc.write(os);  
	      closeStream(os);  
	      closeStream(is);  
	   }  
	    
	   /** 
	    * �ر������� 
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
	    * �ر������ 
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