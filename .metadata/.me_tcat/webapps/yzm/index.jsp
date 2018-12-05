<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  
  <body>
    手机号：<input type="text" id="phone"/><input id="btn" type="button" value="获取验证码"/>
    <br>
    验证码：<input type="text" id="yzm"/><input id="dl" type="button" value="登录"/>
    <br>
    <img alt="yzm" src="http://localhost:8080/yzm/YzmImage">
  </body>
  
  <script type="text/javascript" src="js/jquery-1.11.1.js"></script></head>
  
  <script type="text/javascript">
  
  var resultYzm ="";
  $("#btn").click(function(){
	  var phone = $("#phone").val();
	  $.ajax({
	  
	  	url:"sendSMS",
	  	type:"post",
	  	data:{
	  		"phone":phone
	  		},
	  	dataType: "json", 
	  	success:function(result){
	  		resultYzm=result;
	  		console.log(result);
	  		}
	  });
  	});
 
 
  $("#dl").click(function(){
 	  var yzm =  $("#yzm").val();
 	if(yzm==resultYzm){
 		alert("登录成功");
 	}else{
 		alert("验证码错误");
 	}
  });
  
  </script>
</html>
