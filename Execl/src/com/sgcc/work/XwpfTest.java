package com.sgcc.work;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *  * �����ı��滻
 * ���ʺ���docx�ļ�
 * @author ��
 *
 */
public class XwpfTest {

	
	public static void main(String[] args) {
		try {
			testTemplateWrite();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  /**
   * ��һ��docx�ĵ���Ϊģ�壬Ȼ���滻���е����ݣ���д��Ŀ���ĵ��С�
   * @throws Exception
   */
  public static void testTemplateWrite() throws Exception {
	  InputStream isiamge = new FileInputStream("C:\\Users\\songjinchen\\Desktop\\3.docx");
	  byte[] buffer = new byte[1444];  
      int byteread = 0;  
      int bytesum = 0;  
      while ( (byteread = isiamge.read(buffer)) != -1) {  
          bytesum += byteread; //�ֽ��� �ļ���С  
          System.out.println(bytesum);  
      }  
      
     Map<String, Object> params = new HashMap<String, Object>();
     params.put("appleAmt", "100.00");
     params.put("bananaAmt", "200.00");
     params.put("totalAmt", "300.00");
     params.put("aj", "300.00");  
     params.put("reportDate", "2014-02-28");
     params.put("reportDate1", "2014-03-28");
     params.put("Name", bytesum);  
     params.put("X����", "haohao");
     String filePath = "C:\\Users\\songjinchen\\Desktop\\2.docx";
     InputStream is = new FileInputStream(filePath);
     XWPFDocument doc = new XWPFDocument(is);
     //�滻��������ı���
     replaceInPara(doc, params);
     //�滻�������ı���
    replaceInTable(doc, params);
     OutputStream os = new FileOutputStream("C:\\Users\\songjinchen\\Desktop\\1.doc");
     doc.write(os);
     close(os);
     close(is);
  }
 
  /**
   * �滻��������ı���
   * @param doc Ҫ�滻���ĵ�
   * @param params ����
   */
  private static void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
     Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
     XWPFParagraph para;
     while (iterator.hasNext()) {
        para = iterator.next();
        replaceInPara(para, params);
     }
  }
 
  /**
   * �滻��������ı���
   * @param para Ҫ�滻�Ķ���
   * @param params ����
   */
  private static void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
     List<XWPFRun> runs;
     Matcher matcher;
     if (matcher(para.getParagraphText()).find()) {
        runs = para.getRuns();
        for (int i=0; i<runs.size(); i++) {
           XWPFRun run = runs.get(i);
           String runText = run.toString();
           matcher = matcher(runText);
           if (matcher.find()) {
               while ((matcher = matcher(runText)).find()) {
                  runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
               }
               //ֱ�ӵ���XWPFRun��setText()���������ı�ʱ���ڵײ�����´���һ��XWPFRun�����ı������ڵ�ǰ�ı����棬
               //�������ǲ���ֱ����ֵ����Ҫ��ɾ����ǰrun,Ȼ�����Լ��ֶ�����һ���µ�run��
               para.removeRun(i);
               para.insertNewRun(i).setText(runText);
           }
        }
     }
  }
 
  /**
   * �滻�������ı���
   * @param doc Ҫ�滻���ĵ�
   * @param params ����
   */
  private static void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
     Iterator<XWPFTable> iterator = doc.getTablesIterator();
     XWPFTable table;
     List<XWPFTableRow> rows;
     List<XWPFTableCell> cells;
     List<XWPFParagraph> paras;
     while (iterator.hasNext()) {
        table = iterator.next();
        rows = table.getRows();
        for (XWPFTableRow row : rows) {
           cells = row.getTableCells();
           for (XWPFTableCell cell : cells) {
               paras = cell.getParagraphs();
               for (XWPFParagraph para : paras) {
                  replaceInPara(para, params);
               }
           }
        }
     }
  }
 
  /**
   * ����ƥ���ַ���
   * @param str
   * @return
   */
  private static Matcher matcher(String str) {
     Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
     Matcher matcher = pattern.matcher(str);
     return matcher;
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
 
  /**
   * �ر������
   * @param os
   */
  private static void close(OutputStream os) {
     if (os != null) {
        try {
           os.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
     }
  }
 
}