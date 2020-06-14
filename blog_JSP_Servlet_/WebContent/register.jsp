<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册页面</title>
<link rel="stylesheet" href="/blog_JSP_Servlet/css/register_style.css">
</head>
<body>
  <div class="container">
  <img alt="" src="/blog_JSP_Servlet/images/user/1.jpg" style="width:80px;height:80px;border-radius:50%;margin-left:47%;margin-top:10%" id="preview">
    <div class="login-wrapper">
      <div class="header">Register</div>
      <form class="form-wrapper" method="post" action="/blog_JSP_Servlet/RegisterServlet" id="form" enctype = 'multipart/form-data'>
        <input type="text" name="username" placeholder="username" class="input-item">
        <input type="password" name="password" placeholder="password" class="input-item">
        <input type="email" name="email" placeholder="email" class="input-item">
        <input type="file" name='image' id='file' class="input-item">
        <div class="btn">Register</div>
      </form>
      <div class="msg">
        Already have account? <a href="/blog_JSP_Servlet/Login.jsp">Login</a>
      </div>
    </div>
  </div>
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
    	var form = document.querySelector('#form')
    	var btn = document.querySelector('.btn')
    	btn.onclick = function(){
    		form.submit()
    	}
    </script>
  <script src="/blog_JSP_Servlet/js/autoload.js"></script>
</body>
</html>