<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户管理页面</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/blog_JSP_Servlet/css/article_base.css">
</head>
<body>
	<!-- 头部 -->
    <div class="header">
    	<!-- 网站标志 -->
        <div class="logo fl">
          博客管理系统 <i>HelloWorld</i>
        </div>
        <!-- /网站标志 -->
        <!-- 用户信息 -->
        <div class="info">
        	<h2 style="display:inline; margin-left:500px; margin-top:70px">${ user.username },你好</h2>
            <div class="profile dropdown fr">
                <span class="btn dropdown-toggle" data-toggle="dropdown">
					<img src="${ user.image }" style=" width:70px; height:50px; border-radius:40%"></img>
					<span class="caret"></span>
                </span>
                <ul class="dropdown-menu">
                    <li><a href="/blog_JSP_Servlet/UserEditServlet?userId=${ user.userId }">个人资料</a></li>
                    <li><a href="/blog_JSP_Servlet/LogoutServlet">退出登录</a></li>
                </ul>
            </div>
        </div>
        <!-- /用户信息 -->
    </div>
    <!-- /头部 -->
    <!-- 主体内容 -->
    <div class="content">
    	<!-- 侧边栏 -->
        <div class="aside fl">
            <ul class="menu list-unstyled">
                <li>
                    <a class="item active" href="javascript:;">
						<span class="glyphicon glyphicon-user"></span>
						用户管理
					</a>
                </li>
                <li>
                    <a class="item" href="/blog_JSP_Servlet/ArticleManageServlet">
			  			<span class="glyphicon glyphicon-th-list"></span>
			  			文章管理
			  		</a>
                </li>
                <li>
                    <a class="item" href="/blog_JSP_Servlet/HomeServlet">
			  			<span class="glyphicon glyphicon-th-list"></span>
			  			博客首页
			  		</a>
                </li>
            </ul>
            <div class="cprt">
                Powered by <a href="https://liang34.github.io/Liang34/" target="_blank">我的博客</a>
            </div>
        </div>
        <!-- 侧边栏 -->
        <div class="main">
        	<!-- 分类标题 -->
            <div class="title">
                <h4>用户</h4>
                <span>共找到${ users.totalCount }个用户,一共有${ users.totalPage }页,当前位于第${ users.currentPage }页</span>
                <a href="/blog_JSP_Servlet/UserEditServlet" class="btn btn-primary new">新增用户</a>
            </div>
            <!-- /分类标题 -->
            <!-- 内容列表 -->
            <table class="table table-striped table-bordered table-hover custom-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>用户名</th>
                        <th>邮箱</th>
                        <th>角色</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody> 
                    <!-- 遍历users -->
                    <c:forEach var = "user" items = "${ users.list }">
                    <tr>
                    	<td>${user.userId}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td><c:if test="${user.userType==0}">可用</c:if>
                        <c:if test="${user.userType==1}">禁用</c:if></td>
                        <td>
                            <a href="/blog_JSP_Servlet/UserEditServlet?userId=${ user.userId }" class="glyphicon glyphicon-edit"></a>
                            <i class="glyphicon glyphicon-remove" data-toggle="modal" data-target=".confirm-modal" onClick="delcfm('${ user.userId }')"></i>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- /内容列表 -->
            <!-- 分页 -->
            <ul class="pagination">
				<c:if test="${users.currentPage > 1 }">
					<li>
                    	<a href="/blog_JSP_Servlet/UserManage?currentPage=${ users.currentPage - 1}">
			        	<span>&laquo;</span>
			      		</a>
                	</li>
				</c:if>
                <c:forEach begin="1" end="${ users.totalPage }" var="i">
					<li><a href="/blog_JSP_Servlet/UserManage?currentPage=${i}">${ i }</a></li>
				</c:forEach>
				<c:if test="${ users.currentPage < users.totalPage }">
                <li>
                    <a href="/blog_JSP_Servlet/UserManage?currentPage=${ users.currentPage + 1}">
			        <span>&raquo;</span>
			      </a>
                </li>
                </c:if>
            </ul>
            <!-- /分页 -->
        </div>
    </div>
    <!-- /主体内容 -->
    <!-- 删除确认弹出框 -->
    <div class="modal fade confirm-modal" id="delcfmModel">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" action="/blog_JSP_Servlet/DeleteUserServlet" method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                    <h4 class="modal-title">请确认</h4>
                </div>
                <div class="modal-body">
                    <p>您确定要删除这个用户吗?(注意：管理员和发过文章的小伙伴暂时不能删除)</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <input type="submit" class="btn btn-primary" >
                    <input type="hidden" id="url" name="userId"> 
                </div>
            </form>
        </div>
    </div>
    <!-- /删除确认弹出框 -->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="./js/autoload.js"></script>
    <script type="text/javascript">
    function delcfm(url) {  
        $('#url').val(url);//给会话中的隐藏属性URL赋值  
  	}
    </script>
</body>
</html>