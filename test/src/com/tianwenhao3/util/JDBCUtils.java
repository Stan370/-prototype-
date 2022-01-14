package com.tianwenhao3.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/*
 * �������ݿ�Ĺ�����
 * 
 * 
 */
public class JDBCUtils {
	/*
	 * ��ȡ���ݿ������
	 * 
	 * 
	 */
	
	public static Connection getConnection() throws Exception {
		InputStream is =ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
		Properties pros= new Properties();
		pros.load(is);
		
		String dbuser=pros.getProperty("dbuser");

		String dbpassword=pros.getProperty("dbpassword");
		String dburl=pros.getProperty("dburl");
		String dbdriverClass=pros.getProperty("dbdriverClass");
		
		Class.forName(dbdriverClass);
		
		java.sql.Connection conn=DriverManager.getConnection(dburl,dbuser,dbpassword);
		return conn;
	}
/*
 * �ر���Դ
 */
	public static void closeResource(Connection conn,PreparedStatement ps) {
		
		try {
			if(ps !=null)
			ps.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		try {
			if(conn !=null)
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
	}
public static void closeResource(Connection conn,PreparedStatement ps,ResultSet rs) {
		
		try {
			if(ps !=null)
			ps.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		try {
			if(conn !=null)
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
		try {
			if(rs !=null)
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
	}
	
}
