<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>文章詳情</title>
<link rel="stylesheet" href="/blog_JSP_Servlet/css/base.css">
<link rel="stylesheet" href="/blog_JSP_Servlet/css/article.css">

</head>
<body>
	<!-- 头部框架开始 -->
	<div class="header">
		<div class="w1100">
			<!-- 网站logo开始 -->
			<h1 class="logo fl">
				<a href="javascript:;"><img src="./images
				/static/logo.jpg" alt="Github"></a>
			</h1>
			<!-- 网站logo结束 -->
			<!-- 网站导航开始 -->
			<ul class="navigation fr">
				<li>
					<a href="/blog_JSP_Servlet/HomeServlet">首页</a>
				</li>
				<c:if test="${ user==null }">
					<li>
						<a href="/blog_JSP_Servlet/Login.jsp">登录</a>
					</li>
				</c:if>
			</ul>
			<!-- 网站导航结束 -->
		</div>
	</div>
	<!-- 头部框架结束 -->
	<!-- 文章框架开始 -->
	<div class="article">
		<div class="w1100">
			<div class="container">
				<div class="article-header">
					<h3 class="article-title">${ article.title }</h3>
					<div class="article-info">
						<span class="author">${ article.username }</span>
						<span>${ article.pubDate }</span>
					</div>
				</div>
				<div class="article-content">
					${ article.content }
				</div>
				<div class="article-comment">
					<h4>评论</h4>
					<c:if test="${ user==null }"><h2>先进行<a href="/blog_JSP_Servlet/Login.jsp">登录</a>，再对文章进行评论</h2></c:if>
					<c:if test="${ user!= null }">
						<form class="comment-form" method="post" action="/blog_JSP_Servlet/AddComment">
							<textarea class="comment" name="content"></textarea>
							<input type="hidden" value="${ user.userId }" name="userId"/>
							<input type="hidden" value="${ article.articleId }" name="articleId"/>
							<div class="items">
								<input type="submit" value="提交">
							</div>
						</form>
					</c:if>
					
					<!-- 评论显示 -->
					<div class="comment-list">
					<c:forEach var = "comment" items = "${ comments }">
						<div class="mb10">
							<div class="article-info">
								<span class="author">${ comment.username }</span>
								<span>2020-09-10</span>
								<span>${ comment.email }</span>
							</div>
							<div class="comment-content">
								${ comment.content }
							</div>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 文章框架结束 -->
	<script src="./js/autoload.js"></script>
</body>
</html>