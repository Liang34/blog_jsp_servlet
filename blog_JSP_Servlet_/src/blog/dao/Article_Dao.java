package blog.dao;

import java.sql.SQLException;
import java.util.List;

import blog.domin.Article;
import blog.domin.ArticleAndUser;

public interface Article_Dao {

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @return
     */
    List<ArticleAndUser> findByPage(int start, int rows);
    /**
     * 查询总记录数
     * @return
     * @param condition
     */
	public int findTotalCount();
	/**
	 * 查看文章详细信息
	 * @return ArticleAndUser
	 * @throws SQLException
	 */
	public ArticleAndUser findByArticleId(String articleId);
	/**
	 * 新增文章
	 * @param article
	 * @throws SQLException
	 */
	public void addArticle(Article article);
	/**
	 * 根據用戶名查找文章
	 * @param userId
	 */
	List<ArticleAndUser> findByUserId(String userId);
	/**
	 * 删除文章
	 * @param articleId
	 */
	void delArticle(String articleId);
	/**
	 * 更新文章
	 * @param article
	 */
	void updateArticle(Article article);
}
