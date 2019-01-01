package com.comm.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Test {
	static Test test = new Test();
	static InputStream in = test.getClass().getResourceAsStream("/db.properties"); 
	public static void main(String[] args) {
		
		Properties prop = new Properties(); 
		try {
			
			prop.load(in); 
			
			 String sqlDriver = prop.getProperty("driver"); //������
		     String url = prop.getProperty("url"); //�������ݿ��URL��ַ
		     String username = prop.getProperty("username"); //���ݿ���û���
		     String password = prop.getProperty("password"); //���ݿ������
			OperationData operationData = new OperationData(sqlDriver,url,username,password);
			List<Object> list = operationData.excuteQuery("select * from inof");

//			List<Object> list = operationData.excuteQuery("select FAIL_TIME as failTime from s_user_inof");
			for (Object object : list) {
				Map<String, Object> map = (Map) object;
				for(Map.Entry<String, Object> ma:map.entrySet()){
					System.out.println(ma.getKey()+"::"+ma.getValue());
				}
			}
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}
}
