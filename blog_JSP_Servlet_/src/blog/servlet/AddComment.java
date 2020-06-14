package blog.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.domin.Comment;
import blog.service.CommentService;
import blog.service.impl.CommentServiceImpl;

/**
 * 添加评论
 */
@WebServlet("/AddComment")
public class AddComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String userId = req.getParameter("userId");
		String articleId = req.getParameter("articleId");
		String content = req.getParameter("content");
		Date time = new Date();
		// 对表单进行封装
		Comment com = new Comment(Integer.parseInt(articleId), Integer.parseInt(userId), time, content);
		System.out.println(com);
		// 传给CommentService
		CommentService cs = new CommentServiceImpl();
		cs.addComment(com);
		// 转发到文章详情页
		req.getRequestDispatcher("/ArticleDetail?articleId="+articleId).forward(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
