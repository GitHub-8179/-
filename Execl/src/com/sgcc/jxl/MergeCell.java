package com.sgcc.jxl;
import java.io.File;  
import java.io.IOException;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
import jxl.write.Label;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;  
import jxl.write.biff.RowsExceededException;  

/*
 * POI不支持写入图片（jxl支持，但是只支持png格式的图片）
POI对公式的支持比较好，jxl对公式的支持不如POI。所以财务软件用POI较好
多少数据量时出现内存溢出：
使用POI：运行到2800条左右就报内存溢出，使用jxl到3000条左右报内存溢出
读取excel速率：POI优于jxl
插入数据速率：jxl优于POI
*/


public class MergeCell {  
    public static void main(String[] args) throws IOException,  
            RowsExceededException, WriteException, BiffException {  
        // 1、创建WritableWorkbook对象  
        File file = new File("D:/write.xls");  
        WritableWorkbook oWritableBK = Workbook.createWorkbook(file);  
  
        // 2、创建WritableSheet对象  
        WritableSheet oWritableSheet = oWritableBK.createSheet("testsheet1", 0);  
  
        // 3、添加单元格  
        Label label1 = new Label(0, 0, "test1");  
        oWritableSheet.addCell(label1);  
  
        Label label30 = new Label(3, 1, "我是合并后的单元格！");  
        oWritableSheet.addCell(label30);  
  
        //4、 合并单元格  
        oWritableSheet.mergeCells(3,1, 9, 1);// 参数说明，前两个参数为待合并的起始单元格位置，后两个参数用来指定结束单元格位置（列和行）  
        oWritableBK.write();  
        oWritableBK.close();  
    }  
}  