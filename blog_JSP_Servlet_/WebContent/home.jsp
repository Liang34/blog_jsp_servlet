<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="/blog_JSP_Servlet/css/base.css">
	<link rel="stylesheet" href="/blog_JSP_Servlet/css/index.css">
</head>
<body>
	<!-- 头部框架开始 -->
	<div class="header">
		<div class="w1100">
			<!-- 网站logo开始 -->
			<h1 class="logo fl">
				<a href="javascript:;"><img src="/blog_JSP_Servlet/images/static/logo.jpg" alt="Github"></a>
			</h1>
			<!-- 网站logo结束 -->
			<!-- 网站导航开始 -->
			<ul class="navigation fr">
				<li>
					<a class="active" href="javascript:;">首页</a>
				</li>
				<c:if test="${ user==null }">
					<li>
						<a href="/blog_JSP_Servlet/Login.jsp">登录</a>
					</li>
				</c:if>
				<c:if test="${ user!=null }">
					<li>
						<a href="/blog_JSP_Servlet/EditArticle">写文章</a>
					</li>
				</c:if>
				<c:if test="${ user!=null }">
					<li class="profile dropdown fr">
						<span class="btn dropdown-toggle" data-toggle="dropdown">
							<img src="${ user.image }" style=" width:70px; height:50px; border-radius:40%;marign-left:40px"></img>
							<span class="caret"></span>
                		</span>
						<ul class="dropdown-menu">
                    		<li><a href="/blog_JSP_Servlet/UserEditServlet?userId=${ user.userId }" style="color: black">个人资料</a></li>
                    		<li><a href="/blog_JSP_Servlet/LogoutServlet" style="color: black">退出登录</a></li>
                		</ul>
					</li>
				</c:if>
			</ul>
			<!-- 网站导航结束 -->
		</div>
	</div>
	<!-- 头部框架结束 -->
	<!-- 文章列表开始 -->
	<ul class="list w1100">
		<c:forEach var = "article" items = "${ articles.list }" varStatus="i">
		<li class=${i.index%2==0?'fl':'fr'}>
			<a href="/blog_JSP_Servlet/ArticleDetail?articleId=${ article.articleId }" class="thumbnail">
				<img src="${ article.cover }">
			</a>
			<div class="content">
				<a class="article-title" href="/blog_JSP_Servlet/ArticleDetail?articleId=${ article.articleId }">${ article.title }</a>
				<div class="article-info">
					<span class="author">${ article.username }</span>
					<span ><img alt="" src="${ article.image }" style="border-radius:50%;width:20px;height:20px;"></span>
					<span>${ article.pubDate }</span>
				</div>
				<div class="brief">
					<!--内容简介部分，去除HTML及截取部分.replace(/<[^>]+>/g,'')-->
					${fn:substring(article.content,0,50)}......
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
	<!-- 文章列表结束 -->

	<!-- 分页开始 -->
	<div class="page w1100">
	<c:if test="${ articles.currentPage > 1 }">
		<a href="/blog_JSP_Servlet/HomeServlet?currentPage=${articles.currentPage - 1}">上一页</a>
	</c:if>
		<c:forEach begin="1" end="${ articles.totalPage }" var="i">
		<a href="/blog_JSP_Servlet/HomeServlet?currentPage=${i}" class="${ articles.currentPage==i ? 'active':'' }">${ i }</a>
		</c:forEach>
	<c:if test="${ articles.currentPage < articles.totalPage }">
		<a href="/blog_JSP_Servlet/HomeServlet?currentPage=${articles.currentPage + 1}">下一页</a>
	</c:if>
	</div>
	<!-- 分页结束 -->
	   <script src="https://cdn.bootcdn.net/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<script src="/blog_JSP_Servlet/js/autoload.js"></script>
</body>
</html>