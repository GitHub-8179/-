package com.sgcc.work;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class XwpfWriteTest {  
	   
	public static void main(String[] args) {
		try {
			testReadByDoc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   /** 
	    * ͨ��XWPFDocument�����ݽ��з��ʡ�����XWPF�ĵ����ԣ������ַ�ʽ���ж��������ѡ� 
	    * @throws Exception 
	    */  
	   public static void testReadByDoc() throws Exception {  
	      InputStream is = new FileInputStream("C:\\Users\\songjinchen\\Desktop\\2.docx");  
	      XWPFDocument doc = new XWPFDocument(is);  
	      List<XWPFParagraph> paras = doc.getParagraphs();  
	      for (XWPFParagraph para : paras) {  
	         //��ǰ���������  
//	       CTPPr pr = para.getCTP().getPPr();  
	         System.out.println(para.getText());  
	      }  
	      //��ȡ�ĵ������еı��  
	      List<XWPFTable> tables = doc.getTables();  
	      List<XWPFTableRow> rows;  
	      List<XWPFTableCell> cells;  
	      for (XWPFTable table : tables) {  
	         //�������  
//	       CTTblPr pr = table.getCTTbl().getTblPr();  
	         //��ȡ����Ӧ����  
	         rows = table.getRows();  
	         for (XWPFTableRow row : rows) {  
	            //��ȡ�ж�Ӧ�ĵ�Ԫ��  
	            cells = row.getTableCells();  
	            for (XWPFTableCell cell : cells) {  
	                System.out.println(cell.getText());;  
	            }  
	         }  
	      }  
	     close(is);  
	   }  
	    
	   /** 
	    * �ر������� 
	    * @param is 
	    */  
	   private static void close(InputStream is) {  
	      if (is != null) {  
	         try {  
	            is.close();  
	         } catch (IOException e) {  
	            e.printStackTrace();  
	         }  
	      }  
	   }  
	    
	}  