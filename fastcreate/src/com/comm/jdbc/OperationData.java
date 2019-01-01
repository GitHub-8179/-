package com.comm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationData {

	JdbcFactory jdbcFactory = null;
	Connection connnection = null;
    private PreparedStatement pstm; //���ݿ�Ԥ���봦�����
    /** 
     * ������������� 
     */  
    private ResultSet resultSet = null;  
    /** 
     * ������������ 
     */  
    private Statement statement = null;  
	  /** 
     * insert update delete ͳһ�ķ��� 
     *  
     * @param sql 
     *            insert,update,delete SQL ��� 
     * @return ��Ӱ������� 
     */  
     public OperationData(String sqlDriver, String url, String username,
			String password) {
    	 synchronized  (JdbcFactory.class){
    		 if(jdbcFactory==null){
    			jdbcFactory = new JdbcFactory(sqlDriver,url,username,password);
    			connnection = jdbcFactory.getConnection();
    		 }
    	}
    }
    
    public int executeUpdate(String sql) {  
        int affectedLine = 0;  
        try {  
            // �������  
//            connnection = this.getConnection();  
  
            // ��������  
            statement = connnection.createStatement();  
  
            // ִ��SQL���  
            affectedLine = statement.executeUpdate(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            // �ͷ���Դ  
            closeAll();  
        }  
        return affectedLine;  
    }
	
	/** 
     * ��ѯ���ݿⷽ�� 
     *  
     * @param sql sql��� 
     *             
     * @return ����� 
     */  
    private ResultSet executeQueryRS(String sql) {  
        try {  
            // �������  
//            connnection = this.getConnection();  
  
            // ����Statement����  
            statement = connnection.createStatement();  
  
            // ִ��SQL ���  
            resultSet = statement.executeQuery(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return resultSet;  
    }  
	/** 
     * ��ȡ������������������List�� 
     * @param sql SQL��� 
     * @return List����� 
     */  
    public List<Object> excuteQuery(String sql) {   
        ResultSet rs = executeQueryRS(sql);  
        ResultSetMetaData rsmd = null;  
        int columnCount = 0;  
        try {  
            rsmd = rs.getMetaData();  
        } catch (SQLException e2) {  
            System.out.println(e2.getMessage());  
        }  
        try {  
            columnCount = rsmd.getColumnCount();  
        } catch (SQLException e1) {  
            System.out.println(e1.getMessage());  
        }  
          
        List<Object> list = new ArrayList<Object>();  
          
        try {  
            while(rs.next()) {  
                Map<String, Object> map = new HashMap<String, Object>();  
                for(int i = 1; i <= columnCount; i++) {  
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));                 
                }  
                list.add(map);  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            closeAll();  
        }  
          
        return list;  
    }  
      
  
    /** 
     * �ͷ�������Դ 
     */  
    private void closeAll() {  
        // �ͷŽ��������  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
  
        // �ͷ���������  
        if (statement != null) {  
            try {  
                statement.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
  
        // �ͷ����ݿ�����  
        if (connnection != null) {  
            try {  
                connnection.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
    }  
    
}
