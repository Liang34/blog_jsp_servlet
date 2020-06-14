package blog.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import blog.dao.Comment_dao;
import blog.domin.Comment;
import blog.domin.CommentAndUser;
import blog.util.JDBCUtils;

public class Comment_DaoImp implements Comment_dao {
	// 创建template对象
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	@Override
	public List<CommentAndUser> findAll(String articleId) {
		String sql = "select blog_comment.*,username,image,email from blog_comment, "
				+ "blog_user where blog_comment.userId = blog_user.userId and blog_comment.articleId = ?";
		List<CommentAndUser> list = template.query(sql,new BeanPropertyRowMapper<CommentAndUser>(CommentAndUser.class), articleId);
		return list;
	}
	@Override
	public void addComment(Comment com) {
		String sql = "insert into blog_comment(articleId,userId,content,time) values(?,?,?,?)";
		template.update(sql, com.getArticleId(),com.getUserId(),com.getContent(),com.getTime());
	}
	@Override
	public void delArticleComment(String articleId) {
		String sql = "delete from blog_comment where articleId = ?";
		template.update(sql, articleId);
		
	}

}
