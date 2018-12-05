package com.sgcc.work;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Bookmark;
import org.apache.poi.hwpf.usermodel.Bookmarks;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;


public class HwpfWriteTest {  
    
	public static void main(String[] args) {
		try {
			testReadByDoc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   public static void testReadByDoc() throws Exception {  
	      InputStream is = new FileInputStream("C:\\Users\\songjinchen\\Desktop\\3.doc");  
	      HWPFDocument doc = new HWPFDocument(is);  
	      //�����ǩ��Ϣ  
	      printInfo(doc.getBookmarks());  
	      //����ı�  
	      System.out.println(doc.getDocumentText());  
	      Range range = doc.getRange();  
//	    insertInfo(range);  
	      printInfo(range);  
	      //�����  
	      readTable(range);  
	      //���б�  
	      readList(range);  
	      //ɾ��range  
	      Range r = new Range(2, 5, doc);  
	      r.delete();//���ڴ��н���ɾ���������Ҫ���浽�ļ�����Ҫ�ٰ���д���ļ�  
	      //�ѵ�ǰHWPFDocumentд���������  
	      doc.write(new FileOutputStream("D:\\test.doc"));  
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
	    * �����ǩ��Ϣ 
	    * @param bookmarks 
	    */  
	   private static void printInfo(Bookmarks bookmarks) {  
	      int count = bookmarks.getBookmarksCount();  
	      System.out.println("��ǩ������" + count);  
	      Bookmark bookmark;  
	      for (int i=0; i<count; i++) {  
	         bookmark = bookmarks.getBookmark(i);  
	         System.out.println("��ǩ" + (i+1) + "�������ǣ�" + bookmark.getName());  
	         System.out.println("��ʼλ�ã�" + bookmark.getStart());  
	         System.out.println("����λ�ã�" + bookmark.getEnd());  
	      }  
	   }  
	    
	   /** 
	    * ����� 
	    * ÿһ���س�������һ�����䣬���Զ��ڱ����ԣ�ÿһ����Ԫ�����ٰ���һ�����䣬ÿ�н�������һ�����䡣 
	    * @param range 
	    */  
	   private static void readTable(Range range) {  
	      //����range��Χ�ڵ�table��  
	      TableIterator tableIter = new TableIterator(range);  
	      Table table;  
	      TableRow row;  
	      TableCell cell;  
	      while (tableIter.hasNext()) {  
	         table = tableIter.next();  
	         int rowNum = table.numRows();  
	         for (int j=0; j<rowNum; j++) {  
	            row = table.getRow(j);  
	            int cellNum = row.numCells();  
	            for (int k=0; k<cellNum; k++) {  
	                cell = row.getCell(k);  
	                //�����Ԫ����ı�  
	                System.out.println(cell.text().trim());  
	            }  
	         }  
	      }  
	   }  
	    
	   /** 
	    * ���б� 
	    * @param range 
	    */  
	   private static void readList(Range range) {  
	      int num = range.numParagraphs();  
	      Paragraph para;  
	      for (int i=0; i<num; i++) {  
	         para = range.getParagraph(i);  
//	         if (para.isInList()) {  
//	            System.out.println("list: " + para.text());  
//	         }  
	      }  
	   }  
	    
	   /** 
	    * ���Range 
	    * @param range 
	    */  
	   private static void printInfo(Range range) {  
	      //��ȡ������  
	      int paraNum = range.numParagraphs();  
	      System.out.println(paraNum);  
	      for (int i=0; i<paraNum; i++) {  
//	       insertInfo(range.getParagraph(i));  
	         System.out.println("����" + (i+1) + "��" + range.getParagraph(i).text());  
	         if (i == (paraNum-1)) {  
	            insertInfo(range.getParagraph(i));  
	         }  
	      }  
	      int secNum = range.numSections();  
	      System.out.println(secNum);  
	      Section section;  
	      for (int i=0; i<secNum; i++) {  
	         section = range.getSection(i);  
	         System.out.println(section.getMarginLeft());  
	         System.out.println(section.getMarginRight());  
	         System.out.println(section.getMarginTop());  
	         System.out.println(section.getMarginBottom());  
	         System.out.println(section.getPageHeight());  
	         System.out.println(section.text());  
	      }  
	   }  
	    
	   /** 
	    * �������ݵ�Range������ֻ��д���ڴ��� 
	    * @param range 
	    */  
	   private static void insertInfo(Range range) {  
	      range.insertAfter("Hello");  
	   }  
	    
	}  