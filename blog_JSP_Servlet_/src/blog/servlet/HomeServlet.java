package blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.domin.ArticleAndUser;
import blog.domin.PageBean;
import blog.service.ArticleService;
import blog.service.impl.ArticleServiceImpl;

/**
 * 首页请求，通过调用ArticleService来实现分页功能
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = req.getParameter("currentPage");//当前页码
        String rows = req.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "4"; // 默认情况每页四条
        }
      //2.调用ArticleService查询数据库中的文章(实现分页查询)
        ArticleService ac = new ArticleServiceImpl();
        PageBean<ArticleAndUser> articles = ac.findArticleByPage(currentPage,rows);
		// 存储文章数据
		req.setAttribute("articles", articles);
		// 转发到jsp页面
		req.getRequestDispatcher("/home.jsp").forward(req,res);
				
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
