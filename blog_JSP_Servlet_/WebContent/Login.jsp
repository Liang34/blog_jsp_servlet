<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" href="/blog_JSP_Servlet/css/style.css">
<title>Login</title>
</head>
<body>
  <div class="login-container">
    <div class="left-container">
      <div class="title"><span>登录</span></div>
      <div class="input-container">
        <form action="/blog_JSP_Servlet/LoginServlet" method = "post" id = "form1">
        	<input type="text" name="username" placeholder="用户名">
        	<input type="password" name="password" placeholder="密码">
        </form>
      </div>
      <div class="message-container">
        <span>忘记密码</span>
      </div>
    </div>
    <div class="right-container">
      <div class="regist-container">
        <span><a href="/blog_JSP_Servlet/register.jsp" class="regist">注册</a></span>
      </div>
      <div class="actoin-container">
        <span id="submit">提交</span>
      </div>
    </div>
  </div>
  <script>
    var form = document.getElementById('form1')
  	document.getElementById('submit').onclick = function(){
  		form.submit()
  	}
  </script>
  <script src="/blog_JSP_Servlet/js/autoload.js"></script>
</body>
</html>