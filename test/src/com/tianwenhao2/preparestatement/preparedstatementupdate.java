package com.tianwenhao2.preparestatement;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.tianwenhao.connection.ConnectionTest;
import com.tianwenhao3.util.JDBCUtils;

public class preparedstatementupdate {
	@Test
	public void testCommonTpdate() {
		//String sql="delete from administrator where uid =?";
		//update(sql,001);
		String sql="update user set password =? where uid =?";
		update(sql,"888","tom");
	}
	
	
    public void update(String sql,Object ...args) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			//3,���ռλ��
			for(int i=0;i<args.length;i++) {
				ps.setObject(i+1,args[i]);
			}
			ps.execute();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeResource(conn, ps);
		}
	}
	
	
	public void testUpdate() {
		//��ȡ����
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn = JDBCUtils.getConnection();
			//Ԥ����sql��䣬����preparedstatement
				String sql="update administrator set password =? where uid = ? ";
			    ps = conn.prepareStatement(sql);
			//���ռλ��
			    ps.setObject(1,123456);
			    ps.setObject(2, 001);
			//ִ��
			    ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {//��Դ�ر�
		    JDBCUtils.closeResource(conn,ps);
		}
	}
	public void testInsert() {
		
			java.sql.Connection conn=null;
			PreparedStatement ps=null;
			try {
				InputStream is =ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
				Properties pros= new Properties();
				pros.load(is);
				
				String dbuser=pros.getProperty("dbuser");

				String dbpassword=pros.getProperty("dbpassword");
				String dburl=pros.getProperty("dburl");
				String dbdriverClass=pros.getProperty("dbdriverClass");
				
				Class.forName(dbdriverClass);
				
				conn = DriverManager.getConnection(dburl,dbuser,dbpassword);
				
				String sql="insert into administrator(uid,password)values(?,?)";
				ps = conn.prepareStatement(sql);
				//���ռλ��
				ps.setString(1, "001");
				ps.setString(2, "456789");
				//ִ�в���
				ps.execute();
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally {
				//��Դ�Ĺر�
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
			
		}
	}


