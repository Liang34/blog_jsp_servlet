package blog.domin;

import java.sql.Date;

/**
 * 
 * @author 梁建辉
 *这个类是为了方便展示用户的评论内容，姓名等
 */
public class CommentAndUser {
	Integer articleId;										/*评论所属文章*/
	Integer userId;											/*评论所属作者*/
	Date time;											/*评论论时间*/
	String content;										/*评论内容*/
	String username;									/*用户名*/
	String email; 										/*用戶郵箱*/
	String image; 										/*用户头像*/
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "CommentAndUser [articleId=" + articleId + ", userId=" + userId + ", time=" + time + ", content="
				+ content + ", username=" + username + ", email=" + email + ", image=" + image + "]";
	}
	
	
}
