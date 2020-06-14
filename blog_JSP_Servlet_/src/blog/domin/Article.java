package blog.domin;


public class Article {
	Integer articleId;							/*文章ID*/									
    Integer userId;								/*作者ID*/
    String title;							/*文章标题*/
    String content;							/*文章内容*/
    String pubDate;							/*发布日期:为了方便数据库的插入，这里统一改为String类型*/
    String cover;							/*文章封面*/
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
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", pubDate=" + pubDate + ", cover=" + cover + "]";
	}
	
}
