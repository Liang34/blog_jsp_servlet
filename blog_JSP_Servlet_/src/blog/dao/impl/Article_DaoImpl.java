package blog.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import blog.dao.Article_Dao;
import blog.domin.Article;
import blog.domin.ArticleAndUser;
import blog.util.JDBCUtils;

public class Article_DaoImpl implements Article_Dao{
	// 创建template对象
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	@Override
	public ArticleAndUser findByArticleId(String articleId) {
		try {
			String sql = "select blog_article.*,username,image,email from blog_article, "
					+ "blog_user where blog_article.userId = blog_user.userId and blog_article.articleId = ?";
			ArticleAndUser aau = template.queryForObject(sql, new BeanPropertyRowMapper<ArticleAndUser>(ArticleAndUser.class),articleId);
			return aau;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addArticle(Article article) {
		String sql = "insert into blog_article(articleId, userId,title , content, pubDate, cover) values (?,?,?,?,?,?)";
		template.update(sql,article.getArticleId(),article.getUserId(),article.getTitle(),article.getContent(),article.getPubDate(),
				article.getCover());
	}

	@Override
	public List<ArticleAndUser> findByPage(int start, int rows) {
		String sql = "select blog_article.*,username,image,email from blog_article, "
				+ "blog_user where blog_article.userId = blog_user.userId limit ?,?";
        List<ArticleAndUser> list = template.query(sql, new BeanPropertyRowMapper<ArticleAndUser>(ArticleAndUser.class),start, rows);
        return list;
	}

	@Override
	public int findTotalCount() {
        String sql = "select count(articleId) from blog_article";
        return template.queryForObject(sql, Integer.class);
	}

	@Override
	public List<ArticleAndUser> findByUserId(String userId) {
		String sql = "select blog_article.*,username,image,email from blog_article, "
				+ "blog_user where blog_article.userId = blog_user.userId and blog_article.userId= ?";
        List<ArticleAndUser> list = template.query(sql, new BeanPropertyRowMapper<ArticleAndUser>(ArticleAndUser.class),userId);
        return list;
	}

	@Override
	public void delArticle(String articleId) {
		String sql = "delete from blog_article where articleId=?";
		template.update(sql,articleId);
		
	}

	@Override
	public void updateArticle(Article article) {
		String sql = "update blog_article set title=?, content=?, pubDate=?, cover=? where articleId = ?";
		template.update(sql, article.getTitle(),article.getContent(),article.getPubDate(), article.getCover(), article.getArticleId());
		
	}

}
