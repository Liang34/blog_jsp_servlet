package blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.domin.ArticleAndUser;
import blog.domin.CommentAndUser;
import blog.service.ArticleService;
import blog.service.CommentService;
import blog.service.impl.ArticleServiceImpl;
import blog.service.impl.CommentServiceImpl;

/**
 * 获取文章信息
 */
@WebServlet("/ArticleDetail")
public class ArticleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 获取文章id
		String articleId = req.getParameter("articleId");
		// 根据article调用articleService查询文章
		ArticleService as = new ArticleServiceImpl();
		ArticleAndUser article =as.findByArticleId(articleId);
		req.setAttribute("article", article);
		// 根据articleId调用commentService查询评论
		CommentService cs = new CommentServiceImpl();
		List<CommentAndUser> comments = cs.findAll(articleId);
		for (CommentAndUser comment : comments) {
            System.out.println(comment.toString());
		}
		req.setAttribute("comments", comments);
		req.getRequestDispatcher("/article_detail.jsp").forward(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
