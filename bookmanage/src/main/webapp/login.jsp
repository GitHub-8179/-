<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理登录</title>
    <link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<%--     <link href="<%=basePath %>views/welcome/css/animate.css" rel="stylesheet" type="text/css"/>
 --%>    
 	<link href="<%=basePath %>views/welcome/css/style.css" rel="stylesheet" type="text/css"/>

</head>

<body class="gray-bg">

    <div class="middle-box text-center">
        <div>
            <div>
                <h2 class="logo-name"> 欢迎</h2>
            </div>
            <h3 class="welcomeText">图书管理系统欢迎您的使用</h3>

            <form class="m-t" role="form" action="<%=basePath %>manage/loginVerify.do" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="logname" placeholder="用户名" required="请输入登录账号">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="logpass"  placeholder="密码" required="请输入账号密码">
                </div>
                <div style="color:red">${errorInfo }</div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>


<!--                 <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
               </p> --> 

            </form>
        </div>
    </div>


</body>

	<script type="text/javascript" src= "<%=basePath %>static/js/jquery.min.js"></script>
	<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</html>