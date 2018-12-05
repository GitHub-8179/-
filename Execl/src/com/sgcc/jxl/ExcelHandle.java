package com.sgcc.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelHandle {

	    /**
	     * @param args
	*/
	    public static void main(String[] args) {
	        ExcelHandle excelHandle = new ExcelHandle();
	        excelHandle.writeExcel();

	    }

	    /**
	     * 写入Excel
	     * 
	*/
	    public void writeExcel() {
	        try {
	            //写入到那个Excel文件 如：c:\\hello.xls,或者hello.xls(这个是在项目的根目录下)
	            WritableWorkbook wwb = Workbook.createWorkbook(new File("C:\\Users\\songjinchen\\Desktop\\1.xlsx"));
	            // 创建Excel工作表 指定名称和位置
	            WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
	            // 设置表格的列宽度
	            ws.setColumnView(0, 14);//第一列宽14
	            ws.setColumnView(1, 12);
	            ws.setColumnView(2, 25);
	            ws.setColumnView(3, 20);
	            ws.setColumnView(4, 12);
	            ws.setColumnView(5, 9);
	            ws.setColumnView(6, 12);//第7列宽12

	// **************往工作表中添加数据*****************

	//定义字体格式：字体为：微软雅黑，24号子，加粗
	            WritableFont titleFont = new WritableFont(WritableFont
	                    .createFont("微软雅黑"), 24, WritableFont.NO_BOLD);
	            WritableFont contentFont = new WritableFont(WritableFont
	                    .createFont("楷体 _GB2312"), 12, WritableFont.NO_BOLD);
	            
	            WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
	            WritableCellFormat contentFormat = new WritableCellFormat(
	                    contentFont);
	            WritableCellFormat contentFormat2 = new WritableCellFormat(
	                    contentFont);

	            contentFormat.setBorder(Border.ALL, BorderLineStyle.THIN,
	                    Colour.BLACK);
	            //设置格式居中对齐
	            titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
	            contentFormat2.setAlignment(jxl.format.Alignment.CENTRE);

	            // ***************将定义好的单元格添加到工作表中*****************
	            ws.mergeCells(0, 0, 6, 0);// 合并单元格A-G共7列
	            ws.addCell(new Label(0, 0, "广州XXX大学2009级研究生课程考试成绩册", titleFormat));
	            ws.addCell(new Label(0, 1, "课程名称", contentFormat2));
	            ws.mergeCells(1, 1, 6, 1);// 合并单元格B-G共6列
	            ws.addCell(new Label(1, 1, "大学数学", contentFormat2));
	            ws.addCell(new Label(0, 2, "院所教研室", contentFormat2));
	            ws.mergeCells(1, 2, 6, 2);// 合并单元格B-G共6列
	            ws.addCell(new Label(0, 3, "填表人", contentFormat2));
	            ws.addCell(new Label(2, 3, "教研室负责人", contentFormat2));

	            String th[] = { "学号", "姓名", "学院", "平时成绩", "期末成绩", "总成绩", "补考成绩" };
	            for (int i = 0; i < 7; i++) {
	                ws.addCell(new Label(i, 4, th[i], contentFormat2));
	            }
	            //这里我们可以从数据库里面查询数据，然后在这里获取数据
	            int xh = 200901;
	            String xm = "王佳佳";
	            String xy = "XXX信息技术学院";
	            String space = " ";
	            int cj = 50;
	            String bk = "补 80";
	            //向Excel中插入数据
	            for (int j = 5; j < 10; j++) {
	                ws.addCell(new Label(0, j, "" + xh + j + "", contentFormat));
	                ws.addCell(new Label(1, j, xm+j , contentFormat));
	                ws.addCell(new Label(2, j,  xy , contentFormat));
	                ws.addCell(new Label(3, j, space , contentFormat));
	                ws.addCell(new Label(4, j, space, contentFormat));
	                ws.addCell(new Label(5, j, "" + cj + j + "", contentFormat));
	                ws.addCell(new Label(6, j, "" + bk + "", contentFormat));
	            }
	            // 写入工作表完毕，关闭流
	            wwb.write();
	            wwb.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    
	    
	}
