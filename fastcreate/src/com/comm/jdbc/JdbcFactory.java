package com.comm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.comm.log.LogUtil;

public class JdbcFactory {

	private String sqlDriver; //������
    private String url; //�������ݿ��URL��ַ
    private String username; //���ݿ���û���
    private String password; //���ݿ������

	private Connection connnection; //���ݿ����Ӷ���
//    private PreparedStatement pstm; //���ݿ�Ԥ���봦�����
	    
    Logger logger = LogUtil.setLoggerHanlder();

    /** 
     * �������ݿ����� 
     * @return 
     */  
    public Connection getConnection() {  
        try {  
        	 try {
				Class.forName(sqlDriver);
			} catch (ClassNotFoundException e) {
				System.out.println("��������ʧ��");
				logger.severe("��������ʧ��");			
			}  
            // ��ȡ����  
            connnection = DriverManager.getConnection(url, username,  
            		password);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return connnection;  
    }

	public JdbcFactory(String sqlDriver, String url, String username,
			String password) {
		super();
		this.sqlDriver = sqlDriver;
		this.url = url;
		this.username = username;
		this.password = password;
	}  
	 public JdbcFactory() {
		super();
	}
    
	    
}
