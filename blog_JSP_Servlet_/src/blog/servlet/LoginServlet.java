package blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.domin.User;
import blog.service.User_Service;
import blog.service.impl.User_ServiceImpl;

/**
 *登陆处理：需要判断是否为管理员，用户，是否被禁用
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//1.设置编码
        req.setCharacterEncoding("utf-8");
        // 2、获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 3、根据username和password去Userservice获取用户信息,在Service层进行密码判断
        User_Service us = new User_ServiceImpl();
        User user = us.findByUserName(username, password);
        if(user!=null) {// 密码校验成功
        	if(user.getUserType()==0) {
        		if(user.getRole().equals("admin")) {
            		//存储数据
            		HttpSession session = req.getSession();
            		session.setAttribute("user", user);
            		// admin，转发到用户管理页面
                     req.getRequestDispatcher("UserManage").forward(req,res);
            	}else {
            		// 普通用户，转发到HomeServlet获取文章数据
            		HttpSession session = req.getSession();
            		session.setAttribute("user", user);
            		req.getRequestDispatcher("HomeServlet").forward(req,res);
            	}
        	}else {// 该用户已经被禁用
        		req.setAttribute("code", 408);
            	req.setAttribute("msg", "用户已被禁用，请与管理员联系");
            	req.getRequestDispatcher("error/error.jsp").forward(req,res);
        	}
        } else {// 密码校验失败，跳到错误页面
        	// 设置错误信息
        	req.setAttribute("code", 405);
        	req.setAttribute("msg", "用户名或密码错误");
        	req.setAttribute("url", "/blog_JSP_Servlet/Login.jsp");
        	req.getRequestDispatcher("error/error.jsp").forward(req,res);
        }
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
