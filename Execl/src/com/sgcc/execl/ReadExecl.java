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
 
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行 
 
     * @param file 读取数据的源Excel 
 
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1 
 
     * @return 读出的Excel中数据的内容 
 
     * @throws FileNotFoundException 
 
     * @throws IOException 
 
     */  
      
    public static List<String[][]> getData(File file,int ignoreRows) throws IOException{  
        //返回所有工作表的数据  
        List<String[][]> result = new ArrayList<String[][]>();  
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
        POIFSFileSystem  fs = new POIFSFileSystem(in);  
        HSSFWorkbook wb = new HSSFWorkbook(fs);  
        HSSFCell cell = null;  
        wb.getNumberOfSheets();  
        //多个工作表  
        for(int i=0;i<wb.getNumberOfSheets();i++){  
            //得到工作表  
            HSSFSheet hf = wb.getSheetAt(i);  
            //一个工作表的数据   挤得加上1  比如execl加上第一行的列名 总共66  但是getLastRowNum（）是65 把列明也算上  
            String[][] rowResult = new String[hf.getLastRowNum()+1][7];  
            //每个工作表的多行  
            for(int rownumber = ignoreRows; rownumber<=hf.getLastRowNum();rownumber++){  
                  
                  
                //得到的row的行数不确定的   如果那一行后面有空格  有可能会忽略空格列  
                HSSFRow row = hf.getRow(rownumber);  
                if (row == null) {  
                    continue;  
                }  
                //一行的数据  
                String[] colResult = new String[row.getLastCellNum()];  
                //得到一行的多个列  
                /** 
                 * 这里有个问题  就是row.getLastCellNum()有个情况得到的不准确   
                 * 例子：EXECL总共7列数据   但是实际上最后几列有的为空，它会默认把空的列不计入列值； 
                 * 导致的错误就是你调用写入方法的时候会有用的列值  会出错； 
                 *  
                 */  
                for(short colnumber = 0;colnumber<row.getLastCellNum();colnumber++){  
                        String value="";  
                    cell=row.getCell(colnumber);  
                    //将cell装换类型  
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
                         // 导入时如果为公式生成的数据则无值  
  
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
                }//for()列  
                rowResult[rownumber]=colResult;  
            }//for() 行  
            if(rowResult!=null)  
            result.add(rowResult);  
        }//for工作表  
        in.close();  
          
        return result;  
    }  
      
      
    /** 
 
     * 去掉字符串右边的空格 
 
     * @param str 要处理的字符串 
 
     * @return 处理后的字符串 
 
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
    	         if(file!=null){ //判断文件是否为空 不为空 就进行解析  
    	               
    	             //Excel 2003  
    	             if(true){   
//    	                POIFSFileSystem poiFileSystem = new POIFSFileSystem(file.getInputStream());
    	                
    	                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));  
    	                POIFSFileSystem  poiFileSystem = new POIFSFileSystem(in);  
//    	                Workbook  book = new XSSFWorkbook(file);
    	                //得到文档对象  
    	                HSSFWorkbook workbook = new HSSFWorkbook(poiFileSystem);  
    	                //得到第一个表单  
    	                HSSFSheet aSheet = workbook.getSheetAt(0);  
    	                  
    	                String msg="";  
    	                if (aSheet == null) {  
//    	                    logger.error("workbook.getSheetAt(0) is null!");  
    	                    msg="上传的文档中没有内容！";  
    	                    return SUCCESS;  
    	                }  
    	                //得到最后一行的 行号  
    	                int lastRow = aSheet.getLastRowNum();  
    	                if(lastRow > 5000){  
    	                    msg= "文档记录超过了5000条，请拆分文档，<B>拆分时请保证同一订单号的订单在一个文件里</B>";  
//    	                    request.setAttribute("msg", msg);  
    	                    return SUCCESS;  
    	                }  
    	                //存放错误信息的集合  
    	                List<String> errorList=new ArrayList<String>();  
    	                 //从0 开始  跳过第一列（不处理表头）  
    	                int amount=lastRow;  
    	                //调整记录成功的条数   
    	                int samount=0;    
    	                //调整失败的记录数  
    	                int famount=0;   
    	                  
    	                //循环取出每行 然后 循环每一列  
    	                for(int i=1;i<=lastRow;i++){  
    	                    HSSFRow row=aSheet.getRow(i); //得到 第 n 行  
    	                    int lastCell=row.getLastCellNum(); //得到 n行的总列数  
    	                    if(lastCell!=6){ //不是 6 的就有问题  
    	                        errorList.add("第【"+(row.getRowNum()+1)+"】行,总列数不正确!");  
    	                        famount++;  
    	                        continue; //结束这次循环  
    	                    }else{  
    	                        boolean flag=true; //默认是执行 调整的 如果 用户名为空就不执行了  
    	                        String [] params=new String[6]; //用户保存 一行记录中的 6个值  
    	                        for(int j=0;j<lastCell;j++){  
    	                            HSSFCell cell=  row.getCell(j); //得到每行  第 n列  
    	                            if(j==0 && cell==null){ //判断第一个用户名是否为空 如果为空 就跳出这次循环 不进行用户  
    	                                errorList.add("第【"+(row.getRowNum()+1)+"】行【"+(j+1)+"】列,用户名为空!");  
    	                                famount++;  
    	                                flag=false;  
    	                                break;  
    	                            }else{  
    	                                 String param= getCellValue(cell); //解析当前列的值  
    	                                 params[j]=param;  
    	                            }  
    	                        }  
//    	                        if(flag){  
//    	                            //经过上面的处理后 获取到每一行的 6个值  然后进行 修改 用户的财富  
//    	                            int result=upUsermoneyBag(sessionuser,params);  
//    	                            if(result==1){  
//    	                                samount++;  
//    	                            }else if(result==0){  
//    	                                errorList.add("第【"+(row.getRowNum()+1)+"】行,调整财富失败!");  
//    	                                famount++;  
//    	                            }else if(result==-1){  
//    	                                errorList.add("第【"+(row.getRowNum()+1)+"】行,用户名不存在！");  
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
      * @return 得到当前列的值 
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
      * @param sessionuser 操作者 
      * @param params Excel表格中 一行的 6列值 
      * @return 1 成功  0 失败   -1 不存在这个用户 
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
//                     biMessage.setContent(sessionuser.getUsername()+"调整用户“"+user.getLoginname()+"”的财富，现金"+(cashback+nscashback)+"，贝壳"+(credits+nscredits)+" 。用户的IP是"+user.getYahooim()+"，用户最后登录时间"+MyUtils.getTimeString(user.getLastlogin()));  
//                     biMessage.setTitle(user.getLoginname() + "调整财富提醒");  
//                     biMessage.setType("sys");  
//                     biMessage.setFromuser("系统");  
//                     biMessage.setTouser("购物狂");  
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
