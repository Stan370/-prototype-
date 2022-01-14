package jspservlet.dao.impl;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import jspservlet.dao.UserDAO;
import jspservlet.db.DbConnector;
import jspservlet.vo.User;


public class UserDAOImpl implements UserDAO {
	public int queryByUsername(User user) throws Exception {
		// TODO Auto-generated method stub
		int flag = 0;
		String sql = "select * from user where uid=?";
        PreparedStatement pstmt = null ;   
        DbConnector dbc = null;   
        try{   
            // �������ݿ�   
            dbc = new DbConnector() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ;
            //setString����һ����������Ϊuser.getUsername()
            pstmt.setString(1,user.getUsername()) ;   
            // �������ݿ��ѯ����   
            ResultSet rs = pstmt.executeQuery();
            System.out.println(user.getUsername());
            while(rs.next()){  
                // ��ѯ�����ݣ�֮�󽫲�ѯ�������ݸ�ֵ��person����   
                if(rs.getString("password").equals(user.getPassword())){
                	flag = 1;
                } 
            }
            rs.close() ; 
            pstmt.close() ;   
        }catch (SQLException e){   
            System.out.println(e.getMessage());   
        }finally{   
            // �ر����ݿ�����   
            dbc.close() ;   
        }   
        return flag;
	}
	public int addUser(User user)throws Exception{
		int flag = 0;
		int flag2 = 0;
		try {
			DbConnector dbc = new DbConnector();
			String sql = "select * from user where uid=?";
			String sql2 = "insert into user(uid,password,family_fid) values(?,?,?)";
			PreparedStatement pstmt = dbc.getConnection().prepareStatement(sql);
			PreparedStatement ps = dbc.getConnection().prepareStatement(sql2);
			pstmt.setString(1, user.getUsername());
			ResultSet rs = pstmt.executeQuery();
			System.out.println(user.getUsername());
			
			while(rs.next()) {
				System.out.println(rs.getString("uid"));
				if(rs.getString("uid").equals(user.getUsername())) {
					System.out.println("User "+user.getUsername()+" already exists");
					flag2++;
					return flag;
				}
			}
			if(flag2==0){
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFamilyId());
				System.out.println("Sign up success!");
				System.out.println(ps.executeUpdate());
				return ++flag;
			}
		}catch(SQLException e) {
			e.getStackTrace();
			System.out.println(e);
		}catch(NullPointerException e) {
			HttpServletResponse response = null;
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('����û�е�¼�����¼...'); window.location='error.jsp' </script>");
			out.flush();
			out.close();
		}
		return flag;
	}
	
}
