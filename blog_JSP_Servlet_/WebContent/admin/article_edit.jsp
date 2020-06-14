<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>编辑文章</title>
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
            <div class="cprt">
                Powered by <a href="https://liang34.github.io/Liang34/" target="_blank">我的博客</a>
            </div>
        </div>
        <!-- 侧边栏 -->
        <div class="main">
            <!-- 分类标题 -->
            <div class="title">
                <h4>记录生活，记录美好</h4>
            </div>
            <!-- /分类标题 -->
            <form class="form-container" action='/blog_JSP_Servlet/AddArticle' method='post' enctype = 'multipart/form-data'>
                <div class="form-group">
                    <label>标题</label>
                    <input type="text" class="form-control" placeholder="请输入文章标题" name = 'title'>
                </div>
                <div class="form-group">
                    <label>作者</label>
                    <input type="text" class="form-control" readonly name='author' value=${ user.username }>
                </div>
                <div class="form-group">
                    <label>发布时间</label>
                    <input type="date" class="form-control" name='pubDate'>
                </div>
                
                <div class="form-group">
                   <label for="exampleInputFile">文章封面</label>
                   <!--
                        multiple 允许用户一次性选择多个文件
                   -->
                   <input type="file" name='cover' id='file'>
                   <div class="thumbnail-waper">
                       <img class="img-thumbnail" src="" id='preview'>
                   </div>
                </div>
                <div class="form-group">
                    <label>内容</label>
                    <textarea class="form-control" id="editor" name='content'></textarea>
                </div>
                <input type="hidden" name="userId" value=${ user.userId }>
                <div class="buttons">
                    <input type="submit" class="btn btn-primary" >
                </div>
            </form>
            
        </div>
    </div>
    <!-- /主体内容 -->
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="/blog_JSP_Servlet/js/ckeditor5/ckeditor.js"></script>
    <script src="/blog_JSP_Servlet/js/autoload.js"></script>
    <script type="text/javascript">
    
        let editor;

        ClassicEditor
                .create( document.querySelector('#editor'))
                .then(newEditor => {
                    editor = newEditor;
                })
                .catch( error => {
                    console.error( error );
                });
        // 选择上传文件
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
        // 获取数据
        // const editorData = editor.getData();
    </script>
</body>
</html>