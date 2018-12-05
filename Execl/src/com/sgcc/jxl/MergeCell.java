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
 * POI��֧��д��ͼƬ��jxl֧�֣�����ֻ֧��png��ʽ��ͼƬ��
POI�Թ�ʽ��֧�ֱȽϺã�jxl�Թ�ʽ��֧�ֲ���POI�����Բ��������POI�Ϻ�
����������ʱ�����ڴ������
ʹ��POI�����е�2800�����Ҿͱ��ڴ������ʹ��jxl��3000�����ұ��ڴ����
��ȡexcel���ʣ�POI����jxl
�����������ʣ�jxl����POI
*/


public class MergeCell {  
    public static void main(String[] args) throws IOException,  
            RowsExceededException, WriteException, BiffException {  
        // 1������WritableWorkbook����  
        File file = new File("D:/write.xls");  
        WritableWorkbook oWritableBK = Workbook.createWorkbook(file);  
  
        // 2������WritableSheet����  
        WritableSheet oWritableSheet = oWritableBK.createSheet("testsheet1", 0);  
  
        // 3����ӵ�Ԫ��  
        Label label1 = new Label(0, 0, "test1");  
        oWritableSheet.addCell(label1);  
  
        Label label30 = new Label(3, 1, "���Ǻϲ���ĵ�Ԫ��");  
        oWritableSheet.addCell(label30);  
  
        //4�� �ϲ���Ԫ��  
        oWritableSheet.mergeCells(3,1, 9, 1);// ����˵����ǰ��������Ϊ���ϲ�����ʼ��Ԫ��λ�ã���������������ָ��������Ԫ��λ�ã��к��У�  
        oWritableBK.write();  
        oWritableBK.close();  
    }  
}  