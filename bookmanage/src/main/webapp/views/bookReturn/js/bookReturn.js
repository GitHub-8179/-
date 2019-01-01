
	
function initFunction(){
	
	$("#docDateTable").bootstrapTable({
		method: 'post',
	    contentType: "application/x-www-form-urlencoded",//当请求方法为post的时候,必须要有！！！！
		url: 'borrowCard/selectByExample.do',   
	    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    showRefresh: false,				
	    showToggle: false,				//是否显示详细视图和列表视图的切换按钮
	    showColumns: false,				//是否显示所有的列
	    //toolbar: '#add',                //工具按钮用哪个容器
	    pagination: true,				//是否显示分页（*）
	    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	    pageNumber:1,                       //初始化加载第一页，默认第一页
	    pageSize: 10,                       //每页的记录行数（*）
	    pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	    sortable: true,                     //是否启用排序
	    sortOrder: "asc",                   //排序方式
	    clickToSelect: true,                //是否启用点击选中行
	    uniqueId: "cardId",
	    //showpaginationswitch:true,//是否显示 数据条数选择框
	    height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	  //得到查询的参数
	    queryParams : function (params) {
	        //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	        var temp = {   
	            rows: params.limit,                         //页面大小
	            page: (params.offset / params.limit) + 1,   //页码
	            sort: params.sort,      //排序列名  
	            sortOrder: params.order //排位命令（desc，asc） 
	        };
	        return $("#cxtj #searchForm").serialize()+"&"+jQuery.param(temp);
	    },
	    responseHandler: function(res) {
	    	//console.log(res);
	        return {
	            "total": res.extend.date.total,//总页数
	            "rows": res.extend.date.list   //数据
	         };
	    },
	    columns: [
	    	 {title : '序号',field : 'did',align : 'center',checkbox : true, width : 10},
	    	 {title : '主键',field : 'bookId',align : 'center'},
	    	 {title : '借阅时间',field : 'data',align : 'center'},
	    	 {title : "借阅图书ID" , field : "bookId",align : "center" ,formatter:setPrice() },
	    	 {title : "借阅图书名称" , field : "bookName",align : "center" ,formatter:setPrice() },
	    	 {title : '借阅人ID',field : 'cardId',align : 'center' ,formatter:setCardId()},
	    	 {title : '借阅人名称',field : 'cardName',align : 'center' ,formatter:setCardId()},
	    	 {title : '操作人ID',field : 'adminId',align : 'center'},
	    	 {title : '操作人名称',field : 'adminName',align : 'center'},
	    	 {title : "借阅状态" , field : "recordType",align : "center" ,formatter:setRecordType()}
	     	// events: operateEvents,
            // formatter: operateFormatter
        ]
	}); 
	
}

//查询按钮事件
$('#searchBtn').click(function(){refreshTable()})

function refreshTable(){
	$('#docDateTable').bootstrapTable('refresh', {url: 'borrowCard/selectByExample.do'});
}

function delInfo(){
	var selrows=$('#docDateTable').bootstrapTable('getSelections');
	   
	if(selrows.length>0){
		swal({
            title: "您确定要删除这条信息吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "是的，我要删除！",
            cancelButtonText: "让我再考虑一下…",
            closeOnConfirm: true,
            closeOnCancel: true
        },
        function (isConfirm) {
            if (isConfirm) {
               // swal("删除成功！", "您已经永久删除了这条信息。", "success");
                var ids="";
            	for ( var i in selrows) {
            		ids+=selrows[i].cardId+"-";
            	}
            	$.ajax({
            	  	url:"borrowCard/delManageInfoByKey/"+ids+".do",
            	  	type:"post",
            	  	data:{_method:"DELETE",ids:ids},
//            	  	type:"DELETE",
//            	  	data:{ids:ids},
            	  	dataType: "json", 
            	  	error: function(result){ //失败 
              		 swal({
                         //title: "太帅了",
                         text: "信息提交失败！",
                         type: "error"
                     });
            	  	}, 
            	  	success:function(result){
            	  		swal({title: "提示",text: "删除数据成功"});
            	  		refreshTable();
            	  	}
            	}); 
            } else {
            }
        });
	}else{
		swal({title: "提示",text: "请选择要删除的数据"});
	}
	
	
}
function updateInfo(){
	var selrow=$('#docDateTable').bootstrapTable('getSelections');
	if(selrow.length!=1){
		swal({
            title: "提示",
            text: "请选择一条数据进行修改"
        });
		return ;
	}
//	$('#myModal5').modal('show',selrow[0]);
	var data = selrow[0];
	$('#myModal5').modal('show');

	$("#myModal5 #saveOrUpdateTest" ).text("用户信息修改");

	$("#myModal5 #signupForm #cardId" ).val(data.cardId);
	$("#myModal5 #signupForm #username" ).val(data.username);
//	$("#myModal5 #signupForm #gender" ).val(data.gender);
	var genders = $('#myModal5 #signupForm input[type=radio][name=gender]');
	for ( var i in genders) {
		if(genders[i].value==data.gender){
			genders[i].checked=true;
			break;
		}
	}
	
	
	$("#myModal5 #signupForm #company" ).val(data.company);
	$("#myModal5 #signupForm #phone" ).val(data.phone);
	$("#myModal5 #signupForm #email" ).val(data.email);
}

$('#myModal5').on('show.bs.modal', function (e) {
	$("#signupForm").get(0).reset();
	$("#myModal5 #saveOrUpdateTest" ).text("用户信息新增");

})

function saveUpdate(){
	$.ajax({
	  	url:"borrowCard/saveOrUpdate.do",
	  	type:"post",
	  	data:$("#myModal5 #signupForm").serialize(),
	  	dataType: "json", 
	  	error: function(result){ //失败 
  		 swal({
             //title: "太帅了",
             text: "信息提交失败！",
             type: "error"
         });
	  	}, 
	  	success:function(result){
	  	 $('#myModal5').modal('hide');
  		 swal({
//	             title: "太帅了",
             text: "信息提交成功！",
             type: "success"
         });
  		refreshTable();
	  	}
	}); 
}


function setGender(value, row, index){
//	 if (row.userInfo) {
//         return row.userInfo.userName;
//     } else {
//         return value;
//     }
	return value;
}

function setRank(value, row, index){
//	var date = eval('new ' + eval("111111111111111").source)
//    return date.format("yyyy-MM-dd");
	return value;
}

$('#add1').click(function () {
	$('#myModal5').modal('hide');
});


function ajaxPost(url,data){
	 $.ajax({
		  	url:url,
		  	type:"post",
		  	data:data,
		  	dataType: "json", 
		  	error: function(result){ //失败 
	  		 swal({
	             //title: "太帅了",
	             text: "信息提交失败！",
	             type: "error"
	         });
		  	}, 
		  	success:function(result){
		  	 $('#myModal5').modal('hide');
	  		 swal({
//		             title: "太帅了",
	             text: "信息提交成功！",
	             type: "success"
	         });
	  		 refreshTable();
		  	}
		}); 
}
