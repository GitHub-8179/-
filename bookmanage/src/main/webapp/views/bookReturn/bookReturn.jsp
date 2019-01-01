<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>返还图书</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">

<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/css/sweetalert.css" rel="stylesheet" type="text/css"/>

</head>


<body class="gray-bg" onload="initFunction()">

	<div class="container-fluid">
		<div class="row">
			<div class="panel panel-default" id="cxtj">
		       <div class="panel-heading">
		            <form role="form" id="searchForm" class="form-inline">
		                <div class="form-group">
		                    <label for="sname1">用户名称:</label> 
		                    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名称">
		                </div>
		                 <div class="form-group">
		                    <label for="sname1">手机号:</label> 
		                    <input class="form-control layer-date"  id="phone" name="phone" placeholder="请输入手机号">
<!-- 		                    <input readonly class="form-control layer-date" id="hello1" >-->		                </div>
		                <div class="form-group">
		                    <button type="button" id="searchBtn" class="btn btn-primary">查询</button>
		                </div>
		            </form>
		       </div>
		   	</div>		
		</div>
		<div class="row">
			<table id="docDateTable" class="table table-striped table-hover table-bordered  " style="color: #000000; ">
				<div class="btn-group hidden-xs" id="exampleTableEventsToolbar" role="group">
			        <button type="button" class="btn btn-outline btn-default" data-toggle="modal" data-target="#myModal5" >
			            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
			        </button>
<!-- 			        data-toggle="modal" data-target="#myModal5"
 -->			        <button type="button" class="btn btn-outline btn-default"  onclick='updateInfo()' >
			            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
			        </button>
			        <button type="button" class="btn btn-outline btn-default" onclick='delInfo()'>
			            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
			        </button>
		        </div>
		</table>
			
		</div>
	</div>
	
  
 
<div class="modal inmodal fade" id="myModal5" tabindex="-1" role="dialog"  aria-hidden="true">
   <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">用户信息新增</h4>
                <small class="font-bold">
            </div>
            <div class="modal-body">


                  <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm">
                        	<div class="form-group hidden">
                                <label class="col-sm-3 control-label">主键：</label>
                                <div class="col-sm-8">
                                    <input id="cardId" name="cardId" class="form-control" placeholder="请输入主键" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">用户名称：</label>
                                <div class="col-sm-8">
                                    <input id="username" name="username" class="form-control" type="text" aria-required="true" class="valid">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-8">
	                                <label class="radio-inline">
								        <input type="radio" name="gender" id="gender1" value="1" checked> 男
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="gender" id="gender0"  value="0"> 女
								    </label>
							    </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单位：</label>
                                <div class="col-sm-8">
                                    <input id="company" name="company" class="form-control" placeholder="请输入单位" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input id="email" name="email" class="form-control" type="email">
                                </div>
                            </div>
                           <!--  <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="checkbox" id="agree" name="agree"> 我已经认真阅读并同意《H+使用协议》
                                        </label>
                                    </div>
                                </div>
                            </div> -->
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="button" onclick="saveUpdate()">提交</button>
                                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                    
            </div>
          <!--   <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="add1">保存</button>
            </div> -->
        </div>
    </div>
</div>

</body>

<script type="text/javascript" src= "<%=basePath %>static/js/jquery.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-zh-CN.min.js"></script> 
<script type="text/javascript" src= "<%=basePath %>static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/laydate/laydate.js"></script>
<script type="text/javascript" src= "<%=basePath %>static/js/sweetalert.min.js"></script>

<script type="text/javascript" src= "<%=basePath %>views/bookReturn/js/bookReturn.js"></script>
<%--<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-mobile.min.js"></script>--%>
<script type="text/javascript">
$("#signupForm").validate();

</script>
</html>