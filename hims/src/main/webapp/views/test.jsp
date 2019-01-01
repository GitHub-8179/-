<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("APP_PATH", request.getContextPath());

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${APP_PATH }/views/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

</head>
<body>
	<input type="button" id ="qidong" class='btn btn-info' onclick="qidong()" style="color: #FFFFFF"; value="启动流程">

	<input type="button" id ="chaxun" class='btn btn-info' onclick="chaxun()" style="color: #FFFFFF"; value="查询流程">
	<input type="button" id ="chaxun" class='btn btn-info' onclick="chaxun1()" style="color: #FFFFFF"; value="查询流程1">

	<input type="button" id ="tuisong" class='btn btn-info' onclick="tuisong()" style="color: #FFFFFF"; value="推送流程">

	<div id="zhanshi">
	
	</div>
</body>

<script type="text/javascript" src= "<%=basePath %>views/js/jquery.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>views/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
 function qidong(){
	 $.ajax({
		  	url:"flow/startProcessInstanceByKey.do",
		  	type:"get",
		  	data:{
		  		"fileName":"6",
		  		},
		  	dataType: "json", 
		  	error: function(result){ //失败 
		  		$(".bodys").removeClass("showC");
		  	}, 
		  	success:function(result){
		  		debugger
		  		$(".bodys").removeClass("showC");
		  		$("#zhanshi").html("<div >"+result.extend.data+"</div>");
		  	}
		}); 
 }
 
 function chaxun(){
	 $.ajax({
		  	url:"flow/findMyPersonalTask.do",
		  	type:"get",
		  	data:{
		  		"fileName":"6",
		  		},
		  	dataType: "json", 
		  	error: function(result){ //失败 
		  		$(".bodys").removeClass("showC");
		  	}, 
		  	success:function(result){
		  		debugger
		  		$(".bodys").removeClass("showC");
		  		$("#zhanshi").html("<div >"+result.extend.data+"</div>");
		  	}
		}); 
 }
 
 
 function chaxun1(){
	 $.ajax({
		  	url:"flow/processDefinitionKey.do",
		  	type:"get",
		  	data:{
		  		"fileName":"6",
		  		},
		  	dataType: "json", 
		  	error: function(result){ //失败 
		  		$(".bodys").removeClass("showC");
		  	}, 
		  	success:function(result){
		  		debugger
		  		$(".bodys").removeClass("showC");
		  		$("#zhanshi").html("<div >"+result.extend.data+"</div>");
		  	}
		}); 
 }
 
 function tuisong(){
	 $.ajax({
		  	url:"flow/complete.do",
		  	type:"get",
		  	data:{
		  		"fileName":"6",
		  		},
		  	dataType: "json", 
		  	error: function(result){ //失败 
		  		$(".bodys").removeClass("showC");
		  	}, 
		  	success:function(result){
		  		debugger
		  		$(".bodys").removeClass("showC");
		  		$("#zhanshi").html("<div >"+result.extend.data+"</div>");
		  	}
		}); 
 }
</script>
</html>