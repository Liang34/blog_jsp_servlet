<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户编辑页面</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/blog_JSP_Servlet/css/article_base.css">
</head>
<body>
    <!-- 头部 -->
    <div class="header">
        <!-- 网站标志 -->
        <div class="logo fl">
            <div class="logo fl">
              博客管理系统 <i>HelloWorld</i>
            </div>
        </div>
        <!-- /网站标志 -->
        <!-- 用户信息 -->
        <div class="info">
        	<img src="/blog_JSP_Servlet/images/user/5.jpg" style=" width:70px; height:70px;border-radius:30%"></img>
            <div class="profile dropdown fr">
                <span class="btn dropdown-toggle" data-toggle="dropdown">
                    <p>编辑用户页面</p>
                </span>
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
                    <a class="item active" href="user.html">
                        <span class="glyphicon glyphicon-user"></span>
                        用户管理
                    </a>
                </li>
                <li>
                    <a class="item" href="article.html">
                        <span class="glyphicon glyphicon-th-list"></span>
                        文章管理
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
                <h4>${user.userId}</h4>
                <p class="tips">错误信息</p>
            </div>
            <!-- /分类标题 -->
            	<!-- 因为表单存在图片，需要设置enctype = 'multipart/form-data'的传输方式 -->
            <form class="form-container" action='/blog_JSP_Servlet/UserChangeServlet' method='post' enctype = 'multipart/form-data'>
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control" placeholder="请输入用户名" value=${user.username} name = "username">
                </div>
                <div class="form-group">
                    <label>邮箱</label>
                    <input type="email" class="form-control" placeholder="请输入邮箱地址" value=${user.email} name = "email">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" placeholder="请输入密码" name="password">
                </div>
                <div class="form-group">
                    <label>角色</label>
                    <select class="form-control" name="role">
                        <option ${user.role == 'user' ? 'selected' : '' } value="user">普通用户</option>
                        <option ${user.role == 'admin' ? 'selected' : '' } value="admin">超级管理员</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>状态</label>
                    <select class="form-control" name="userType">
                        <option  ${user.userType == '0' ? 'selected' : '' } value="0">可用</option>
                        <option ${user.userType == '1' ? 'selected' : '' } value="1">禁用</option>
                    </select>
                </div>
                <div class="form-group">
                   <label for="exampleInputFile">用户头像</label>
                   <input type="file" name='image' id='file'>
                   <div class="thumbnail-waper">
                       <img class="img-thumbnail" src="${ user.image }" id='preview' style="width:150px;height:150px;border-radius:30%">
                   </div>
                </div>
                <!-- 用户ID,隐藏提交，方便提交的servlet判断是修改用户还是新增用户 -->
                <input type="hidden" value=${ user.userId } name="userId"/>
                <div class="buttons">
                    <input type="submit" class="btn btn-primary">
                </div>
            </form>
        </div>
    </div>
    <!-- /主体内容 -->
    <!-- /删除确认弹出框 -->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	var file = document.querySelector('#file')
    	var preview = document.querySelector('#preview')
    	// 当用户选择完文件以后
    	file.onchange = function(){
        // 1、创建文件读取对象
        var reader = new FileReader()
        // 用户选择的文件列表：this.files[0]
        // 2读取文件
        reader.readAsDataURL(this.files[0])
        // 监听onload事件
        reader.onload = function(){
            // 将文件读取结果显示在页面中
            preview.src = reader.result
        	}
		}
    </script>
</body>
</html>