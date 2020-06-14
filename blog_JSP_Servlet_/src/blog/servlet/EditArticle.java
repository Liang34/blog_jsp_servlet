package blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.domin.Article;
import blog.domin.ArticleAndUser;
import blog.domin.User;
import blog.service.ArticleService;
import blog.service.impl.ArticleServiceImpl;

/**
 * 编辑用户信息
 * @author 梁建辉
 *
 */
@WebServlet("/EditArticle")
public class EditArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 判断用户是否登录
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String articleId = req.getParameter("articleId");
		if(user!=null) {
			if(articleId.equals("")) {
				req.getRequestDispatcher("/admin/article_edit.jsp").forward(req,res);
			}else {
				ArticleService as = new ArticleServiceImpl();
				ArticleAndUser article = as.findByArticleId(articleId);
				req.setAttribute("article", article);
				req.getRequestDispatcher("/admin/article_edit2.jsp").forward(req,res);
			}
			
		}else {
        	req.setAttribute("code", 410);
        	req.setAttribute("msg", "请登录");
        	req.setAttribute("url", "/blog_JSP_Servlet/Login.jsp");
        	req.getRequestDispatcher("error/error.jsp").forward(req,res);
        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
