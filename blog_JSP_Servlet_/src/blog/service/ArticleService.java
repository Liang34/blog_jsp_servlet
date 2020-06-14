package blog.service;

import blog.domin.Article;
import blog.domin.ArticleAndUser;
import blog.domin.PageBean;

public interface ArticleService {
	/**
     * User分页查询
     * @param currentPage
     * @param rows
     * @return
     */
	PageBean<ArticleAndUser> findArticleByPage(String currentPage, String rows);
	/**
	 * 查看文章详细信息
	 * @param articleId
	 * @return
	 */
	ArticleAndUser findByArticleId(String articleId);
	/**
	 * 保存文章
	 * @param article
	 */
	void addArticle(Article article);
	/**
	 * 删除文章以及该文章的评论
	 * @param articleId
	 */
	void delArticle(String articleId);
	/**
	 * 根据articleId来更新文章
	 * @param article
	 */
	void updateArticle(Article article);
}
