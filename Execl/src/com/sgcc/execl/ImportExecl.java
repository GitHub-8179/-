package com.sgcc.execl;


import java.io.File;  
  
import java.io.FileInputStream;  
  
import java.io.IOException;  
  
import java.io.InputStream;  
  
import java.util.ArrayList;  
  
import java.util.List;  
  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
  
import org.apache.poi.ss.usermodel.Cell;  
  
import org.apache.poi.ss.usermodel.Row;  
  
import org.apache.poi.ss.usermodel.Sheet;  
  
import org.apache.poi.ss.usermodel.Workbook;  
  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
/** 
 *  
 * @����������excel��ȡ 
 *  
 *               �����jar�� 
 *  
 *               poi-3.8-beta3-20110606.jar 
 *  
 *               poi-ooxml-3.8-beta3-20110606.jar 
 *  
 *               poi-examples-3.8-beta3-20110606.jar 
 *  
 *               poi-excelant-3.8-beta3-20110606.jar 
 *  
 *               poi-ooxml-schemas-3.8-beta3-20110606.jar 
 *  
 *               poi-scratchpad-3.8-beta3-20110606.jar 
 *  
 *               xmlbeans-2.3.0.jar 
 *  
 *               dom4j-1.6.1.jar 
 *  
 *               jar��������ص�ַ��http://poi.apache.org/download.html 
 *  
 *               ����poi-bin-3.8-beta3-20110606.zipp 
 *  
 * @���ߣ����� 
 *  
 * @ʱ�䣺2012-08-29 ����16:27:15 
 */  
  
public class ImportExecl  
{  
  
    /** ������ */  
  
    private int totalRows = 0;  
  
    /** ������ */  
  
    private int totalCells = 0;  
  
    /** ������Ϣ */  
  
    private String errorInfo;  
  
    /** ���췽�� */  
  
    public ImportExecl()  
    {  
  
    }  
  
    /** 
     *  
     * @�������õ������� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:27:15 
     *  
     * @����@return 
     *  
     * @����ֵ��int 
     */  
  
    public int getTotalRows()  
    {  
  
        return totalRows;  
  
    }  
  
    /** 
     *  
     * @�������õ������� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:27:15 
     *  
     * @����@return 
     *  
     * @����ֵ��int 
     */  
  
    public int getTotalCells()  
    {  
  
        return totalCells;  
  
    }  
  
    /** 
     *  
     * @�������õ�������Ϣ 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:27:15 
     *  
     * @����@return 
     *  
     * @����ֵ��String 
     */  
  
    public String getErrorInfo()  
    {  
  
        return errorInfo;  
  
    }  
  
    /** 
     *  
     * @��������֤excel�ļ� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:27:15 
     *  
     * @����@param filePath���ļ�����·�� 
     *  
     * @����@return 
     *  
     * @����ֵ��boolean 
     */  
  
    public boolean validateExcel(String filePath)  
    {  
  
        /** ����ļ����Ƿ�Ϊ�ջ����Ƿ���Excel��ʽ���ļ� */  
  
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath)))  
        {  
  
            errorInfo = "�ļ�����excel��ʽ";  
  
            return false;  
  
        }  
  
        /** ����ļ��Ƿ���� */  
  
        File file = new File(filePath);  
  
        if (file == null || !file.exists())  
        {  
  
            errorInfo = "�ļ�������";  
  
            return false;  
  
        }  
  
        return true;  
  
    }  
  
    /** 
     *  
     * @����������ļ����ȡexcel�ļ� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:27:15 
     *  
     * @����@param filePath �ļ�����·�� 
     *  
     * @����@return 
     *  
     * @����ֵ��List 
     */  
  
    public List<List<String>> read(String filePath)  
    {  
  
        List<List<String>> dataLst = new ArrayList<List<String>>();  
  
        InputStream is = null;  
  
        try  
        {  
  
            /** ��֤�ļ��Ƿ�Ϸ� */  
  
            if (!validateExcel(filePath))  
            {  
  
                System.out.println(errorInfo);  
  
                return null;  
  
            }  
  
            /** �ж��ļ������ͣ���2003����2007 */  
  
            boolean isExcel2003 = true;  
  
            if (WDWUtil.isExcel2007(filePath))  
            {  
  
                isExcel2003 = false;  
  
            }  
  
            /** ���ñ����ṩ�ĸ������ȡ�ķ��� */  
  
            File file = new File(filePath);  
  
            is = new FileInputStream(file);  
  
            dataLst = read(is, isExcel2003);  
  
            is.close();  
  
        }  
        catch (Exception ex)  
        {  
  
            ex.printStackTrace();  
  
        }  
        finally  
        {  
  
            if (is != null)  
            {  
  
                try  
                {  
  
                    is.close();  
  
                }  
                catch (IOException e)  
                {  
  
                    is = null;  
  
                    e.printStackTrace();  
  
                }  
  
            }  
  
        }  
  
        /** ��������ȡ�Ľ�� */  
  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @�������������ȡExcel�ļ� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:40:15 
     *  
     * @����@param inputStream 
     *  
     * @����@param isExcel2003 
     *  
     * @����@return 
     *  
     * @����ֵ��List 
     */  
  
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003)  
    {  
  
        List<List<String>> dataLst = null;  
  
        try  
        {  
  
            /** ��ݰ汾ѡ�񴴽�Workbook�ķ�ʽ */  
  
            Workbook wb = null;  
  
            if (isExcel2003)  
            {  
                wb = new HSSFWorkbook(inputStream);  
            }  
            else  
            {  
                wb = new XSSFWorkbook(inputStream);  
            }  
            dataLst = read(wb);  
  
        }  
        catch (IOException e)  
        {  
  
            e.printStackTrace();  
  
        }  
  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @��������ȡ��� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:50:15 
     *  
     * @����@param Workbook 
     *  
     * @����@return 
     *  
     * @����ֵ��List<List<String>> 
     */  
  
    private List<List<String>> read(Workbook wb)  
    {  
  
        List<List<String>> dataLst = new ArrayList<List<String>>();  
  
        /** �õ���һ��shell */  
  
        Sheet sheet = wb.getSheetAt(0);  
  
        /** �õ�Excel������ */  
  
        this.totalRows = sheet.getPhysicalNumberOfRows();  
  
        /** �õ�Excel������ */  
  
        if (this.totalRows >= 1 && sheet.getRow(0) != null)  
        {  
  
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
  
        }  
  
        /** ѭ��Excel���� */  
  
        for (int r = 0; r < this.totalRows; r++)  
        {  
  
            Row row = sheet.getRow(r);  
  
            if (row == null)  
            {  
  
                continue;  
  
            }  
  
            List<String> rowLst = new ArrayList<String>();  
  
            /** ѭ��Excel���� */  
  
            for (int c = 0; c < this.getTotalCells(); c++)  
            {  
  
                Cell cell = row.getCell(c);  
  
                String cellValue = "";  
  
                if (null != cell)  
                {  
                    // �������ж���ݵ�����  
                    switch (cell.getCellType())  
                    {  
                    case HSSFCell.CELL_TYPE_NUMERIC: // ����  
                        cellValue = cell.getNumericCellValue() + "";  
                        break;  
  
                    case HSSFCell.CELL_TYPE_STRING: // �ַ�  
                        cellValue = cell.getStringCellValue();  
                        break;  
  
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                        cellValue = cell.getBooleanCellValue() + "";  
                        break;  
  
                    case HSSFCell.CELL_TYPE_FORMULA: // ��ʽ  
                        cellValue = cell.getCellFormula() + "";  
                        break;  
  
                    case HSSFCell.CELL_TYPE_BLANK: // ��ֵ  
                        cellValue = "";  
                        break;  
  
                    case HSSFCell.CELL_TYPE_ERROR: // ����  
                        cellValue = "�Ƿ��ַ�";  
                        break;  
  
                    default:  
                        cellValue = "δ֪����";  
                        break;  
                    }  
                }  
  
                rowLst.add(cellValue);  
  
            }  
  
            /** �����r�еĵ�c�� */  
  
            dataLst.add(rowLst);  
  
        }  
  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @������main���Է��� 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����17:12:15 
     *  
     * @����@param args 
     *  
     * @����@throws Exception 
     *  
     * @����ֵ��void 
     */  
  
    public static void main(String[] args) throws Exception  
    {  
  
        ImportExecl poi = new ImportExecl();  
  
        // List<List<String>> list = poi.read("d:/aaa.xls");  
  
//        List<List<String>> list = poi.read("C:\\Users\\Administrator\\Desktop\\预算表.xlsx");  
        List<List<String>> list = poi.read("C:\\Users\\Administrator\\Desktop\\201802-2018年2月利润表底稿终稿3.2（利润预算目标）.xls");  
       
  
        if (list != null)  
        {  
  
            for (int i = 0; i < list.size(); i++)  
            {  
  
                System.out.print("��" + (i) + "��");  
  
                List<String> cellList = list.get(i);  
  
                for (int j = 0; j < cellList.size(); j++)  
                {  
  
                    // System.out.print("    ��" + (j + 1) + "��ֵ��");  
  
                    System.out.print("    " + cellList.get(j));  
  
                }  
                System.out.println();  
  
            }  
  
        }  
  
    }  
  
}  
  
/** 
 *  
 * @������������ 
 *  
 * @���ߣ����� 
 *  
 * @ʱ�䣺2012-08-29 ����16:30:40 
 */  
  
class WDWUtil  
{  
  
    /** 
     *  
     * @�������Ƿ���2003��excel������true��2003 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:29:11 
     *  
     * @����@param filePath���ļ�����·�� 
     *  
     * @����@return 
     *  
     * @����ֵ��boolean 
     */  
  
    public static boolean isExcel2003(String filePath)  
    {  
  
        return filePath.matches("^.+\\.(?i)(xls)$");  
  
    }  
  
    /** 
     *  
     * @�������Ƿ���2007��excel������true��2007 
     *  
     * @���ߣ����� 
     *  
     * @ʱ�䣺2012-08-29 ����16:28:20 
     *  
     * @����@param filePath���ļ�����·�� 
     *  
     * @����@return 
     *  
     * @����ֵ��boolean 
     */  
  
    public static boolean isExcel2007(String filePath)  
    {  
  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
  
    }  
  
}  