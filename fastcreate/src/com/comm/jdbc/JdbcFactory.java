package com.comm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.comm.log.LogUtil;

public class JdbcFactory {

	private String sqlDriver; //驱动名
    private String url; //连接数据库的URL地址
    private String username; //数据库的用户名
    private String password; //数据库的密码

	private Connection connnection; //数据库连接对象
//    private PreparedStatement pstm; //数据库预编译处理对象
	    
    Logger logger = LogUtil.setLoggerHanlder();

    /** 
     * 建立数据库连接 
     * @return 
     */  
    public Connection getConnection() {  
        try {  
        	 try {
				Class.forName(sqlDriver);
			} catch (ClassNotFoundException e) {
				System.out.println("加载驱动失败");
				logger.severe("加载驱动失败");			
			}  
            // 获取连接  
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
