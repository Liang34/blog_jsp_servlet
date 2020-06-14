package blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.domin.PageBean;
import blog.domin.User;
import blog.service.User_Service;
import blog.service.impl.User_ServiceImpl;

@WebServlet("/UserManage")
public class UserManage extends HttpServlet {
	/**
	 * 06-08:改进，增加数据库分页展示，减轻服务器压力
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 在這裡做權限驗證，非登錄用戶和非管理員不可看此頁
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null && user.getRole().equals("admin")) {
			// 接收参数 currentPage(当前所在页),rows(每页显示条数)
			String currentPage = req.getParameter("currentPage");
			String rows = req.getParameter("rows");
			if(currentPage == null || "".equals(currentPage)){
	            currentPage = "1";
	        }
	        if(rows == null || "".equals(rows)){
	            rows = "2";
	        }
	      //2.调用UserService查询数据库中的用户(实现分页查询)
	        User_Service us = new User_ServiceImpl();
	        PageBean<User> users = us.findUserByPage(currentPage, rows);
	        
			// 转发到用户管理 
			req.setAttribute("users", users);
			req.getRequestDispatcher("/admin/user_manage.jsp").forward(req,res);
		}else {// 用户未登录，跳到错误页面
			// 设置错误信息
        	req.setAttribute("code", 407);
        	req.setAttribute("msg", "用户未登录或該用戶沒有權限看此页内容，请登录管理员账号");
        	req.setAttribute("url", "/blog_JSP_Servlet/Login.jsp");
        	req.getRequestDispatcher("error/error.jsp").forward(req,res);
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
