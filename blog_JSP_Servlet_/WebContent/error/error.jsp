<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <link rel="stylesheet" href="/blog_JSP_Servlet/css/error_style.css">
<title>错误页面提示</title>
</head>
<body>
  <div class="main-container">
    <div class="img-container container-item">
      <img src="/blog_JSP_Servlet/images/static/error.svg">
    </div>
    <div class="text-container container-item">
      <div class="code">${ code }</div>
      <div class="msg">${ msg }</div>
      <div class="action">3秒后自动返回前一页...</div>
    </div>
  </div>
  <script type="text/javascript">
		setTimeout(function () {
			location.href = '${ url }';
		}, 3000)
  </script>
</body>
</html>