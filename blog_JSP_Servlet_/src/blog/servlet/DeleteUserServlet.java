package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.User_Service;
import blog.service.impl.User_ServiceImpl;

/**
 * 删除用户
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 获取userId
		String userId = req.getParameter("userId");
		// 調用service去刪除用戶
		User_Service us = new User_ServiceImpl();
		Boolean flag = us.delUser(userId);
		if(flag) {
			// 回到用戶管理頁面
			res.sendRedirect("/blog_JSP_Servlet/UserManage");
		}else {
			// 跳到錯誤頁面
			req.setAttribute("code", "410");
			req.setAttribute("msg", "不可以刪除管理員,或者写过文章的小伙伴");
			req.setAttribute("url", "/blog_JSP_Servlet/UserManage");
			req.getRequestDispatcher("/error/error.jsp").forward(req, res);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
