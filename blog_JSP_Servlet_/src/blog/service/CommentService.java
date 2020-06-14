package blog.service;

import java.util.List;

import blog.domin.Comment;
import blog.domin.CommentAndUser;

public interface CommentService {
	/**
	 * 查询所有评论
	 * @param articleId
	 * @return
	 */
	List<CommentAndUser> findAll(String articleId);
	/**
	 * 新增评论
	 * @param com
	 */
	void addComment(Comment com);

}
