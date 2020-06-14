package blog.domin;

import java.sql.Date;

/**
 * 
 * 说明：之所以创建这个类是应为在数据库中查询所有文章时，能够同时返回文章对应的用户信息，
 * 但又不想文章表插入过多的用户字段就搞了这么一个类，方便封装
 *
 */
public class ArticleAndUser {
	Integer articleId;							/*文章ID*/									
    Integer userId;								/*作者ID*/
    String title;							/*文章标题*/
    String content;							/*文章内容*/
    Date pubDate;							/*发布日期*/
    String cover;							/*文章封面*/	
    // 用户部分字段
    String username;						/*用户名*/
    String image; 							/*用户头像*/
	String email; 							/*用戶郵箱*/
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ArticleAndUser [articleId=" + articleId + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", pubDate=" + pubDate + ", cover=" + cover + ", username=" + username + ", image=" + image
				+ ", email=" + email + "]";
	}
	
}
