package blog.domin;

import java.util.Date;

public class Comment {
	int articleId;										/*评论所属文章*/
	int userId;											/*评论所属作者*/
	Date time;											/*评论论时间*/
	String content;										/*评论内容*/
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Comment [articleId=" + articleId + ", userId=" + userId + ", time=" + time + ", content=" + content
				+ "]";
	}
	public Comment(int articleId, int userId, Date time, String content) {
		super();
		this.articleId = articleId;
		this.userId = userId;
		this.time = time;
		this.content = content;
	}
	
}
