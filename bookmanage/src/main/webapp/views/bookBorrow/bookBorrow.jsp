<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String recordType= request.getParameter("recordType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>借阅图书</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">

<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/bootstrap-3.3.7-dist/css/bootstrap-table.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/css/sweetalert.css" rel="stylesheet" type="text/css"/>

</head>


<body class="gray-bg">

	<div class="container-fluid">
		<div class="row">
			<div class="panel panel-default" id="cxtj">
		       <div class="panel-heading">
		            <form role="form" id="searchForm" class="form-inline">
		            	<div class="form-group hidden">
                                <label class="col-sm-3 control-label">类型：</label>
                                <div class="col-sm-8">
                                    <input id="recordType" name="recordType" class="form-control" type="text" value="<%=recordType%>">
                                </div>
                         </div>
		                <div class="form-group">
		                    <label for="sname1">图书编号:</label> 
		                    <input type="text" class="form-control" id="bookName" name="bookName" placeholder="请输入图书编号">
		                </div>
		                 <div class="form-group">
		                    <label for="sname1">用户编码:</label> 
		                    <input class="form-control layer-date"  id="cardId" name="cardId" placeholder="请输入用户编号">
		                <div class="form-group">
		                    <button type="button" id="searchBtn" class="btn btn-primary" onclick="saveUpdate()" >
							</button>
							
		                </div>
		            </form>
		       </div>
		   	</div>		
		</div>
		
		
		<div class="row">
			<table id="docDateTable" class="table table-striped table-hover table-bordered  " style="color: #000000; ">
		</table>
			
		</div>
	</div>
	
	
	
	
<div class="modal inmodal fade" id="myModal5" tabindex="-1" role="dialog"  aria-hidden="true">
   <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">用户信息详情</h4>
                <small class="font-bold">
            </div>
            <div class="modal-body">

                  <div class="ibox-content">
                        <form class="form-horizontal m-t" id="signupForm">
                        	<div class="form-group">
                                <label class="col-sm-3 control-label">主键：</label>
                                <div class="col-sm-8">
                                    <input id="bookId" name="bookId" class="form-control" placeholder="请输入主键" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">图书类型：</label>
                                <div class="col-sm-8">
                                    <input id="bookType" name="bookType" class="form-control" type="text" aria-required="true" class="valid">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-8">
	                                <label class="radio-inline">
								        <input type="radio" name="status" id="status1" value="1" checked> 在架
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="status" id="status0"  value="0"> 下架
								    </label>
							    </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">书名：</label>
                                <div class="col-sm-8">
                                    <input id="bookName" name="bookName" class="form-control" placeholder="请输入单位" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">编码：</label>
                                <div class="col-sm-8">
                                    <input id="code" name="code" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">作者：</label>
                                <div class="col-sm-8">
                                    <input id="author" name="author" class="form-control" type="text">
                                </div>
                            </div>
                            
                              <div class="form-group">
                                <label class="col-sm-3 control-label">价格：</label>
                                <div class="col-sm-8">
                                    <input id="price" name="price" class="form-control" type="text">
                                </div>
                            </div>
                              <div class="form-group">
                                <label class="col-sm-3 control-label">出版社：</label>
                                <div class="col-sm-8">
                                    <input id="publisher" name="publisher" class="form-control" type="text">
                                </div>
                            </div>
                               <div class="form-group">
                                <label class="col-sm-3 control-label">出版日期：</label>
                                <div class="col-sm-8">
                                    <input id="publishYear" name="publishYear" class="form-control" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </form>
                    </div>
            </div>
        </div>
    </div>
</div>


<div class="modal inmodal fade" id="showAdminInfo" tabindex="-1" role="dialog"  aria-hidden="true">
   <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id='saveOrUpdateTest'></h4>
                <small class="font-bold">
            </div>
            <div class="modal-body">


                  <div class="ibox-content">
                        <form class="form-horizontal m-t" id="adminForm">
                        	<div class="form-group hidden">
                                <label class="col-sm-3 control-label">主键：</label>
                                <div class="col-sm-8">
                                    <input id="fadminId" name="adminId" class="form-control" placeholder="请输入主键" type="text">
                                </div>
                            </div>
                            <div class="form-group hidden">
                                <label class="col-sm-3 control-label">单位：</label>
                                <div class="col-sm-8">
                                    <input id="fdepartment" name="department" class="form-control" placeholder="请输入单位" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账号名称：</label>
                                <div class="col-sm-8">
                                    <input id="fname" name="name" class="form-control" type="text" aria-required="true" class="valid">
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
                                <label class="col-sm-3 control-label">是否生效：</label>
                                <div class="col-sm-8">
	                                <label class="radio-inline">
								        <input type="radio" name="isValid" id="isValid1" value="1" checked> 生效
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="isValid" id="isValid0"  value="0"> 失效
								    </label>
							    </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">手机号：</label>
                                <div class="col-sm-8">
                                    <input id="fphone" name="phone" class="form-control" type="text" aria-required="true" aria-invalid="true" class="error">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input id="femail" name="email" class="form-control" type="email">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">生效时间：</label>
                                <div class="col-sm-8">
                                    <input id="feffectTime" name="effectTime" class="form-control">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">失效时间：</label>
                                <div class="col-sm-8">
                                    <input id="ffailTime" name="failTime" class="form-control" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </form>
                    </div>
            </div>
        </div>
    </div>
</div>


<div class="modal inmodal fade" id="showCardIdInfo" tabindex="-1" role="dialog"  aria-hidden="true">
   <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">用户信息详情</h4>
                <small class="font-bold">
            </div>
            <div class="modal-body">
                  <div class="ibox-content">
                        <form class="form-horizontal m-t" id="cardForm">
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
								        <input type="radio" name="gender" id="gender11" value="1" checked> 男
								    </label>
								    <label class="radio-inline">
								        <input type="radio" name="gender" id="gender00"  value="0"> 女
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
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </form>
                    </div>
            </div>
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

<script type="text/javascript" src= "<%=basePath %>views/bookBorrow/js/bookBorrow.js"></script>
<%--<script type="text/javascript" src= "<%=basePath %>static/bootstrap-3.3.7-dist/js/bootstrap-table-mobile.min.js"></script>--%>
<script type="text/javascript">
if(<%=recordType%>==0){
	$("#searchBtn").text("确认借阅");
}else{
	$("#searchBtn").text("确认归还");

}

</script>
</html>