package jspservlet.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.UserDAO;
import jspservlet.dao.impl.UserDAOImpl;
import jspservlet.vo.User;

/**
 * Servlet implementation class RegistServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		    throws IOException, ServletException{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��װ�����ݣ�����UserService�㣬���������ʾ�쳣��Ϣ�����浽request����
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String fid=req.getParameter("fid");
		User user=new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setFamilyId(req.getParameter("fid"));
		//У���û����Ƿ���Ϲ淶����map��ת������Ϣ
		Map<String,String> errors=new HashMap<String,String>();
		//У���û����Ƿ���Ϲ淶
		if(username==null||username.trim().isEmpty()) {
			errors.put("username", "�û�������Ϊ��");
		}else if(username.length()<3||username.length()>15) {
			errors.put("username","�û������ȱ�����3-15֮�䣡");
		}
		//У�������Ƿ���Ϲ淶
		if(password==null||password.trim().isEmpty()) {
			errors.put("password", "���벻��Ϊ��");
		}else if(password.length()<3||password.length()>15) {
			errors.put("password","���볤�ȱ�����3-15֮�䣡");
		}
		System.out.println(errors);
		
		UserDAO dao = new UserDAOImpl();   
	     int flag = 0;
	     try {
				flag = dao.addUser(user);
				System.out.println(flag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("error");
				e.printStackTrace();
		 }
	     if(flag == 1){   
			 res.getWriter().write("ע��ɹ�");
	         req.getRequestDispatcher("regitSuccess.jsp").forward(req, res);
	         //res.sendRedirect("./welcome.jsp");
	     } 
		 else {
	         res.getWriter().write("ע��ʧ��");
	         res.sendRedirect("regitFail.jsp");
	     }
	}
}
