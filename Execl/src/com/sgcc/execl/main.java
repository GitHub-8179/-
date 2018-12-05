package com.sgcc.execl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
	public static void main(String[] args) throws IOException{  
//		����HSSFWorkbook:�ǲ���Excel2003��ǰ������2003���İ汾����չ����.xls
//
//		����XSSFWorkbook:�ǲ���Excel2007�İ汾����չ����.xlsx
        ReadExecl re = new ReadExecl();  
        File file = new File("C:\\Users\\songjinchen\\Desktop\\1.xls");  
        File file1 = new File("C:\\Users\\songjinchen\\Desktop\\1.xls"); 
        WriterExecl we = new WriterExecl("C:\\Users\\songjinchen\\Desktop\\1.xlsx");  
        List<String[][]> result = new ArrayList<String[][]>();  
        //�������� ��0��ʼ  
//        result =  re.getData(file, 0);  
        re.batchRegulateMoneybag(file1);
//        re.batchRegulateMoneybag(file);
//        //�ж�����  
//        int row = result.get(0).length;  
//  
//         //д��  �������row   ����column  column�ǲ�ȷ����  
//        we.writeEx(row,result.get(0));  
         
    }  
}
