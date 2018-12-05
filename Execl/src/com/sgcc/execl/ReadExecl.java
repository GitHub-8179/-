package com.sgcc.execl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;


public class ReadExecl {  
	  
    /*private String fileUrl; 
    
    public ReadExecl(String fileUrl) { 
        // TODO Auto-generated constructor stub 
        this.fileUrl = fileUrl; 
    }*/  
   // File file = new File(fileUrl);  
      
    /** 
 
     * ��ȡExcel�����ݣ���һά����洢����һ���и��е�ֵ����ά����洢���Ƕ��ٸ��� 
 
     * @param file ��ȡ���ݵ�ԴExcel 
 
     * @param ignoreRows ��ȡ���ݺ��Ե�������������ͷ����Ҫ���� ���Ե�����Ϊ1 
 
     * @return ������Excel�����ݵ����� 
 
     * @throws FileNotFoundException 
 
     * @throws IOException 
 
     */  
      
    public static List<String[][]> getData(File file,int ignoreRows) throws IOException{  
        //�������й����������  
        List<String[][]> result = new ArrayList<String[][]>();  
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
        POIFSFileSystem  fs = new POIFSFileSystem(in);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFCell cell = null;  
        wb.getNumberOfSheets();  
        //���������  
        for(int i=0;i<wb.getNumberOfSheets();i++){  
            //�õ�������  
            HSSFSheet hf = wb.getSheetAt(i);  
            //һ�������������   ���ü���1  ����execl���ϵ�һ�е����� �ܹ�66  ����getLastRowNum������65 ������Ҳ����  
            String[][] rowResult = new String[hf.getLastRowNum()+1][7];  
            //ÿ��������Ķ���  
            for(int rownumber = ignoreRows; rownumber<=hf.getLastRowNum();rownumber++){  
                  
                  
                //�õ���row��������ȷ����   �����һ�к����пո�  �п��ܻ���Կո���  
                HSSFRow row = hf.getRow(rownumber);  
                if (row == null) {  
                    continue;  
                }  
                //һ�е�����  
                String[] colResult = new String[row.getLastCellNum()];  
                //�õ�һ�еĶ����  
                /** 
                 * �����и�����  ����row.getLastCellNum()�и�����õ��Ĳ�׼ȷ   
                 * ���ӣ�EXECL�ܹ�7������   ����ʵ����������е�Ϊ�գ�����Ĭ�ϰѿյ��в�������ֵ�� 
                 * ���µĴ�����������д�뷽����ʱ������õ���ֵ  ����� 
                 *  
                 */  
                for(short colnumber = 0;colnumber<row.getLastCellNum();colnumber++){  
                        String value="";  
                    cell=row.getCell(colnumber);  
                    //��cellװ������  
                    if(cell!=null){  
//                        cell.setEncoding(HSSFCell.ENCODING_UTF_16);  
                        switch(cell.getCellType()){  
                        case HSSFCell.CELL_TYPE_STRING:  
                            value = cell.getStringCellValue();  
                            break;  
                        case HSSFCell.CELL_TYPE_NUMERIC:  
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {  
  
                                Date date = cell.getDateCellValue();  
  
                                if (date != null) {  
  
                                    value = new SimpleDateFormat("yyyy-MM-dd")  
  
                                           .format(date);  
  
                                } else {  
  
                                    value = "";  
  
                                }  
  
                             } else {  
  
                                value = new DecimalFormat("0").format(cell  
  
                                       .getNumericCellValue());  
  
                             }  
  
                             break;  
                        case HSSFCell.CELL_TYPE_FORMULA:  
                         // ����ʱ���Ϊ��ʽ���ɵ���������ֵ  
  
                            if (!cell.getStringCellValue().equals("")) {  
  
                               value = cell.getStringCellValue();  
  
                            } else {  
  
                               value = cell.getNumericCellValue() + "";  
  
                            }  
  
                            break;  
                        
                        case HSSFCell.CELL_TYPE_BLANK:  
  
                            break;  
  
                        case HSSFCell.CELL_TYPE_ERROR:  
  
                            value = "";  
  
                            break;  
  
                        case HSSFCell.CELL_TYPE_BOOLEAN:  
  
                            value = (cell.getBooleanCellValue() == true ? "Y"  
  
                                   : "N");  
  
                            break;  
  
                        default:  
  
                            value = "";  
                        }//switch  
                          
                    }//if  
                    if (colnumber == 0 && value.trim().equals("")) {  
                       // break;  
                     }  
                      System.out.println(value);
                    colResult[colnumber]=rightTrim(value);  
                }//for()��  
                rowResult[rownumber]=colResult;  
            }//for() ��  
            if(rowResult!=null)  
            result.add(rowResult);  
        }//for������  
        in.close();  
          
        return result;  
    }  
      
      
    /** 
 
     * ȥ���ַ����ұߵĿո� 
 
     * @param str Ҫ������ַ��� 
 
     * @return �������ַ��� 
 
     */  
  
     public static String rightTrim(String str) {  
  
       if (str == "") {  
  
           return "";  
  
       }  
  
       int length = str.length();  
  
       for (int i = length - 1; i >= 0; i--) {  
  
           if (str.charAt(i) != 0x20) {  
  
              break;  
  
           }  
  
           length--;  
  
       }  
  
       return str.substring(0, length);  
  
    }  
     
     
     public String batchRegulateMoneybag(File file){  
    	 String SUCCESS = null;
    	     try {  
    	         if(file!=null){ //�ж��ļ��Ƿ�Ϊ�� ��Ϊ�� �ͽ��н���  
    	               
    	             //Excel 2003  
    	             if(true){   
//    	                POIFSFileSystem poiFileSystem = new POIFSFileSystem(file.getInputStream());
    	                
    	                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
    	                POIFSFileSystem  poiFileSystem = new POIFSFileSystem(in);  
//    	                Workbook  book = new XSSFWorkbook(file);
    	                //�õ��ĵ�����  
    	                HSSFWorkbook workbook = new HSSFWorkbook(poiFileSystem);  
    	                //�õ���һ����  
    	                HSSFSheet aSheet = workbook.getSheetAt(0);  
    	                  
    	                String msg="";  
    	                if (aSheet == null) {  
//    	                    logger.error("workbook.getSheetAt(0) is null!");  
    	                    msg="�ϴ����ĵ���û�����ݣ�";  
    	                    return SUCCESS;  
    	                }  
    	                //�õ����һ�е� �к�  
    	                int lastRow = aSheet.getLastRowNum();  
    	                if(lastRow > 5000){  
    	                    msg= "�ĵ���¼������5000���������ĵ���<B>���ʱ�뱣֤ͬһ�����ŵĶ�����һ���ļ���</B>";  
//    	                    request.setAttribute("msg", msg);  
    	                    return SUCCESS;  
    	                }  
    	                //��Ŵ�����Ϣ�ļ���  
    	                List<String> errorList=new ArrayList<String>();  
    	                 //��0 ��ʼ  ������һ�У��������ͷ��  
    	                int amount=lastRow;  
    	                //������¼�ɹ�������   
    	                int samount=0;    
    	                //����ʧ�ܵļ�¼��  
    	                int famount=0;   
    	                  
    	                //ѭ��ȡ��ÿ�� Ȼ�� ѭ��ÿһ��  
    	                for(int i=1;i<=lastRow;i++){  
    	                    HSSFRow row=aSheet.getRow(i); //�õ� �� n ��  
    	                    int lastCell=row.getLastCellNum(); //�õ� n�е�������  
    	                    if(lastCell!=6){ //���� 6 �ľ�������  
    	                        errorList.add("�ڡ�"+(row.getRowNum()+1)+"����,����������ȷ!");  
    	                        famount++;  
    	                        continue; //�������ѭ��  
    	                    }else{  
    	                        boolean flag=true; //Ĭ����ִ�� ������ ��� �û���Ϊ�վͲ�ִ����  
    	                        String [] params=new String[6]; //�û����� һ�м�¼�е� 6��ֵ  
    	                        for(int j=0;j<lastCell;j++){  
    	                            HSSFCell cell=  row.getCell(j); //�õ�ÿ��  �� n��  
    	                            if(j==0 && cell==null){ //�жϵ�һ���û����Ƿ�Ϊ�� ���Ϊ�� ���������ѭ�� �������û�  
    	                                errorList.add("�ڡ�"+(row.getRowNum()+1)+"���С�"+(j+1)+"����,�û���Ϊ��!");  
    	                                famount++;  
    	                                flag=false;  
    	                                break;  
    	                            }else{  
    	                                 String param= getCellValue(cell); //������ǰ�е�ֵ  
    	                                 params[j]=param;  
    	                            }  
    	                        }  
//    	                        if(flag){  
//    	                            //��������Ĵ���� ��ȡ��ÿһ�е� 6��ֵ  Ȼ����� �޸� �û��ĲƸ�  
//    	                            int result=upUsermoneyBag(sessionuser,params);  
//    	                            if(result==1){  
//    	                                samount++;  
//    	                            }else if(result==0){  
//    	                                errorList.add("�ڡ�"+(row.getRowNum()+1)+"����,�����Ƹ�ʧ��!");  
//    	                                famount++;  
//    	                            }else if(result==-1){  
//    	                                errorList.add("�ڡ�"+(row.getRowNum()+1)+"����,�û��������ڣ�");  
//    	                                famount++;  
//    	                            }  
//    	                        }  
    	                    }  
    	                }  
    	             }  
    	          }  
    	        } catch (Exception e) {  
    	            e.printStackTrace();  
    	        }  
    	        return SUCCESS;  
    	    }  
      
     /** 
      * @author LuoB. 
      * @return �õ���ǰ�е�ֵ 
      */  
     private String getCellValue(HSSFCell cell){  
         String param="";  
         if(cell==null){  
             param="0";  
         }else{  
             int type=cell.getCellType();  
             switch (type) {  
             case 0:  
                 Double d=cell.getNumericCellValue();  
                 param=d.toString();  
                 break;  
             case 1:  
                 param=cell.getStringCellValue();  
                 break;  
             default:  
                 param="0";  
                 break;  
             }  
         }  
         return param;  
     }  
     
     
     /** 
      * @param sessionuser ������ 
      * @param params Excel����� һ�е� 6��ֵ 
      * @return 1 �ɹ�  0 ʧ��   -1 ����������û� 
      */  
//     private int upUsermoneyBag(User sessionuser,String [] params){  
//         try{  
//             if(params.length==6){  
//                 BiUser user=iBiUserService.findBiUserByName(params[0]);  
//                 Double cashback=Util.parseDouble(params[1]);  
//                 /*String cstr=params[2]; 
//                 cstr=cstr.contains(".")?cstr.substring(0,cstr.indexOf(".")):cstr;*/  
//                 Long credits=Util.parseLong(params[2]);  
//                 Double nscashback=Util.parseDouble(params[3]);  
//                 /*String nscstr=params[4]; 
//                 nscstr=nscstr.contains(".")?nscstr.substring(0,nscstr.indexOf(".")):nscstr;*/  
//                 Long nscredits=Util.parseLong(params[4]);  
//                 String note=params[5];  
//                 if(user != null){  
//                     BiMessage biMessage = new BiMessage();  
//                     biMessage.setContent(sessionuser.getUsername()+"�����û���"+user.getLoginname()+"���ĲƸ����ֽ�"+(cashback+nscashback)+"������"+(credits+nscredits)+" ���û���IP��"+user.getYahooim()+"���û�����¼ʱ��"+MyUtils.getTimeString(user.getLastlogin()));  
//                     biMessage.setTitle(user.getLoginname() + "�����Ƹ�����");  
//                     biMessage.setType("sys");  
//                     biMessage.setFromuser("ϵͳ");  
//                     biMessage.setTouser("�����");  
//                     biMessage.setTouserid(19717165l);  
//                     biMessage.setDownflag("0");  
//                     iMessageService.sendTextMsg(biMessage);  
//                     iBiUserService.regulateMoneybag(user, cashback, credits,nscashback,nscredits, sessionuser.getUsername(), note);  
//                     return 1;  
//                 }  
//                 else{  
//                     return -1;  
//                 }  
//             }  
//             return 0;  
//         }catch(Exception e){  
//             e.printStackTrace();  
//             return 0;  
//         }  
//     }  
}  
