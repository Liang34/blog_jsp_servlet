package blog.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.domin.User;
import blog.service.User_Service;
import blog.service.impl.User_ServiceImpl;

@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取取userId
		String userId = req.getParameter("userId");
		if(userId!= null) { // 编辑用户，需要带数据去转发
			// 根据id查询用户
			User_Service us = new User_ServiceImpl();
			User user = us.findByUserId(userId);
			req.setAttribute("user", user);
			// 转发到user_edit
			req.getRequestDispatcher("admin/user_edit.jsp").forward(req,res);
		}else {
			// 新增用户，直接转发
			req.getRequestDispatcher("admin/user_edit.jsp").forward(req,res);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
