package blog.service.impl;

import java.util.List;

import blog.dao.Article_Dao;
import blog.dao.Comment_dao;
import blog.dao.impl.Article_DaoImpl;
import blog.dao.impl.Comment_DaoImp;
import blog.domin.Article;
import blog.domin.ArticleAndUser;
import blog.domin.PageBean;
import blog.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

	@Override
	public PageBean<ArticleAndUser> findArticleByPage(String _currentPage, String _rows) {
		Article_Dao dao = new Article_DaoImpl();
	    int currentPage = Integer.parseInt(_currentPage);
	    int rows = Integer.parseInt(_rows);
	    if(currentPage <=0) {
	       currentPage = 1;
	    }
	    //1.创建空的PageBean对象
	    PageBean<ArticleAndUser> pb = new PageBean<ArticleAndUser>();
	    //2.设置参数
	    pb.setCurrentPage(currentPage);
	    pb.setRows(rows);
	    //3.调用dao查询总记录数
	    int totalCount = dao.findTotalCount();
	    pb.setTotalCount(totalCount);
	    //4.调用dao查询List集合
	    //计算开始的记录索引
	     int start = (currentPage - 1) * rows;
	    List<ArticleAndUser> list = dao.findByPage(start,rows);
	    pb.setList(list);
	    //5.计算总页码
	    int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
	    pb.setTotalPage(totalPage);
	    return pb;
		}

	@Override
	public ArticleAndUser findByArticleId(String articleId) {
		// 调用Dao层
		Article_Dao dao = new Article_DaoImpl();
		ArticleAndUser aau = dao.findByArticleId(articleId);
		return aau;
	}

	@Override
	public void addArticle(Article article) {
		// 调用dao层保存文章
		Article_Dao dao = new Article_DaoImpl();
		dao.addArticle(article);
	}

	@Override
	public void delArticle(String articleId) {
		// 先调用comment_dao去删除文章的评论
		Comment_dao cd = new Comment_DaoImp();
		cd.delArticleComment(articleId);
		// 调用articledao删除文章
		Article_Dao dao = new Article_DaoImpl();
		dao.delArticle(articleId);
	}

	@Override
	public void updateArticle(Article article) {
		Article_Dao dao = new Article_DaoImpl();
		dao.updateArticle(article);
	}
}
