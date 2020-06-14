package blog.service.impl;

import java.util.List;

import blog.dao.Comment_dao;
import blog.dao.impl.Comment_DaoImp;
import blog.domin.Comment;
import blog.domin.CommentAndUser;
import blog.service.CommentService;

public class CommentServiceImpl implements CommentService{
	Comment_dao ad = new Comment_DaoImp();
	@Override
	public List<CommentAndUser> findAll(String articleId) {
		List<CommentAndUser> list = ad.findAll(articleId);
		return list;
	}

	@Override
	public void addComment(Comment com) {
		ad.addComment(com);
	}

	
}
