package blog.dao;

import java.util.List;

import blog.domin.Comment;
import blog.domin.CommentAndUser;

public interface Comment_dao {
	/**
	 * 根据文章id查询所有评论
	 * @param articleId
	 * @return
	 */
	public List<CommentAndUser> findAll(String articleId);
	/**
	 * 插入评论
	 * @param com
	 */
	public void addComment(Comment com);
	/**
	 * 删除文章的评论
	 * @param articleId
	 */
	public void delArticleComment(String articleId);
}
